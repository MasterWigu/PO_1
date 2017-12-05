package mmt.core;

public class Special implements Category {
	
	public void doAction(Passenger passenger) {
    System.out.println("Player is in start state");
    passenger.setState(this);	
  }

  public int getDiscount() {
  	return 0.3;
  }
}
