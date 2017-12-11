package mmt.core;

/**
* Classe que controla o registo de passageiros, serviços e itinerarios.
*
* @author Henrique Fernandes 87662 
* @author Miguel Oliveira    87689
*
* @version 1.0
*/

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

import java.time.format.DateTimeParseException;

import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.time.LocalTime;
import java.time.LocalDate;

/**
 * A train company has schedules (services) for its trains and passengers that
 * acquire itineraries based on those schedules.
 */
public class TrainCompany implements java.io.Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201708301010L;

  /** Id de cada passageiro. */
  private int _nextPassId;

  /** Id de cada itinerario. */
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
   * The itineraries held by the trainCompany ordered by insertion time.
   */

  private List<Itinerary> _itin = new ArrayList<Itinerary>();

  /**
   * The station held by the trainCompany indexed by unique identifier.
   */
  private Map<String, Station> _statMap = new TreeMap<String, Station>();


  private List<Itinerary> _tempItin = new ArrayList<Itinerary>();

  /** Apaga todos os passageiros e itinerarios registados. */
  protected void reset() {
    _passMap = new TreeMap<Integer, Passenger>();
    _pass = new ArrayList<Passenger>();
    _itin = new ArrayList<Itinerary>();
    _nextPassId = 0;
    _nextItinId = 0;
  }

  /**
  * Permite registar um novo passageiro.
  *
  * @param name nome do passageiro.
  */ 
  protected void registerPassenger(String name) {
  	int id = _nextPassId++;
  	Passenger p = new Passenger(name, id);
    _passMap.put(id, p);
    _pass.add(p);
  }

  /**
  * Permite alterar o nome de um passageiro.
  *
  * @param name nome do passageiro a alterar.
  *
  * @throws NoSuchPassengerIdException Id do passageiro inválido.
  */ 
  protected void changePassengerName(int id, String newName) throws NoSuchPassengerIdException {
    Passenger p = _passMap.get(id);
    if (p == null) {
      throw new NoSuchPassengerIdException(id);
    }
    p.setName(newName);
  }

  /**
  * Retorna uma collection com os pasageiros com o id dado.
  *
  * @param id id do passageiro desejado.
  *
  * @return Collection Collection com os passageiros com o id dado.
  *
  * @throws NoSuchPassengerIdException Id do passageiro inválido.
  */
  protected Passenger getPassengerById(int id) throws NoSuchPassengerIdException {
    Passenger p = _passMap.get(id);
    if (p==null) {
      throw new NoSuchPassengerIdException(id);
    }
    return p;
  }

  /**
  * Retorna uma collection com todos os passageiros.
  *
  * @return Collection Collection com todos os passageiros.
  */
  protected Collection<Passenger> getPassengers() {
    return Collections.unmodifiableCollection(_pass);
  }

  /**
  * Permite adicionar um serviço.
  *
  * @param id id do serviço.
  * @param cost custo do serviço.
  */
  protected void registerService(int id, double cost) {
    Service s = new Service(id, cost);
    _servMap.put(id, s);
    _serv.add(s);
  }

  /**
  * Retorna uma collection com os serviços com o id dado.
  *
  * @param id id do serviço desejado.
  *
  * @return Collection Collection com os serviços com o id dado.
  *
  * @throws NoSuchServiceIdException Id do serviço inválido.
  */ 
  protected Service getServiceById(int id) throws NoSuchServiceIdException {
  	Service s = _servMap.get(id);
    if (s == null) {
      throw new NoSuchServiceIdException(id);
    }
    return s;
  }

  /**
  * Retorna uma collection com todos os serviços ordenados por id.
  *
  * @return Collection Collection com todos os serviços.
  */
  protected Collection<Service> getServices() {
    List<Service> servs = new ArrayList<Service>();
    for (Integer id : _servMap.keySet()) {
      servs.add(_servMap.get(id));
    }
    return Collections.unmodifiableCollection(servs);
  }

  /**
  * Retorna uma collection com os serviços com uma dada estação ordenados por hora de partida.
  *
  * @param station Estação do serviço desejado.
  *
  * @return Collection Collection com os serviços com a estação dada.
  */
  protected Collection<Service> getServicesDeparting(Station station) {
    Comparator<Service> comparator;
    List<Service> out = new ArrayList<Service>();
    for (Service s : _serv) {
      if (s.getDepartureStation() == station) {
        out.add(s);
      }
    } //em out estao todos os servicos com partida na estacao
    comparator = new Comparator<Service>() {
      public int compare(Service s1, Service s2) {
        return s1.getDepartureStop().getTime().compareTo(s2.getDepartureStop().getTime()); 
      }
    };

    Collections.sort(out, comparator);

    return Collections.unmodifiableCollection(out);
  }


   /**
  * Retorna uma collection com os serviços com uma dada estação ordenados por hora de chegada.
  *
  * @param station Estação do serviço desejado.
  *
  * @return Collection Collection com os serviços com a estação dada.
  */
  protected Collection<Service> getServicesArriving(Station station) {
    Comparator<Service> comparator;
    List<Service> out = new ArrayList<Service>();
    for (Service s : _serv) {
      if (s.getArrivalStation() == station) {
        out.add(s);
      }
    } //em out estao todos os servicos com partida na estacao
    comparator = new Comparator<Service>() {
      public int compare(Service s1, Service s2) {
        return s1.getArrivalStop().getTime().compareTo(s2.getArrivalStop().getTime()); 
      }
    };

    Collections.sort(out, comparator);

    return Collections.unmodifiableCollection(out);
  }


  protected Collection<Service> getServicesPassing(Station station) {
    List<Service> services = new ArrayList<Service>();
    for (Service i : _serv) {
      if (i.passesStation(station))
        services.add(i);
    }
    return Collections.unmodifiableCollection(services);
  }

  protected Collection<Integer> getServicesIdPassing(Station station) {
    List<Integer> ids = new ArrayList<Integer>();
    for (Service i : _serv) {
      if (i.passesStation(station))
        ids.add(i.getId());
    }
    return Collections.unmodifiableCollection(ids);
  }

  /**
  * Permite adicionar uma estação.
  *
  * @param name name da estação.
  */
  protected Station addStation(String name) {
    //checks if the station already exists, if true, returns the station, else, creates the station and returns it
    if (_statMap.containsKey(name))
      return _statMap.get(name);
    Station st = new Station(name);
    _statMap.put(name, st);
    return st;
  }

  /**
  * Retorna uma certa estação.
  *
  * @param name nome da estação.
  *
  * @return st Estação retornada.
  *
  * @throws NoSuchStationNameException Nome da estação inválida.
  */
  protected Station getStation(String name) throws NoSuchStationNameException {
    Station st = _statMap.get(name);
    if (st == null) {
      throw new NoSuchStationNameException(name);
    }
    return st;
  }

  protected Collection<Segment> getSegmentsBetween(int servId, Station origin, Station dest) {
    Service serv = _servMap.get(servId);
    TrainStop t1 = serv.getTrainStop(origin);
    TrainStop t2 = serv.getTrainStop(dest);
    List<Segment> segs = new ArrayList<Segment>();
    
    List<TrainStop> stops = new ArrayList<TrainStop>(serv.getStopsBetween(t1, t2));

    for (int i = 0; i < stops.size()-1; i++) {
      segs.add(new Segment(stops.get(i), stops.get(i+1), serv));
    }
    return Collections.unmodifiableCollection(segs);
  }
  

  protected void commitItinerary(int passengerId, int itineraryNumber) throws NoSuchPassengerIdException, NoSuchItineraryChoiceException {
    if (itineraryNumber == 0) { //discard the search
      _tempItin = new ArrayList<Itinerary>();
    } 
    else {
      try {
        Itinerary i = _tempItin.get(itineraryNumber-1);
        this.getPassengerById(passengerId).addItinerary(i);
        _itin.add(i);
      } catch (IndexOutOfBoundsException iob) {
        throw new NoSuchItineraryChoiceException(passengerId, itineraryNumber);
      }
    }
  }

  public String searchItineraries(int passengerId, String departureStation, String arrivalStation, String departureDate,
                                              String departureTime) throws NoSuchStationNameException, NoSuchPassengerIdException, 
                                              BadTimeSpecificationException, BadDateSpecificationException, BadTimeSpecificationException {
    _tempItin = new ArrayList<Itinerary>(); //reset the temporary buffer
    LocalTime depTime;
    LocalDate depDate;
    try{
      depTime = LocalTime.parse(departureTime);
    } catch (DateTimeParseException btp) {
      throw new BadTimeSpecificationException(btp.getParsedString());
    }

    try {
      depDate = LocalDate.parse(departureDate);
    } catch (DateTimeParseException btp) {
      throw new BadDateSpecificationException(btp.getParsedString());
    }


    Station dep = this.getStation(departureStation);
    Station arr = this.getStation(arrivalStation);
    this.getPassengerById(passengerId); //only to check if the passenger exists   

    List<Service> depServs = new ArrayList<Service>(getServicesPassing(dep));
    List<Service> arrServs = new ArrayList<Service>(getServicesPassing(arr));
    depServs.retainAll(arrServs); //maintain in depServs only the services that are suitable for a direct itinerary


    List<Segment> segs;
    int num = 1;
    for (Service serv : depServs) {
      if (serv.getTrainStop(dep).getTime().isBefore(serv.getTrainStop(arr).getTime())) { //check the direction of the train
        segs = new ArrayList<Segment>(this.getSegmentsBetween(serv.getId(), dep, arr));
        if (segs.get(0).getOrigin().getTime().isAfter(depTime)) { //consider only itineraries that depart after the departureTime 
          _tempItin.add(new Itinerary(segs, depDate, passengerId, num++));
        }
      }
    }

    Comparator<Itinerary> comparator = new Comparator<Itinerary>() {
      public int compare(Itinerary i1, Itinerary i2) {
        int temp = 0;

        temp = i1.getDepartureTime().compareTo(i2.getDepartureTime());
        if (temp != 0)
          return temp;
        temp = i1.getArrivalTime().compareTo(i2.getArrivalTime());
        if (temp != 0)
          return temp;
        return Integer.compare(Long.valueOf(i1.getDuration()).intValue(), Long.valueOf(i2.getDuration()).intValue());
      }
    };

    Collections.sort(_tempItin, comparator);

    //FALTA ORDENAR OS ITINERARIOS
    boolean found = false;
    String out = new String();
    for (Itinerary i : _tempItin) {
      out+="\n" + i.toString() + "\n"; //DERRRRPRPPPP
      found = true;
    }

    if (found)
      return out.substring(0, out.length()-1); //to eliminate the ending \n
    else
      return "";
  }

  public String getIndirectItineraries(int passengerId, String departureStation, String arrivalStation, String departureDate,
                                              String departureTime) throws NoSuchStationNameException, NoSuchPassengerIdException, 
                                              BadTimeSpecificationException, BadDateSpecificationException, BadTimeSpecificationException{
    //List<Station> stations = new ArrayList<Station>();
    List<Itinerary> itins = new ArrayList<Itinerary>();

    Station dep = this.getStation(departureStation);
    Station arr = this.getStation(arrivalStation);
    this.getPassengerById(passengerId);

    LocalTime depTime;
    LocalDate depDate;
    try{
      depTime = LocalTime.parse(departureTime);
    } catch (DateTimeParseException btp) {
      throw new BadTimeSpecificationException(btp.getParsedString());
    }

    try {
      depDate = LocalDate.parse(departureDate);
    } catch (DateTimeParseException btp) {
      throw new BadDateSpecificationException(btp.getParsedString());
    }

    _tempItin = getItineraries( passengerId, dep, arr, depDate, depTime);
    
     Comparator<Itinerary> comparator = new Comparator<Itinerary>() {
      public int compare(Itinerary i1, Itinerary i2) {
        int temp = 0;

        temp = i1.getDepartureTime().compareTo(i2.getDepartureTime());
        if (temp != 0)
          return temp;
        temp = i1.getArrivalTime().compareTo(i2.getArrivalTime());
        if (temp != 0)
          return temp;
        return Integer.compare(Long.valueOf(i1.getDuration()).intValue(), Long.valueOf(i2.getDuration()).intValue());
      }
    };

    Collections.sort(_tempItin, comparator);

    //FALTA ORDENAR OS ITINERARIOS
    boolean found = false;
    String out = new String();
    for (Itinerary i : _tempItin) {
      out+="\n" + i.toString() + "\n"; //DERRRRPRPPPP
      found = true;
    }

    if (found)
      return out.substring(0, out.length()-1); //to eliminate the ending \n
    else
      return "";
  }
  protected List<Itinerary> getItineraries(int passengerId, Station departureStation, Station arrivalStation, LocalDate departureDate, LocalTime departureTime) {
    List<Itinerary> itins = new  ArrayList<Itinerary>();
    for (Station s :_statMap.values()) {
      List<Station> stats = new ArrayList<Station>(_statMap.values());
      stats.remove(s);
      Itinerary itin = getItinerariesRecursive(passengerId, departureStation, arrivalStation, arrivalStation, departureDate, departureTime, new ArrayList<Segment>(), stats);
      if (itin != null)
        itins.add(itin);
    }
    return itins;
  }

  protected Itinerary getItinerariesRecursive(int passengerId, Station departureStation, Station arrivalStation, Station originalArr, LocalDate departureDate, LocalTime departureTime, List<Segment> segs, List<Station> stats) {
    if (arrivalStation.equals(originalArr)) {
      return new Itinerary(segs, departureDate, passengerId, 0);
    }
    if (stats.size() == 0)
      return null;
    for (Station s : stats) {
      stats.remove(s);
      for (Segment seg : this.getSegmentsFrom(s)) {
        if (stats.contains(seg.getDest().getStation())) {
          segs.add(seg);
          return getItinerariesRecursive(passengerId, departureStation, seg.getDest().getStation(), originalArr, departureDate, seg.getDest().getTime(), segs, stats);
        }
        return null;
      }
    }
    return null;
  }


  protected List<Segment> getSegmentsFrom(Station stat) {
    List<Segment> segs = new ArrayList<Segment>();
    List<Service> servs = new ArrayList<Service>(this.getServicesPassing(stat));
    TrainStop t1;
    TrainStop t2;
    for (Service serv : _serv) {
      t1 = serv.getTrainStop(stat);
      t2 = serv.getNextTrainStop(t1);
      if (t2 != null)
        segs.add(new Segment(t1, t2, serv));
    }
    return segs;
  }


  protected String showItineraries() {
    String out = new String();
    boolean found = false;
    try {
      for  (int pass = 0; pass < _pass.size(); pass++) {
        if (_pass.get(pass).getNumItins() != 0) {
          out += showItinerary(pass);
          out += "\n\n";
          found = true;
        }
      }
    } catch (NoSuchPassengerIdException nspi) {
    //Impossible to happen, ignored
    }
    if (found) 
      return out.substring(0, out.length()-2); //to eliminate the two ending \n's
    else
      return "";
  }

  protected String showItinerary(int passengerId) throws NoSuchPassengerIdException {
    Passenger pass = this.getPassengerById(passengerId);
    String out = new String();
    if (pass.getNumItins() != 0) {
      out += "== Passageiro " + pass.getId() + ": " + pass.getName() + " ==";
      out += pass.getItineraries();
    }
    return out;
  }
}

