package mmt.core;

import mmt.core.exceptions.BadDateSpecificationException;
import mmt.core.exceptions.BadEntryException;
import mmt.core.exceptions.BadTimeSpecificationException;
import mmt.core.exceptions.InvalidPassengerNameException;
import mmt.core.exceptions.NoSuchDepartureException;
import mmt.core.exceptions.NoSuchPassengerIdException;
import mmt.core.exceptions.NoSuchServiceIdException;
import mmt.core.exceptions.NoSuchStationNameException;
import mmt.core.exceptions.NoSuchItineraryChoiceException;
import mmt.core.exceptions.NonUniquePassengerNameException;

import mmt.core.Passenger;
import java.util.*;

//FIXME import other classes if necessary

/**
 * A train company has schedules (services) for its trains and passengers that
 * acquire itineraries based on those schedules.
 */
public class TrainCompany implements java.io.Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201708301010L;

  private int _nextPassId;
  private int _nextItinId;


  /**
   * The passengers held by the trainCompany indexed by unique identifier.
   */
  private Map<Integer, Passenger> _passMap = new TreeMap<Integer, Passenger>();
  
  /**
   * The passengers held by the trainCompany ordered by insertion time.
   */
  private List<Passenger> _pass = new ArrayList<Passenger>();

  /**
   * The services held by the trainCompany indexed by unique identifier.
   */

  private Map<Integer, Service> _servMap = new TreeMap<Integer, Service>();
  
  /**
   * The services held by the trainCompany ordered by insertion time.
   */

  private List<Service> _serv = new ArrayList<Service>();

  /**
   * The itineraries held by the trainCompany indexed by unique identifier.
   */
  private Map<Integer, Itinerary> _itinMap = new TreeMap<Integer, Itinerary>();
  
  /**
   * The itineraries held by the trainCompany ordered by insertion time.
   */

  private List<Itinerary> _itin = new ArrayList<Itinerary>();

  /**
   * The station held by the trainCompany indexed by unique identifier.
   */
  private Map<String, Station> _statMap = new TreeMap<String, Station>();


  public void reset() {
    _passMap = new TreeMap<Integer, Passenger>();
    _pass = new ArrayList<Passenger>();
    _itinMap = new TreeMap<Integer, Itinerary>();
    _itin = new ArrayList<Itinerary>();
  }

  public void changePassengerName(int id, String newName) {
    Passenger p = _passMap.get(id);
    p.setName(newName);
  }

  public void registerPassenger(String name) {
  	int id = ++_nextPassId;
  	Passenger p = new Passenger(name, id);
    _passMap.put(id, p);
    _pass.add(p);
  }

  public void registerService(int id, double cost) {
  	Service s = new Service(id, cost);
    _servMap.put(id, s);
    _serv.add(s);
  }

  public Station addStation(String name) {
    //checks if the station already exists, if true, returns the station, else, creates the station and returns it
    if (_statMap.containsKey(name))
      return _statMap.get(name);
    Station st = new Station(name);
    _statMap.put(name, st);
    return st;
  }

/*
  public void registerItinerary(String name) {
  	int id = ++_nextItinId;
  	Itinerary i = new Itinerary(name);
    _itinMap.put(id, i);
    _itin.add(i);
  }
*/

  public Passenger getPassengerById(int id) {
    return _passMap.get(id);
  }

  public Collection<Passenger> getPassengers() {
    return Collections.unmodifiableCollection(_pass);
  }

  public Service getServiceById(int id) {
  	return _servMap.get(id);
  }

  public Collection<Service> getServices() {
    return Collections.unmodifiableCollection(_serv);
  }

  public Collection<Service> getServicesDeparting(Station station) {
    List<Service> out = new ArrayList<Service>();
    for (Service s : _serv) {
      if (s.getDeparture() == station) {
        out.add(s);
      }
    }
    return out;
  }

  public Station getStation(String name) {
    return _statMap.get(name);
  }
/*
  public Itinerary getItineraryById(int id) {
  	Itinerary i = _itinMap.get(id);
  }

  public Itinerary getItinerary() {
    Itinerary i = _itinMap.get();
  }
*/

/*
  public Itinerary searchItineraries(int passengerId, String departureStation, String arrivalStation, String departureDate, String departureTime) { //FIXME define thrown exceptions 

  }

  public Itinerary commitItinerary(int passengerId, int itineraryNumber) { //FIXME define thrown exceptions 

  }
*/
  //FIXME implement other functions if necessary

}
