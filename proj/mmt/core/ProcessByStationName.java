package mmt.core;

import mmt.core.exceptions.NoSuchStationNameException;
import java.util.List;
import java.util.ArrayList;

public abstract class ProcessByStationName{

	private String _station;
	private TrainCompany _trainCompany;
	protected List<Service> _newServices = new ArrayList<Service>();
   	private String str = "";

	public ProcessByStationName(TrainCompany trainCompany, String station){
		_trainCompany = trainCompany;
		_station = station;

	}

	public String selectServices() throws NoSuchStationNameException{
		check();
		addService(_station, _trainCompany);
		doComparator();
		addtoString();
		return str;

	}

	public void check() throws NoSuchStationNameException{
		if(!(_trainCompany.getStationMap().containsKey(_station)))
      		throw new NoSuchStationNameException(_station);
	}

	public abstract void addService(String station, TrainCompany trainCompany);

	public abstract void doComparator();

	public void addtoString(){
		for(Service s: _newServices)
      		str += s.toString();
	}

}