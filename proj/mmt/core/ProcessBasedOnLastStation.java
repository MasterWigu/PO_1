package mmt.core;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

public class ProcessBasedOnLastStation extends ProcessByStationName{ 

    public ProcessBasedOnLastStation(TrainCompany trainCompany, String station){
        super(trainCompany, station);
    }


public void addService(String station, TrainCompany trainCompany){
    for(Service s: trainCompany.getServiceMap().values())
      if(s.getLastStation().equals(station))
        _newServices.add(s);
}

public void doComparator(){
    Comparator<Service> comparator;

    comparator = new Comparator<Service>() {
        public int compare(Service service1, Service service2) {
            return (service1.getLastTime()).compareTo(service2.getLastTime());
        }
    };
    Collections.sort(_newServices, comparator);
}

}