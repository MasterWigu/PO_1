package mmt.core;


import java.util.List;
import java.util.ArrayList;
import java.time.Duration;

public class Passenger implements java.io.Serializable{

	private double _totalCost;
	private int[] _costs = new int[10];
	private String _name;
	private int _id;
	private long _tripTimeMin; //total trip time in minutes since java.Duration doesnt offer any good options
 	private List<Itinerary> _itin = new ArrayList<Itinerary>();
	private Category _category;

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

	public void setName(String name) {
		_name = name;
	}

	public Passenger(String name, int id) {
		_name = name;
		_costs = new int[10];
		_id = id;
		_tripTimeMin = 0;
		Normal cat = new Normal();
		this.setCategory(cat);
	}

	public void checkCategory() {
		double sum = 0;
		for (double i : _costs) {
			sum+=i;
		}
		if (sum <= 250) {
			Normal cat = new Normal();
			this.setCategory(cat);
		}
		else if (sum <= 2500) {
			Frequent cat = new Frequent();
			this.setCategory(cat);
		}
		else {
			Special cat = new Special();
			this.setCategory(cat);
		}
	}


    public void setCategory(Category category) {
        _category = category;		
    }

    public double getDiscount() {
        return _category.getDiscount();
    }


    public String showPassenger() {
    	long hours = _tripTimeMin / 60;
    	long minutes = _tripTimeMin % 60;
        return "" + _id + "|" + _name + "|" + _category.toString() + "|" + _itin.size() + "|" + String.format("%.2f", _totalCost) + "|" + String.format("%02d", hours) + ":" + String.format("%02d", minutes);
    }
}