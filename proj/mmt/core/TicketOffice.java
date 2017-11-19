package mmt.core;

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

import java.util.*;
import mmt.core.Passenger;

//FIXME import other classes if necessary

/**
 * Façade for handling persistence and other functions.
 */
public class TicketOffice {

  /** The object doing most of the actual work. */
  private TrainCompany _trainCompany=new TrainCompany();
  //FIXME define other fields

  public void reset() {
    _trainCompany.reset();
  }

  public void save(String filename) throws FileNotFoundException, IOException/*FIXME add thrown exceptions*/ {
 
    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
    out.writeObject(_trainCompany);
    out.close();
  }

  public void load(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
    ObjectInputStream inob = new ObjectInputStream(new FileInputStream(filename));
    _trainCompany = (TrainCompany)inob.readObject();
  }

  public void importFile(String datafile) throws ImportFileException {
    NewParser parse = new NewParser();
    _trainCompany = parse.parseFile(datafile);
  }

  /*
  public Itinerary searchItineraries(int passengerId, String departureStation, String arrivalStation, String departureDate, String departureTime) { //FIXME define thrown exceptions 
    //FIXME implement method
  }


  public Itinerary commitItinerary(int passengerId, int itineraryNumber) { //FIXME define thrown exceptions 
    //FIXME implement method
  }

*/
  //FIXME add methods for passenger registration and passenger name update
  public void registerPassenger(String name){
    _trainCompany.registerPassenger(name);
  }

  public void changePassengerName(int id, String newName){
    _trainCompany.changePassengerName(id, newName);
  }

/*
  public String getServicesArriving(Station station) {
    return  _trainCompany.getServicesArriving(station);
  }
*/
  public Collection<Service> getServicesDeparting(String stationName) {
    Station s = _trainCompany.getStation(stationName);
    return _trainCompany.getServicesDeparting(s);
  }

  public Service getServiceById(int id) {
    return _trainCompany.getServiceById(id);
  }

  public Collection<Service> getServices() {
    return _trainCompany.getServices();
  }

  public Collection<Passenger> getPassengers() {
    return _trainCompany.getPassengers();
  }

  public Passenger getPassengerById(int id) {
    return _trainCompany.getPassengerById(id);
  }

  //FIXME add other functions if necessary

}
