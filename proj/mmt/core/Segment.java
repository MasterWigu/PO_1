package mmt.core;

import mmt.*;

public class Segment implements java.io.Serializable{
	//private Service _service;
	private TrainStop _TrainStop1;
	private TrainStop _TrainStop2;

	/*public Service getService() {
		return _service;
	}*/

	public TrainStop getTrainStop1() {
		return _TrainStop1;
	}

	public TrainStop getTrainStop2() {
		return _TrainStop2;
	}
}