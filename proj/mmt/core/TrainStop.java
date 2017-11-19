package mmt.core;

import mmt.core.Station;
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
		return String.format("%02d", _time.getHour()) + ":" +String.format("%02d", _time.getMinute())+ " " + _station.getName();
	}
}