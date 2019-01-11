package frc.frc6644.robot.subsystems;

import frc.frc6644.robot.RobotMap;

public class Camera extends Subsystem {
	static int resWidth = 640;
	static int resHeight = 360;
	static USBCamera camera;
	static private Camera instance = null;
	static CvSink imageSink = new CvSink("CV Image Grabber");
	static CvSource imageSource = new CvSource("CV Image Source", VideoMode.PixelFormat.kMJPEG, 640, 480, 30);
	//static MjpegServer cvStream = new MjpegServer("CV Image Stream", 1186);

	/**
     * This getInstance makes it so there can only be one Camera at a time.
     * This is done for multiple reasons, but i'll just say it's to keep processing power down i guess.
     */
    static public Camera getInstance(){
    if(instance == null){
      instance = new Camera();
    }
    return instance;
  }

	/**
	 * This basically makes a camera and should only run once(first time you
	 * getInstance())
	 */
	private Camera() {
		camera.setResolution(resWidth, resHeight);
		camera.setFPS(7);
		imageSink.setSource(camera);
		//cvStream.setSource(imageSource);
		camera = setUsbCamera(0, inputStream);
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				if (camera != null) {
					camera.free();
				}
			}
		});
		// Loads our OpenCV library. This MUST be included
		System.loadLibrary("opencv_java310");
	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	double[] red = { 0.0, 172.32081911262802 };
	double[] green = { 208.67805755395685, 255.0 };
	double[] blue = { 4.586330935251798, 170.14505119453926 };
	int erode1Iterations = 6;
	int dilateIterations = 25;
	int erode2Iterations = 9;
	double findBlobsMinArea = 1.0;
	double[] findBlobsCircularity = { 0.0, 1.0 };
	boolean findBlobsDarkBlobs = false;
	MatOfKeyPoint findBlobsOutput = new MatOfKeyPoint();

	public MatOfKeyPoint process() {
		String return_value;
		Mat resizeInput = new Mat();
		imageSink.grabFrame(resizeInput);
		Imgproc.resize(resizeInput, resizeInput, new Size(resWidth, resHeight), 0.0, 0.0, interpolation);

		// RGB Threshold

		Imgproc.cvtColor(resizeInput, resizeInput, Imgproc.COLOR_BGR2RGB);
		Core.inRange(resizeInput, new Scalar(red[0], green[0], blue[0]), new Scalar(red[1], green[1], blue[1]),
				resizeInput);

		// CV Erode

		erode(resizeInput, resizeInput, erode1Iterations);
		// CV Dilate

		Imgproc.dilate(resizeInput, resizeInput, new Mat(), new Point(-1, -1), dilateIterations, Core.BORDER_CONSTANT,
				new Scalar(-1));
		// CV Erode2

		erode(resizeInput, resizeInput, erode2Iterations);
		// Find Blobs

		findBlobs(resizeInput, findBlobsMinArea, findBlobsCircularity, findBlobsDarkBlobs, findBlobsOutput);
		return findBlobsOutput;
	}

	/**
	 * This exists just because i used erode twice and didn't want to type it twice
	 * 
	 * @param src
	 * @param output
	 * @param iterations
	 */
	private void erode(Mat src, Mat output, int iterations) {
		Imgproc.erode(src, output, new Mat(), new Point(-1, -1), iterations, Core.BORDER_CONSTANT, new Scalar(-1));
	}

	/**
	 * MADE BY GRIP
	 * 
	 * @param input
	 * @param minArea
	 * @param circularity
	 * @param darkBlobs
	 * @param blobList
	 */
	private void findBlobs(Mat input, double minArea, double[] circularity, Boolean darkBlobs, MatOfKeyPoint blobList) {
		FeatureDetector blobDet = FeatureDetector.create(FeatureDetector.SIMPLEBLOB);
		try {
			File tempFile = File.createTempFile("config", ".xml");

			StringBuilder config = new StringBuilder();

			config.append("<?xml version=\"1.0\"?>\n");
			config.append("<opencv_storage>\n");
			config.append("<thresholdStep>10.</thresholdStep>\n");
			config.append("<minThreshold>50.</minThreshold>\n");
			config.append("<maxThreshold>220.</maxThreshold>\n");
			config.append("<minRepeatability>2</minRepeatability>\n");
			config.append("<minDistBetweenBlobs>10.</minDistBetweenBlobs>\n");
			config.append("<filterByColor>1</filterByColor>\n");
			config.append("<blobColor>");
			config.append((darkBlobs ? 0 : 255));
			config.append("</blobColor>\n");
			config.append("<filterByArea>1</filterByArea>\n");
			config.append("<minArea>");
			config.append(minArea);
			config.append("</minArea>\n");
			config.append("<maxArea>");
			config.append(Integer.MAX_VALUE);
			config.append("</maxArea>\n");
			config.append("<filterByCircularity>1</filterByCircularity>\n");
			config.append("<minCircularity>");
			config.append(circularity[0]);
			config.append("</minCircularity>\n");
			config.append("<maxCircularity>");
			config.append(circularity[1]);
			config.append("</maxCircularity>\n");
			config.append("<filterByInertia>1</filterByInertia>\n");
			config.append("<minInertiaRatio>0.1</minInertiaRatio>\n");
			config.append("<maxInertiaRatio>" + Integer.MAX_VALUE + "</maxInertiaRatio>\n");
			config.append("<filterByConvexity>1</filterByConvexity>\n");
			config.append("<minConvexity>0.95</minConvexity>\n");
			config.append("<maxConvexity>" + Integer.MAX_VALUE + "</maxConvexity>\n");
			config.append("</opencv_storage>\n");
			FileWriter writer;
			writer = new FileWriter(tempFile, false);
			writer.write(config.toString());
			writer.close();
			blobDet.read(tempFile.getPath());
		} catch (IOException e) {
			e.printStackTrace();
		}

		blobDet.detect(input, blobList);
	}

	public static String center(MatOfKeyPoint blobsMat) {
		List<KeyPoint> list = blobsMat.toList();
		int largest = 0;// index of largest blob
		int secondLargest = 0;
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).size > list.get(largest).size) {
					secondLargest = largest;
					largest = i;
				}
			}
		} else {
			return "No Blob";// No point found
		}

		largest = list.get(largest).pt.x;
		secondLargest = list.get(secondLargest).pt.x;

		if ((largest + secondLargest) / 2 >= resWidth / 2) {
			return "-> -> TURN RIGHT -> ->";
		} else if ((largest + secondLargest) / 2 <= resWidth / 2) {
			return "<- <- TURN LEFT <- <-";
		} else {
			return "OH NOES Something went wrong";
		}
	}
}

}