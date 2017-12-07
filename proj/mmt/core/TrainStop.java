package mmt.core;

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

	public boolean isFirst(TrainStop stop) {
		return this.getTime().isBefore(stop.getTime());
	}

	public boolean equals(TrainStop stop) {
		return _station.equals(stop.getStation()) && _time.equals(stop.getTime());
	}

	public String showStop() {
		return String.format("%02d", _time.getHour()) + ":" +String.format("%02d", _time.getMinute())+ " " + _station.getName();
	}
}