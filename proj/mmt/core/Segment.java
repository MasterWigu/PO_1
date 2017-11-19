package mmt.core;

public class Segment implements java.io.Serializable{
	private TrainStop _TrainStop1;
	private TrainStop _TrainStop2;

	public TrainStop getTrainStop1() {
		return _TrainStop1;
	}

	public TrainStop getTrainStop2() {
		return _TrainStop2;
	}
}