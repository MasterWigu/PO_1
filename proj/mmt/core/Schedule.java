package mmt.core;

import mmt.*;
import java.time.LocalTime;
import java.time.LocalDate;

public class Schedule {
	private LocalDate _date;
	private LocalTime _time;

	public Schedule (LocalDate date, LocalTime time) {
		_date = date;
		_time = time;
	}

	public LocalDate getDate() {
		return _date;
	}

	public LocalTime getTime() {
		return _time;
	}
}