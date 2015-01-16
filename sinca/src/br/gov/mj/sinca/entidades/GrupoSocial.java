package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the grupo_social database table.
 * 
 */
@Entity
@Table(name = "grupo_social")
@NamedQuery(name = "GrupoSocial.findAll", query = "SELECT g FROM GrupoSocial g")
public class GrupoSocial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_GRUPO_SOCIAL", unique = true, nullable = false)
	private Integer idGrupoSocial;

	@Column(name = "DESC_GRUPO_SOCIAL", length = 500)
	private String descGrupoSocial;

	// bi-directional many-to-one association to Processo
	@OneToMany(mappedBy = "grupoSocial")
	private List<Processo> processos;

	public GrupoSocial(int idGrupoSocial) {
		super();
		this.idGrupoSocial = idGrupoSocial;
	}

	public GrupoSocial() {
	}

	public Integer getIdGrupoSocial() {
		return this.idGrupoSocial;
	}

	public void setIdGrupoSocial(Integer idGrupoSocial) {
		this.idGrupoSocial = idGrupoSocial;
	}

	public String getDescGrupoSocial() {
		return this.descGrupoSocial;
	}

	public void setDescGrupoSocial(String descGrupoSocial) {
		this.descGrupoSocial = descGrupoSocial;
	}

	public List<Processo> getProcessos() {
		return this.processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

	public Processo addProcesso(Processo processo) {
		getProcessos().add(processo);
		processo.setGrupoSocial(this);

		return processo;
	}

	public Processo removeProcesso(Processo processo) {
		getProcessos().remove(processo);
		processo.setGrupoSocial(null);

		return processo;
	}

}