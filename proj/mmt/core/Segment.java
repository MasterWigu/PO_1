package mmt.core;

public class Segment implements java.io.Serializable{
	private TrainStop _origin;
	private TrainStop _destination;

	public TrainStop getTrainStop1() {
		return _origin;
	}

	public TrainStop getTrainStop2() {
		return _destination;
	}

	/*public long getDuration() {
		return 
	}*/

	public Segment(TrainStop origin, TrainStop destination) {
		_origin = origin;
		_destination = destination;
	}
}