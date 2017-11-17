package mmt.core;


import mmt.core.Estacao;


public class Itenerario {
	private double _custoTotal;
	private int _numeroOrdem;
	private LocalDate _date;
	private Estacao _estacaoO;
	private Estacao _estacaoD;

	public double getCustoTotal() {
		return _custoTotal;
	}

	public int getNumeroOrdem() {
		return _numeroOrdem;
	}

	public LocalDate getDate() {
		return _date;
	}

	public Itenerario(LocalDate date, Estacao estacaoO, Estacao estacaoD) {
		_date = date;
		_estacaoO = estacaoO;
		_estacaoD= estacaoD;
	}
}