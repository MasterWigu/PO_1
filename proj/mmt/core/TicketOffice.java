package mmt.core;

/**
* Classe que controla a companhia de comboios.
*
* @author Henrique Fernandes 87662 
* @author Miguel Oliveira    87689
*
* @version 1.0
*/

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import mmt.core.exceptions.BadDateSpecificationException;
import mmt.core.exceptions.BadTimeSpecificationException;
import mmt.core.exceptions.ImportFileException;
import mmt.core.exceptions.MissingFileAssociationException;
import mmt.core.exceptions.NoSuchPassengerIdException;
import mmt.core.exceptions.NoSuchServiceIdException;
import mmt.core.exceptions.NoSuchStationNameException;
import mmt.core.exceptions.NoSuchItineraryChoiceException;
import mmt.core.exceptions.NonUniquePassengerNameException;

import java.util.Collection;

/**
 * Façade for handling persistence and other functions.
 */
public class TicketOffice {

  /** The object doing most of the actual work. */
  private TrainCompany _trainCompany=new TrainCompany();

  /** Nome que o ficheiro toma. */
  private String _saveFile = "";

  /** Apaga todos os passageiros e itinerarios registados. */
  public void reset() {
    _trainCompany.reset();
  }

  /**
  * Guarda o estado de todo o programa.
  *
  * @param filename nome do ficheiro.
  *
  * @throws FileNotFoundException, IOException Ficheiro nao encontrado, Erro no output.
  */ 
  public void save(String filename) throws FileNotFoundException, IOException {
 
    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
    out.writeObject(_trainCompany);
    out.close();
  }

  /**
  * Permite recuperar o estado anterior de um programa a partir de serialização.
  *
  * @param filename nome do ficheiro.
  *
  * @throws FileNotFoundException, IOException, ClassNotFoundException Ficheiro nao encontrado, Erro no output, Classe não encontrada.
  */ 
  public void load(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
    ObjectInputStream inob = new ObjectInputStream(new FileInputStream(filename));
    _trainCompany = (TrainCompany)inob.readObject();
  }

  /**
  * Importa um ficheiro de dados de serviços, passageiros e itinerarios.
  *
  * @param filename nome do ficheiro.
  *
  * @throws ImportFileException Ficheiro inválido para importar.
  */ 
  public void importFile(String datafile) throws ImportFileException {
    NewParser parse = new NewParser();
    _trainCompany = parse.parseFile(datafile);
  }

  /**
  * Retorna o nome do ficheiro de save utilizado.
  *
  * @return _saveFile nome do ficheiro de save.
  */ 
  public String getSaveFile() {
    return _saveFile;
  }

  /**
  * Permite atribuir um nome ao ficheiro de save.
  *
  * @param name nome do ficheiro a atribuir.
  */ 
  public void setSaveFile(String name) {
    _saveFile = name;
  }

  /**
  * Permite registar um novo passageiro.
  *
  * @param name nome do passageiro.
  */ 
  public void registerPassenger(String name){
    _trainCompany.registerPassenger(name);
  }

  /**
  * Permite alterar o nome de um passageiro.
  *
  * @param name nome do passageiro a alterar.
  *
  * @throws NoSuchPassengerIdException Id do passageiro inválido.
  */ 
  public void changePassengerName(int id, String newName) throws NoSuchPassengerIdException {
    _trainCompany.changePassengerName(id, newName);
  }

  /**
  * Retorna uma collection com todos os passageiros.
  *
  * @return Collection Collection com todos os passageiros.
  */ 
  public String getPassengers() {
    return _trainCompany.getPassengers();
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
  public String getPassengerById(int id) throws NoSuchPassengerIdException {
    return _trainCompany.getPassengerStringById(id);
  }

  /**
  * Retorna uma collection com todos os serviços.
  *
  * @return Collection Collection com todos os serviços.
  */ 
  public String getAllServices() {
    return _trainCompany.getServices();
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
  public String getServiceById(int id) throws NoSuchServiceIdException {
    return _trainCompany.getServiceStringById(id);
  }

  /**
  * Retorna uma collection com os serviços com partida numa dada estação.
  *
  * @param station Estação do serviço desejado.
  *
  * @return Collection Collection com os serviços com a estação dada.
  *
  * @throws NoSuchStationName Nome da estação inválida.
  */
  public String getServicesDeparting(String stationName) throws NoSuchStationNameException {
    return _trainCompany.getServicesDeparting(stationName);
  }

  /**
  * Retorna uma collection com os serviços com chegada a uma dada estação.
  *
  * @param station Estação do serviço desejado.
  *
  * @return Collection Collection com os serviços com a estação dada.
  *
  * @throws NoSuchStationName Nome da estação inválida.
  */
  public String getServicesArriving(String stationName) throws NoSuchStationNameException {
    return _trainCompany.getServicesArriving(stationName);
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
  public String searchItineraries(int passengerId, String departureStation, String arrivalStation, String departureDate,
                                              String departureTime) throws NoSuchStationNameException, NoSuchPassengerIdException, 
                                              BadTimeSpecificationException, BadDateSpecificationException, BadTimeSpecificationException {

    return _trainCompany.searchItineraries(passengerId, departureStation, arrivalStation, departureDate, departureTime);
  }

  /**
  * Associa o itenerário dado a um passageiro.
  *
  * @param passengerId Id do passageiro.
  * @param itineraryNumber Número do itinerário.
  *
  * @throws NoSuchPassengerIdException Id do passageiro inválido. 
  * @throws NoSuchItineraryChoiceException Numero do itinerario inválido.
  */
  public void commitItinerary(int passengerId, int itineraryNumber) throws NoSuchPassengerIdException, NoSuchItineraryChoiceException  {
    _trainCompany.commitItinerary(passengerId, itineraryNumber);
  }

  /**
  * Retorna todos os itinerarios existentes.
  *
  * @return out String com todos os itinerários existentes.
  */
  public String showItineraries() {
    return _trainCompany.showItineraries();
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
  public String showItinerary(int passengerId) throws NoSuchPassengerIdException {
    return _trainCompany.showItinerary(passengerId);
  }
}