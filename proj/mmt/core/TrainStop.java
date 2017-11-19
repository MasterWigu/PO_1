package mmt.core;

import mmt.*;
import java.time.LocalTime;

public class TrainStop implements java.io.Serializable{
	private Station _station;
	private LocalTime _time;

	public TrainStop (LocalTime time, Station station) {
		_station = station;
		_time = time;
	}

	public Station getStation() {
		return _station;
	}

	public LocalTime getTime() {
		return _time;
	}


	public String showStop() {
		return String.format("%2.0d", _time.getHour()) + ":" +String.format("%2.0d", _time.getMinute())+ " " + _station.getName();
	}
}