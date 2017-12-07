package mmt.core;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Collection;

public class Itinerary implements java.io.Serializable{
	private double _totalCost;
	private int _orderNumber;
	private LocalDate _date;
	private Passenger _passenger;
	private List<Segment> _segments = new ArrayList<Segment>();

	public double getTotalCost() {
		return _totalCost;
	}

	public double getItinCost(Itinerary itin) {
		double cost = 0;
		for(Segment s : _segments){
			cost += getCost(s);	//getCost a definir
		}
		return cost;
	}

	public int getOrderNumber() {
		return _orderNumber;
	}

	public LocalDate getDate() {
		return _date;
	}

	public Collection<Segment> getSegmentList() {
		return Collections.unmodifiableCollection(_segments);
	}

	public void addSegment(Segment segment) {
		_segments.add(segment);
	}

	public void setOrderNumber(int order) {
		_orderNumber = order;
	}

	public Itinerary(LocalDate date, Passenger passenger, int orderNum) {
		_date = date;
		_passenger = passenger;
	}


	public Itinerary(Itinerary itin, LocalDate date, Passenger pass, int orderNum) { //para se poder duplicar itinerarios
		_date = date;
		_passenger = pass;
		for (Segment i : itin.getSegmentList())
			_segments.add(i);
		_orderNumber = orderNum;
	}
}