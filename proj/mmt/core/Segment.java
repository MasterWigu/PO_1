package mmt.core;

public class Segment implements java.io.Serializable{
	private TrainStop _trainStop1;
	private TrainStop _trainStop2;

	public TrainStop getTrainStop1() {
		return _trainStop1;
	}

	public TrainStop getTrainStop2() {
		return _trainStop2;
	}

	public Segment(TrainStop t1, TrainStop t2) {
		_trainStop1 = t1;
		_trainStop2 = t2;
	}
}