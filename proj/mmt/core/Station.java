package mmt.core;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalTime;


public class Station implements java.io.Serializable{
	private String _name;
	private List<TrainStop> _stops = new ArrayList<TrainStop>(); 

	public Station(String name) {
		_name = name;
	}

	public void addStop(TrainStop stop) {
		_stops.add(stop);
	}

	public String getName() {
		return _name;
	}
}