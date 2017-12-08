package mmt.core;

import java.time.temporal.ChronoUnit;


public class Segment implements java.io.Serializable{
	private TrainStop _origin;
	private TrainStop _destination;
	private Service _service;

	public TrainStop getOrigin() {
		return _origin;
	}

	public TrainStop getDest() {
		return _destination;
	}

	public long getDuration() {
		return ChronoUnit.MINUTES.between(_origin.getTime(), _destination.getTime());
	}

	public Service getService() {
		return _service;
	}

	public Segment(TrainStop origin, TrainStop destination, Service service) {
		_origin = origin;
		_destination = destination;
		_service = service;
	}

	public double getCost() {
		return 1.0 * _service.getCost() * this.getDuration() / _service.getTravelTime();	
	} 
}