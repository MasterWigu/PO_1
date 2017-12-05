package mmt.core;


public class Normal implements Category {
	
	public void setCategory(Passenger passenger) {
    passenger.setCategory(this);	
  }	

  public double getDiscount() {
  	return 0.0;
  }

  public String toString(){
      return "NORMAL";
   }
}
