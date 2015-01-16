package br.gov.mj.sinca.enums;

public enum TipoDadosAdicionais {

	DOCUMENTO(1), PROCESSO(2);

	private final int valor;

	TipoDadosAdicionais(int valorOpcao) {
		valor = valorOpcao;
	}

	public int getValor() {
		return valor;
	}

}
