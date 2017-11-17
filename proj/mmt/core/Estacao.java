package mmt.core;

import mmt.*;

import java.time.LocalTime;

public class Estacao {
	private String _nome;
	private LocalTime _time;

	public String getEstacao() {
		return _nome;
	}

	public LocalTime getTime() {
		return _time;
	}
}