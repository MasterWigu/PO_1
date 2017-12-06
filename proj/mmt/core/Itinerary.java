package mmt.core;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Itinerary implements java.io.Serializable{
	private double _totalCost;
	private int _orderNumber;
	private LocalDate _date;
	private Passenger _passenger;
	private List<Segment> _segments = new ArrayList<Segment>();

	public double getTotalCost() {
		return _totalCost;
	}

	public int getOrderNumber() {
		return _orderNumber;
	}

	public LocalDate getDate() {
		return _date;
	}

	public Itinenary(LocalDate date, Passenger passenger) {
		_date = date;
		_passenger = passenger;
	}

	public void addSegment(Segment segment) {
		_segments.add(segment);
	}

	public Itinerary(LocalDate date, Station stationO, Station stationD) {
		_date = date;

	}

	public Itinerary(Itinerary itin, LocalDate date, Passenger pass) { //para se poder duplicar itinerarios
		_date = date;
		_passenger = pass;
		
	}
}