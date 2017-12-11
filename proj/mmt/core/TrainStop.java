package mmt.core;

import java.time.LocalTime;

public class TrainStop implements java.io.Serializable{
	private Station _station;
	private LocalTime _time;


	protected TrainStop (LocalTime time, Station station) {
		_station = station;
		_time = time;
	}

	protected Station getStation() {
		return _station;
	}

	protected LocalTime getTime() {
		return _time;
	}

	protected boolean isFirst(TrainStop stop) {
		return this.getTime().isBefore(stop.getTime());
	}

	protected boolean equals(TrainStop stop) {
		return _station.equals(stop.getStation()) && _time.equals(stop.getTime());
	}

	protected String showStop() {
		return String.format("%02d", _time.getHour()) + ":" +String.format("%02d", _time.getMinute())+ " " + _station.getName();
	}
}