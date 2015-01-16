package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the procedencia database table.
 * 
 */
@Entity
@Table(name="procedencia")
@NamedQuery(name="Procedencia.findAll", query="SELECT p FROM Procedencia p")
public class Procedencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PROCEDENCIA", unique=true, nullable=false)
	private Integer idProcedencia;

	@Column(name="DESC_PROCEDENCIA", length=500)
	private String descProcedencia;

	//bi-directional many-to-one association to DadosAdicionais
	@OneToMany(mappedBy="procedencia")
	private List<DadosAdicionais> dadosAdicionais;

	public Procedencia() {
	}

	public Integer getIdProcedencia() {
		return this.idProcedencia;
	}

	public void setIdProcedencia(Integer idProcedencia) {
		this.idProcedencia = idProcedencia;
	}

	public String getDescProcedencia() {
		return this.descProcedencia;
	}

	public void setDescProcedencia(String descProcedencia) {
		this.descProcedencia = descProcedencia;
	}

	public List<DadosAdicionais> getDadosAdicionais() {
		return this.dadosAdicionais;
	}

	public void setDadosAdicionais(List<DadosAdicionais> dadosAdicionais) {
		this.dadosAdicionais = dadosAdicionais;
	}

	public DadosAdicionais addDadosAdicionai(DadosAdicionais dadosAdicionai) {
		getDadosAdicionais().add(dadosAdicionai);
		dadosAdicionai.setProcedencia(this);

		return dadosAdicionai;
	}

	public DadosAdicionais removeDadosAdicionai(DadosAdicionais dadosAdicionai) {
		getDadosAdicionais().remove(dadosAdicionai);
		dadosAdicionai.setProcedencia(null);

		return dadosAdicionai;
	}

}