package mmt.core;

import mmt.*;

import java.time.LocalTime;

public class Station {
	private String _name;
	private List<TrainStop> _stops = new ArrayList<TrainStop>(); 

	public Station(String name) {
		_name = name;
	}

	public addStop(TrainStop stop) {
		_stops.add(stop);
	}

	public String getName() {
		return _name;
	}

	public LocalTime getTime() {
		return _time;
	}
}