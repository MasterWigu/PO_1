package mmt.core;


import java.util.List;
import java.util.ArrayList;
import java.time.Duration;
import java.util.Comparator;
import java.util.Collections;

public class Passenger implements java.io.Serializable{

	private double _totalCost;
	private double[] _costs;
	private String _name;
	private int _id;
	private long _tripTimeMin; //total trip time in minutes since java.Duration doesnt offer any good options
 	private List<Itinerary> _itin = new ArrayList<Itinerary>();
	private Category _category;

	protected int getTotalCost() {
		int sum=0;
		for(int i=0; i<10;i++){
			sum +=_costs[i];
		}
		return sum;
	}

	protected String getName() {
		return _name;
	}

	protected int getId() {
		return _id;
	}

	protected void setName(String name) {
		_name = name;
	}

	protected int getNumItins() {
		return _itin.size();
	}

	protected long getTotalTimePass() {
		return _tripTimeMin;  
	}

    protected void addItinerary(Itinerary itin) {
    	itin.setOrderNumber(_itin.size()+1);
    	_itin.add(itin);
    	_costs[_itin.size()%10] = itin.getCost();
    	_totalCost += itin.getCost();
    	_tripTimeMin += itin.getDuration();
    }

	protected Passenger(String name, int id) {
		_name = name;
		_costs = new double[10];
		_id = id;
		_tripTimeMin = 0;
		Normal cat = new Normal();
		this.setCategory(cat);
	}

	protected void checkCategory() {
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


    protected void setCategory(Category category) {
        _category = category;		
    }

    protected double getDiscount() {
        return _category.getDiscount();
    }

    protected String getItineraries() {
    	String out = new String();
    	List<Itinerary> tempItin = new ArrayList<Itinerary>(_itin);

		Comparator<Itinerary> comparator = new Comparator<Itinerary>() {
      		public int compare(Itinerary i1, Itinerary i2) {
       			return i1.getDate().compareTo(i2.getDate());
      		}
    	};

    	Collections.sort(tempItin, comparator);
    	int num = 1;
    	for (Itinerary i : tempItin) {
    		out += "\n\n";
    		i.setOrderNumber(num++);
    		out += i.toString();
    	}
    	return out;
    }

    public String showPassenger() {
    	long hours = _tripTimeMin / 60;
    	long minutes = _tripTimeMin % 60;
        return "" + _id + "|" + _name + "|" + _category.toString() + "|" + _itin.size() + "|" + String.format("%.2f", _totalCost) + "|" + String.format("%02d", hours) + ":" + String.format("%02d", minutes);
    }
}