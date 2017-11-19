package mmt.core;

//import mmt.core.Station;
//import java.time.LocalDate;

public class Itinerary implements java.io.Serializable{
	private double _totalCost;
	private int _orderNumber;
	private LocalDate _date;
	private Station _stationO;
	private Station _stationD;

	public double getTotalCost() {
		return _totalCost;
	}

	public int getOrderNumber() {
		return _orderNumber;
	}

	public LocalDate getDate() {
		return _date;
	}

	public Itinerary(LocalDate date, Station stationO, Station stationD) {
		_date = date;
		_stationO = stationO;
		_stationD= stationD;
	}
}