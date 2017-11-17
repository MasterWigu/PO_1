package mmt.core;

import mmt.core.Estacao;

public class Segmento {
	private Service _service;
	private Estacao _estacao1;
	private Estacao _estacao2;

	public Service getService() {
		return _service;
	}

	public Estacao getEstacao1() {
		return _estacao1;
	}

	public Estacao getEstacao2() {
		return _estacao2;
	}
}