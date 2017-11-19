package mmt.core;

import mmt.*;
import java.util.*;
import java.time.LocalTime;

public class Service {

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

	public Station getDeparture() {
		return _stops.get(0);
	}

	public Station getArrival() {
		int last = _stop.length() - 1; //test dis
		return _stops.get(last);
	}


 	public String showService() {
 		String out = new String();
 		out = out + "Serviço #"+_id+" @ "+_cost+"\n";
 		for (TrainStop st : _stops) {
 			out += (st.showStop() + "\n");
 		}
 		return out;
 	}
}