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

import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

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


  /** Apaga todos os passageiros e itinerarios registados. */
  public void reset() {
    _passMap = new TreeMap<Integer, Passenger>();
    _pass = new ArrayList<Passenger>();
    _itinMap = new TreeMap<Integer, Itinerary>();
    _itin = new ArrayList<Itinerary>();
  }

  /**
  * Permite registar um novo passageiro.
  *
  * @param name nome do passageiro.
  */ 
  public void registerPassenger(String name) {
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
  public void changePassengerName(int id, String newName) throws NoSuchPassengerIdException {
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
  public Passenger getPassengerById(int id) throws NoSuchPassengerIdException {
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
  public Collection<Passenger> getPassengers() {
    return Collections.unmodifiableCollection(_pass);
  }

  /**
  * Permite adicionar um serviço.
  *
  * @param id id do serviço.
  * @param cost custo do serviço.
  */
  public void registerService(int id, double cost) {
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
  public Service getServiceById(int id) throws NoSuchServiceIdException {
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
  public Collection<Service> getServices() {
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
  public Collection<Service> getServicesDeparting(Station station) {
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
  * Permite adicionar uma estação.
  *
  * @param name name da estação.
  */
  public Station addStation(String name) {
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
  public Station getStation(String name) throws NoSuchStationNameException {
    Station st = _statMap.get(name);
    if (st == null) {
      throw new NoSuchStationNameException(name);
    }
    return st;
  }
}