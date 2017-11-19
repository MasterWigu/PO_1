package mmt.core;

import mmt.*;


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


 	public String showService() {
 		String out = new String();
 		out = out + "Serviço #"+_id+" @ "+_cost+"\n";
 		for (TrainStop st : _stops) {
 			out += (st.showStop() + "\n");
 		}
 		return out;
 	}
}