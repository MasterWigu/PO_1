package mmt.core;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Collection;
import java.time.temporal.ChronoUnit;

public class Itinerary implements java.io.Serializable{
	private int _orderNumber;
	private LocalDate _date;
	private int _passengerId;
	private List<Segment> _segments = new ArrayList<Segment>();

	protected double getCost() {
		double cost = 0;
		for(Segment s : _segments){
			cost += s.getCost();
		}
		return cost;
	}

	protected int getOrderNumber() {
		return _orderNumber;
	}

	protected LocalDate getDate() {
		return _date;
	}

	protected Collection<Segment> getSegmentList() {
		return Collections.unmodifiableCollection(_segments);
	}

	protected void addSegment(Segment segment) {
		_segments.add(segment);
	}

	protected void addSegments(Collection<Segment> segs) {
		for (Segment i : segs) {
			this.addSegment(i);
		}
	}

	protected long getDuration() {
		TrainStop start = _segments.get(0).getOrigin();
		TrainStop end = _segments.get(_segments.size()-1).getDest();
		return ChronoUnit.MINUTES.between(start.getTime() , end.getTime());
	}

	protected void setOrderNumber(int order) {
		_orderNumber = order;
	}

	public String toString() {
		String out = new String();
		Service serv = _segments.get(0).getService();
		TrainStop t1 = _segments.get(0).getOrigin();
		TrainStop t2 = null;

		for(int i = 0; i<_segments.size(); i++) {
			if (!(_segments.get(i).getService().equals(serv))) {
				t2 = _segments.get(i-1).getDest();
				out = out + serv.showService(t1, t2);
				serv = _segments.get(i).getService();
				t1 = _segments.get(i).getOrigin();
			}
		}
		t2 = _segments.get(_segments.size()-1).getDest();
		out = out + serv.showService(t1, t2);

		return out;
	}

	protected Itinerary(LocalDate date, int passengerId) {
		_date = date;
		_passengerId = passengerId;
		_orderNumber = 0; //to be redifined later
	}

	protected Itinerary(LocalDate date, int passengerId, int orderNum) {
		_date = date;
		_passengerId = passengerId;
		_orderNumber = orderNum;
	}


	protected Itinerary(Itinerary itin, LocalDate date, int passId, int orderNum) { //para se poder duplicar itinerarios
		_date = date;
		_passengerId = passId;
		for (Segment i : itin.getSegmentList())
			_segments.add(i);
		_orderNumber = orderNum;
	}
}