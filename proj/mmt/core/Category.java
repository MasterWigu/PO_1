package mmt.core;

public abstract class Category implements java.io.Serializable{

	private double _discount;
	private String _tipo;


	public Category (int discount, String tipo) {
		_discount=discount;
		_tipo = tipo;
	}
	
}