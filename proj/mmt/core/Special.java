package mmt.core;

public class Special implements Category,  java.io.Serializable {
	
	public void setCategory(Passenger passenger) {
    passenger.setCategory(this);	
  }	

  public double getDiscount() {
  	return 0.5;
  }

  public String toString(){
      return "ESPECIAL";
   }
}
