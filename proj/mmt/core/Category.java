package mmt.core;

import mmt.*;


public abstract class Category {

	private double _discount;
	private String _tipo;


	public Category (int discount, String tipo) {
		_discount=discount;
		_tipo = tipo;
	}
	
}