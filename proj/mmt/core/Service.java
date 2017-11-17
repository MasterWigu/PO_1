package mmt.core;

import mmt.*;


public class Service {

	private int _id;
	private double _cost;


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
}