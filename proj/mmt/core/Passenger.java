package mmt.core;

import mmt.*;


public class Passenger implements java.io.Serializable{

	private double _custoIndividual;
	private int[] _gastos;
	private String _name;
	private int _id;
	private String _tipo;

	public double getCustoIndividual() {
		return _custoIndividual;
	}

	public int getGastos() {
		int soma=0;
		for(int i=0; i<10;i++){
			soma +=_gastos[i];
		}
		return soma;
	}

	public String getName() {
		return _name;
	}

	public int getId() {
		return _id;
	}

	public String getTipo() {
		return _tipo;
	}

	public void setName(String name) {
		_name = name;
	}

	public Passenger(String name, int id) {
		_name = name;
		_gastos = new int[10];
		_tipo = "Normal";
		_id = id;
	}


  public String showPassenger() {
    return "" + _id + "|" + _name + "|" + _tipo;
  }
}