package mmt.core;

import mmt.*;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalTime;


public class Passenger implements java.io.Serializable{

	private int _totalCost;
	private int[] _costs;
	private String _name;
	private int _id;
	private String _type;
	private LocalTime _lTime;
 	private List<Itinerary> _itin = new ArrayList<Itinerary>();

	public int getTotalCost() {
		int sum=0;
		for(int i=0; i<10;i++){
			sum +=_costs[i];
		}
		return sum;
	}

	public String getName() {
		return _name;
	}

	public int getId() {
		return _id;
	}

	public String getType() {
		return _type;
	}

	public void setName(String name) {
		_name = name;
	}

	public Passenger(String name, int id) {
		_name = name;
		_costs = new int[10];
		_type = "NORMAL";
		_id = id;
		_lTime = LocalTime.parse("00:00:00.0");
	}


  public String showPassenger() {
    return "" + _id + "|" + _name + "|" + _type + "|" + _itin.size() + "|" + _totalCost + "|" + String.format("%.2f", _ltime.getHour()) + ":" + String.format("%.2f", _ltime.getMinute());
  }
}