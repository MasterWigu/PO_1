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
  public Collection<Passenger> getPassengers() {
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
  public Passenger getPassengerById(int id) throws NoSuchPassengerIdException {
    return _trainCompany.getPassengerById(id);
  }

  /**
  * Retorna uma collection com todos os serviços.
  *
  * @return Collection Collection com todos os serviços.
  */ 
  public Collection<Service> getServices() {
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
  public Service getServiceById(int id) throws NoSuchServiceIdException {
    return _trainCompany.getServiceById(id);
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
  public Collection<Service> getServicesDeparting(String stationName) throws NoSuchStationNameException {
    Station s = _trainCompany.getStation(stationName);
    return _trainCompany.getServicesDeparting(s);
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
  public Collection<Service> getServicesArriving(String stationName) throws NoSuchStationNameException {
    Station s = _trainCompany.getStation(stationName);
    return _trainCompany.getServicesArriving(s);
  }
}