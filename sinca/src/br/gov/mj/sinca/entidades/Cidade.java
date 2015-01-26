package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cidade database table.
 * 
 */
@Entity
@NamedQuery(name="Cidade.findAll", query="SELECT c FROM Cidade c")
public class Cidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cidadeId;

	private byte capital;

	private String nome;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="EstadoId")
	private Estado estado;

	public Cidade() {
	}

	public Integer getCidadeId() {
		return this.cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}

	public byte getCapital() {
		return this.capital;
	}

	public void setCapital(byte capital) {
		this.capital = capital;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}