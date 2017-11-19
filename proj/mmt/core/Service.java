package mmt.core;

import mmt.core.Station;
import mmt.core.TrainStop;
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

	public Station getDeparture() {
		return _stops.get(0).getStation();
	}

	public Station getArrival() {
		int last = _stops.size() - 1; //test dis
		return _stops.get(last).getStation();
	}


 	public String showService() {
 		String out = new String();
 		out = out + "Serviço #"+_id+" @ " + String.format("%.2f", _cost);
 		for (TrainStop st : _stops) {
 			out += ("\n" + st.showStop());
 		}
 		return out;
 	}
}