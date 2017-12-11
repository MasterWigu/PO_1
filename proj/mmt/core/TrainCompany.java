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
   * The station held by the trainCompany indexed by unique identifier.
   */
  private Map<String, Station> _statMap = new TreeMap<String, Station>();

  /**
   * The itineraries held by the trainCompany ordered by insertion time.
   */
  private List<Itinerary> _tempItin = new ArrayList<Itinerary>();

  /** Apaga todos os passageiros e itinerarios registados. */
  protected void reset() {
    _passMap = new TreeMap<Integer, Passenger>();
    _pass = new ArrayList<Passenger>();
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
  * Retorna o pasageiro com o id dado.
  *
  * @param id id do passageiro desejado.
  *
  * @return Passenger Passenger com o id dado.
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
  * Retorna a string representando pasageiro com o id dado.
  *
  * @param id id do passageiro desejado.
  *
  * @return String String do passageiro com o id dado.
  *
  * @throws NoSuchPassengerIdException Id do passageiro inválido.
  */
  protected String getPassengerStringById(int id) throws NoSuchPassengerIdException {
    return this.getPassengerById(id).showPassenger();
  }

  /**
  * Retorna uma string com todos os passageiros.
  *
  * @return String String com todos os passageiros.
  */
  protected String getPassengers() {
    String out = "";
    for(Passenger p : _pass){
      out += (p.showPassenger() + "\n");
    }
    if (out.length() > 1)
      return out.substring(0, out.length()-1);
    return "";
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
  * Retorna o serviço com o id dado.
  *
  * @param id id do serviço desejado.
  *
  * @return Service Serviço com o id dado.
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
  * Retorna uma string com o serviço com o id dado.
  *
  * @param id id do serviço desejado.
  *
  * @return String Stringg com o serviço com o id dado.
  *
  * @throws NoSuchServiceIdException Id do serviço inválido.
  */ 
  protected String getServiceStringById(int id) throws NoSuchServiceIdException {
    return getServiceById(id).toString();
  }

  /**
  * Retorna uma collection com todos os serviços ordenados por id.
  *
  * @return String String com todos os serviços.
  */
  protected String getServices() {
    List<Service> servs = new ArrayList<Service>();
    String out = "";
    for (Integer id : _servMap.keySet()) {
      servs.add(_servMap.get(id));
    }

    for(Service s : servs)
      out += (s.toString() + "\n");

    if (out.length() > 1)
      return out.substring(0, out.length()-1);
    return "";
  }

  /**
  * Retorna os serviços que partem de uma dada estação.
  *
  * @param station Estação do serviço desejado.
  *
  * @return process.selectServices String com os serviços com a estação dada.
  *
  * @throws NoSuchStationNameException Nome da estação inválida.
  */
  protected String getServicesDeparting(String station) throws NoSuchStationNameException {
    ProcessByStationName process = new ProcessBasedOnFirstStation(this, station);
    return process.selectServices();
  }


  /**
  * Retorna os serviços que chegam a uma dada estação.
  *
  * @param station Estação do serviço desejado.
  *
  * @return process.selectServices String com os serviços com a estação dada.
  *
  * @throws NoSuchStationNameException Nome da estação inválida.
  */
  protected String getServicesArriving(String station) throws NoSuchStationNameException {
    ProcessByStationName process = new ProcessBasedOnLastStation(this, station);
    return process.selectServices();
  }

  /**
  * Retorna uma collection com os serviços que passam numa dada estação.
  *
  * @param station Estação do serviço desejado.
  *
  * @return Collection Collection com os serviços com a estação dada.
  */
  protected Collection<Service> getServicesPassing(Station station) {
    List<Service> services = new ArrayList<Service>();
    for (Service i : _serv) {
      if (i.passesStation(station))
        services.add(i);
    }
    return Collections.unmodifiableCollection(services);
  }

  /**
  * Retorna uma collection com o id dos serviços que passam numa dada estação.
  *
  * @param station Estação do serviço desejado.
  *
  * @return Collection Collection com os ids dos serviços.
  */
  protected Collection<Integer> getServicesIdPassing(Station station) {
    List<Integer> ids = new ArrayList<Integer>();
    for (Service i : getServicesPassing(station)) {
        ids.add(i.getId());
    }
    return Collections.unmodifiableCollection(ids);
  }

  /**
  * Retorna o mapa dos serviços.
  *
  * @return _servMap Mapa com os serviços.
  */
  protected Map<Integer, Service> getServiceMap() {
    return _servMap;
  }

  /**
  * Permite adicionar uma estação.
  *
  * @param name nome da estação.
  *
  * @return retorna a estação.
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
  * Retorna a estação com dado nome.
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

  /**
  * Retorna o mapa de estações.
  *
  * @return _statMap Mapa com as estações.
  */
  protected Map<String, Station> getStationMap() {
    return _statMap;
  }

  /**
  * Obtemos uma lista de segmentos a começar e a acabar numa dada estação.
  *
  * @param servId Id do serviço.
  * @param origin estação de origem do segmento.
  * @param dest estação de destino do segmento.
  *
  * @return Collection retorna uma lista de segmentos.
  */
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
  


  /**
  * Retorna todos os itinerarios existentes.
  *
  * @return out String com todos os itinerários existentes.
  */
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



  /**
  * Retorna todos os itinerarios existentes com um dado id de um passageiro.
  *
  * @param passengerId Id de um passageiro.
  *
  * @return out String com todos os itinerários existentes de um passageiro.
  *
  * @throws NoSuchPassengerIdException Id de passaageiro inválido.
  */
  protected String showItinerary(int passengerId) throws NoSuchPassengerIdException {
    Passenger pass = this.getPassengerById(passengerId);
    String out = new String();
    if (pass.getNumItins() != 0) {
      out += "== Passageiro " + pass.getId() + ": " + pass.getName() + " ==";
      out += pass.getItineraries();
    }
    return out;
  }


  /**
  * Retorna todos as propostas de itinerarios existentes ordenados por tempos.
  *
  * @return out String com todas as propostas de itinerários existentes já ordenadas.
  */

  public String tempItinToString() {
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

    boolean found = false;
    String out = new String();
    for (Itinerary i : _tempItin) {
      out+="\n" + i.toString() + "\n";
      found = true;
    }

    if (found)
      return out.substring(0, out.length()-1); //to eliminate the ending \n
    else
      return "";
  }


  /**
  * Associa uma proposta de itinerário dada a um passageiro.
  *
  * @param passengerId Id do passageiro.
  * @param itineraryNumber Número da proposta de itinerário.
  *
  * @throws NoSuchPassengerIdException Id do passageiro inválido. 
  * @throws NoSuchItineraryChoiceException Numero do itinerario inválido.
  */
  protected void commitItinerary(int passengerId, int itineraryNumber) throws NoSuchPassengerIdException, NoSuchItineraryChoiceException {
    if (itineraryNumber != 0) { 
      try {
        Itinerary i = _tempItin.get(itineraryNumber-1);
        this.getPassengerById(passengerId).addItinerary(i);
      } catch (IndexOutOfBoundsException iob) {
        throw new NoSuchItineraryChoiceException(passengerId, itineraryNumber);
      }  
    }
    _tempItin = new ArrayList<Itinerary>(); //clean the temporary buffer
  }

  /**
  * Cria novos itinerários.
  *
  * @param passengerId Id de um passageiro.
  * @param departureStation Estação de partida.
  * @param arrivalStation Estação de chegada.
  * @param departureDate Data de partida.
  * @param departureTime Hora de partida.
  *
  * @return tempItinToString() Função que vai tratar de imprimir os itinerários.
  *
  * @throws NoSuchStationNameException Nome da estação inválida.
  * @throws NoSuchPassengerIdException Id de passageiro inválido.
  * @throws BadTimeSpecificationException Hora inválida.
  * @throws BadDateSpecificationException Data inválida.
  */
  protected String searchItineraries(int passengerId, String departureStation, String arrivalStation, String departureDate,
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

                                        //passaria a chamar getIndirectItineraries
    _tempItin = new ArrayList<Itinerary>(getDirectItineraries(passengerId, dep, arr, depDate, depTime));

    return tempItinToString(); 
  }

  /**
  * Cria novos itinerários diretos.
  *
  * @param passengerId Id de um passageiro.
  * @param dep Estação de partida.
  * @param arr Estação de chegada.
  * @param depDate Data de partida.
  * @param depTime Hora de partida.
  *
  * @return Collection Collection com os itinerários criados.
  */
  public Collection<Itinerary> getDirectItineraries(int passengerId, Station dep, Station arr, LocalDate depDate,
                                              LocalTime depTime)  {
    List<Itinerary> tempItins = new ArrayList<Itinerary>(); //reset the temporary buffer

    List<Service> depServs = new ArrayList<Service>(getServicesPassing(dep));
    List<Service> arrServs = new ArrayList<Service>(getServicesPassing(arr));
    depServs.retainAll(arrServs); //maintain in depServs only the services that are suitable for a direct itinerary

    List<Segment> segs;
    int num = 1;
    for (Service serv : depServs) {
      if (serv.getTrainStop(dep).getTime().isBefore(serv.getTrainStop(arr).getTime())) { //check the direction of the train
        segs = new ArrayList<Segment>(this.getSegmentsBetween(serv.getId(), dep, arr));
        if (segs.get(0).getOrigin().getTime().isAfter(depTime)) { //consider only itineraries that depart after the departureTime 
          tempItins.add(new Itinerary(segs, depDate, passengerId, num++));
        }
      }
    }
   return Collections.unmodifiableCollection(tempItins);
  }

  /**
  * Cria novos itinerários indiretos.
  *
  * @param passengerId Id de um passageiro.
  * @param dep Estação de partida.
  * @param arr Estação de chegada.
  * @param depDate Data de partida.
  * @param depTime Hora de partida.
  *
  * @return Collection Collection com os itinerário criados. (se funcionasse lel)
  */
  protected Collection<Itinerary> getIndirectItineraries(int passengerId, Station dep, Station arr, LocalDate depDate, LocalTime depTime) {
    List<Service> filtServs = new ArrayList<Service>();
    for (Service serv : _servMap.values()) {
      if (serv.passesStation(dep)) 
        filtServs.add(serv);
    }
    //Em filtServs estao todos os servicos que passam na partida

    for (Service serv : filtServs) {
      getItineraryRec(serv, dep, arr, new ArrayList<Service>(), new ArrayList<Station>());
    }
    return null;
  }

  protected Itinerary getItineraryRec(Service serv, Station dep, Station arr, Collection<Service> servs, Collection<Station>stats) {
    Itinerary itin = new Itinerary();
    if (!(servs.contains(serv))) {
      if (itin.getDestStation() == arr)
        return itin;
    }
    return null;
  }
}

