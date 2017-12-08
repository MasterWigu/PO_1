package mmt.core;

import java.time.temporal.ChronoUnit;


public class Segment implements java.io.Serializable{
	private TrainStop _origin;
	private TrainStop _destination;
	private Service _service;

	protected TrainStop getOrigin() {
		return _origin;
	}

	protected TrainStop getDest() {
		return _destination;
	}

	protected long getDuration() {
		return ChronoUnit.MINUTES.between(_origin.getTime(), _destination.getTime());
	}

	protected Service getService() {
		return _service;
	}

	protected Segment(TrainStop origin, TrainStop destination, Service service) {
		_origin = origin;
		_destination = destination;
		_service = service;
	}

	protected double getCost() {
		return 1.0 * _service.getCost() * this.getDuration() / _service.getTravelTime();	
	} 
}