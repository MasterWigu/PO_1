package mmt.core;

//imports


public class Passenger {

	private double _custoIndividual;
	private int[10] _gastos;
	private String _nome;
	private int _id;
	private String _tipo;

	public double getCustoIndividual() {
		return _custoIndividual;
	}

	public int getGastos() {
		soma=0;
		for(int i=0; i<10;i++){
			soma +=_gastos[i];
		}
	}

	public String getNome() {
		return _nome;
	}

	public int getId() {
		return _id;
	}

	public String getTipo() {
		return _tipo;
	}

	public void setNome(String nome) {
		_nome = nome;
	}

	public Passenger(String nome, int id) {
		_nome = nome;
		_id = id;
	}
}