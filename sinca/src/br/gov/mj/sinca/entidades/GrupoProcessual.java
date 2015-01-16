package br.gov.mj.sinca.entidades;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the grupo_processual database table.
 * 
 */
@Entity
@Table(name="grupo_processual")
@NamedQuery(name="GrupoProcessual.findAll", query="SELECT g FROM GrupoProcessual g")
public class GrupoProcessual implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_GRUPO_PROCESSUAL", unique=true, nullable=false)
	private Integer idGrupoProcessual;

	@Column(name="DESC_GRUPO_PROCESSUAL", length=500)
	private String descGrupoProcessual;

	//bi-directional many-to-one association to Processo
	@OneToMany(mappedBy="grupoProcessual")
	private List<Processo> processos;

	public GrupoProcessual() {
	}

	public GrupoProcessual(int idGrupoProcessual, String descGrupoProcessual,
			List<Processo> processos) {
		super();
		this.idGrupoProcessual = idGrupoProcessual;
		this.descGrupoProcessual = descGrupoProcessual;
		this.processos = processos;
	}

	public GrupoProcessual(String descGrupoProcessual) {
		super();
		this.descGrupoProcessual = descGrupoProcessual;
	}

	public GrupoProcessual(Integer idGrupoProcessual) {
		super();
		this.idGrupoProcessual = idGrupoProcessual;
	}



	public Integer getIdGrupoProcessual() {
		return this.idGrupoProcessual;
	}

	public void setIdGrupoProcessual(Integer idGrupoProcessual) {
		this.idGrupoProcessual = idGrupoProcessual;
	}

	public String getDescGrupoProcessual() {
		return this.descGrupoProcessual;
	}

	public void setDescGrupoProcessual(String descGrupoProcessual) {
		this.descGrupoProcessual = descGrupoProcessual;
	}

	public List<Processo> getProcessos() {
		return this.processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

	public Processo addProcesso(Processo processo) {
		getProcessos().add(processo);
		processo.setGrupoProcessual(this);

		return processo;
	}

	public Processo removeProcesso(Processo processo) {
		getProcessos().remove(processo);
		processo.setGrupoProcessual(null);

		return processo;
	}

}