package br.gov.mj.sinca.enums;

public enum ProtocoloExterno {

	SIM(1), NAO(2);

	private final int valor;

	ProtocoloExterno(int valorOpcao) {
		valor = valorOpcao;
	}

	public int getValor() {
		return valor;
	}

}
