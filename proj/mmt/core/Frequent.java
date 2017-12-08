package mmt.core;

public class Frequent implements Category, java.io.Serializable {

	public void setCategory(Passenger passenger) {
    passenger.setCategory(this);	
  }	

  public double getDiscount() {
  	return 0.15;
  }

  public String toString(){
      return "FREQUENTE";
   }
}
