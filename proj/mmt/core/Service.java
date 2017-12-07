package mmt.core;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Collection;

public class Service implements java.io.Serializable{

	private int _id;
	private double _cost;
	private int _travelTime;
	private List<TrainStop> _stops = new ArrayList<TrainStop>();


	public int getId(){
		return _id;
	}

	public double getCost() {
		return _cost;
	}

	public Service (int id, double cost) {
		_id = id;
		_cost = cost;
		_travelTime = 0;
	}

	public TrainStop addStop(LocalTime stopTime, Station station) {
		TrainStop st = new TrainStop(stopTime, station);
		LocalTime lastTime = _stops.get(_stop.size()-1).getTime();
		int minutes = MINUTES.between(stopTime, lastTime);
		_travelTime += minutes;
		_stops.add(st);
		return st;
	}

	public TrainStop getDepartureStop() {
		return _stops.get(0);
	}

	public Station getDepartureStation() {
		return _stops.get(0).getStation();
	}

	public TrainStop getArrivalStop() {
		int last = _stops.size() - 1;
		return _stops.get(last);
	}

	public Station getArrivalStation() {
		int last = _stops.size() - 1;
		return _stops.get(last).getStation();
	}

	public TrainStop getTrainStop(Station stat) {
		for (TrainStop s : _stops) {
			if (s.getStation() == stat)
				return s;
		}
	}

	public boolean passesStation(Station stat) {
		for (TrainStop i: _stops) {
			if (i.getStation() == stat)
				return true;
		}
		return false;
	}


 	public String showService() {
 		String out = new String();
 		out = out + "Serviço #"+_id+" @ " + String.format("%.2f", _cost);
 		for (TrainStop st : _stops) {
 			out += ("\n" + st.showStop());
 		}
 		return out;
 	}

 	public Collection<TrainStop> getStopsBetween(TrainStop start, TrainStop end) {
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