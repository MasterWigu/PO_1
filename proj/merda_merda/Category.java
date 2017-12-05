package mmt.core;

public interface Category implements java.io.Serializable {

	public void doAction(Passenger passenger);
	
	public int getDiscount();
}