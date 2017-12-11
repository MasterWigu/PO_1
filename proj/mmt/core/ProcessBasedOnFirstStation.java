package mmt.core;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

public class ProcessBasedOnFirstStation extends ProcessByStationName{ 

public ProcessBasedOnFirstStation(TrainCompany trainCompany, String station){
super(trainCompany, station);
}


public void addService(String station, TrainCompany trainCompany){
	for(Service s: trainCompany.getServiceMap().values())
      if(s.getFirstStationName().equals(station))
        _newServices.add(s);
}

public void doSort(){
    Comparator<Service> comparator;

    comparator = new Comparator<Service>() {
        public int compare(Service service1, Service service2) {
            return (service1.getFirstTime()).compareTo(service2.getFirstTime());
        }
    };
    Collections.sort(_newServices, comparator);
}

}