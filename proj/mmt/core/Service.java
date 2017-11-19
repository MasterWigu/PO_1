package mmt.core;

import mmt.*;
import java.util.*;
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

	public Station getDeparture() {
		return _stops.get(0).getStation();
	}

	public Station getArrival() {
		int last = _stops.size() - 1; //test dis
		return _stops.get(last).getStation();
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