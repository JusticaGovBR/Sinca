package br.gov.mj.sinca.pojo;

import java.io.Serializable;

public class UfPojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7935573461855462034L;

	private String uf;
	private String descUf;

	public UfPojo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UfPojo(String uf, String descUf) {
		super();
		this.uf = uf;
		this.descUf = descUf;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getDescUf() {
		return descUf;
	}

	public void setDescUf(String descUf) {
		this.descUf = descUf;
	}

}
