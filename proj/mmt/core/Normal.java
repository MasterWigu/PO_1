package mmt.core;


public class Normal implements Category, java.io.Serializable {
	
	protected void setCategory(Passenger passenger) {
    passenger.setCategory(this);	
  }	

  protected double getDiscount() {
  	return 0.0;
  }

  public String toString(){
      return "NORMAL";
   }
}
