package mmt.core;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Collection;
import java.time.temporal.ChronoUnit;

public class Service implements java.io.Serializable{

	private int _id;
	private double _cost;
	private long _travelTime;
	private List<TrainStop> _stops = new ArrayList<TrainStop>();


	protected int getId(){
		return _id;
	}

	protected double getCost() {
		return _cost;
	}

	protected double getPartCost(TrainStop origin, TrainStop destination) {
		long minutes = ChronoUnit.MINUTES.between(origin.getTime(), destination.getTime());
		return 1.0 * _cost * minutes / _travelTime;
	}

	protected Service (int id, double cost) {
		_id = id;
		_cost = cost;
		_travelTime = 0;
	}

	protected long getTravelTime() {
		return _travelTime;
	}

	protected TrainStop addStop(LocalTime stopTime, Station station) {
		TrainStop st = new TrainStop(stopTime, station);
		int indice = _stops.size() - 1;
		if (indice != -1) { //caso em que a lista ja nao esta vazia
			LocalTime lastTime = _stops.get(indice).getTime();
			long minutes = ChronoUnit.MINUTES.between(lastTime, stopTime);
			_travelTime += minutes;
		}
		_stops.add(st);
		return st;
	}

	protected TrainStop getDepartureStop() {
		return _stops.get(0);
	}

	protected String getFirstStationName() {
		return _stops.get(0).getStation().getName();
	}

	protected LocalTime getFirstTime() {
		return this.getDepartureStop().getTime();
	}

	protected TrainStop getArrivalStop() {
		int last = _stops.size() - 1;
		return _stops.get(last);
	}

	protected String getLastStationName() {
		int last = _stops.size() - 1;
		return _stops.get(last).getStation().getName();
	}

	protected LocalTime getLastTime() {
		return this.getArrivalStop().getTime();
	}

	protected TrainStop getTrainStop(Station stat) {
		for (TrainStop s : _stops) {
			if (s.getStation() == stat)
				return s;
		}
		return null;
	}

	protected boolean passesStation(Station stat) {
		for (TrainStop i: _stops) {
			if (i.getStation() == stat)
				return true;
		}
		return false;
	}

 	public String toString() {
 		String out = new String();
 		out = out + "Serviço #"+_id+" @ " + String.format("%.2f", _cost);
 		for (TrainStop st : _stops) {
 			out += ("\n" + st.showStop());
 		}
 		return out;
 	}

 	public String showService(TrainStop origin, TrainStop dest) {
 		String out = new String();
 		boolean found = false;
 		out = out + "Serviço #"+_id+" @ " + String.format("%.2f", this.getPartCost(origin, dest));
 		for (TrainStop st : _stops) {
 			if (st.equals(origin))
 				found = true;
 			if (found)
 				out += ("\n" + st.showStop());
 			if (st.equals(dest))
 				break;
 		}
 		return out;
 	}

 	protected Collection<TrainStop> getStopsBetween(TrainStop start, TrainStop end) {
 		List<TrainStop> tStops = new ArrayList<TrainStop>();
 		boolean found = false;
 		for (TrainStop st : _stops) {
 			if (st.equals(start))
 				found = true;
 			if (found)
 				tStops.add(st);
 			if (st.equals(end))
 				break;
 		}
 		return Collections.unmodifiableCollection(tStops);
 	}
}