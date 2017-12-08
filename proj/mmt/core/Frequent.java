package mmt.core;

public class Frequent implements Category, java.io.Serializable {

	protected void setCategory(Passenger passenger) {
    passenger.setCategory(this);	
  }	

  protected double getDiscount() {
  	return 0.15;
  }

  public String toString(){
      return "FREQUENTE";
   }
}
