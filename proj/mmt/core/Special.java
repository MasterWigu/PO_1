package mmt.core;

public class Special implements Category,  java.io.Serializable {
	
	protected void setCategory(Passenger passenger) {
    passenger.setCategory(this);	
  }	

  protected double getDiscount() {
  	return 0.5;
  }

  public String toString(){
      return "ESPECIAL";
   }
}
