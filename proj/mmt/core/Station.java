package mmt.core;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalTime;


public class Station implements java.io.Serializable{
	private String _name;
	private List<TrainStop> _stops = new ArrayList<TrainStop>(); 

	protected Station(String name) {
		_name = name;
	}

	protected void addStop(TrainStop stop) {
		_stops.add(stop);
	}

	protected String getName() {
		return _name;
	}

	protected boolean equals(Station stat) {
		return _name.equals(stat.getName());
	}
}