package mmt.core;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalTime;


public class Service implements java.io.Serializable{

	private int _id;
	private double _cost;
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
	}

	public TrainStop addStop(LocalTime ltime, Station station) {
		TrainStop st = new TrainStop(ltime, station);
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

  	public String showService(TrainStop origin, TrainStop destination) {
 		String out = new String();
 		out = out + "Serviço #"+_id+" @ " + String.format("%.2f", _cost);
 		for (TrainStop st : _stops) {
 			
 			out += ("\n" + st.showStop());
 		}
 		return out;
 	}
}