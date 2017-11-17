package mmt.core;

public class Horario {
	private LocalDate _date;
	private LocalTime _time;

	public Horario (LocalDate date, LocalTime time) {
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