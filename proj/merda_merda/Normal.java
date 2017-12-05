package mmt.core;


public class Normal implements Category {
	
	public void doAction(Passenger passenger) {
    System.out.println("Player is in start state");
    passenger.setState(this);	
  }

  public int getDiscount() {
  	return 0.1;
  }
}
