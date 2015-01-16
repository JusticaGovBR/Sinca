package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sub_grupo_processual database table.
 * 
 */
@Entity
@Table(name="sub_grupo_processual")
@NamedQuery(name="SubGrupoProcessual.findAll", query="SELECT s FROM SubGrupoProcessual s")
public class SubGrupoProcessual implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_SUB_GRUPO_PROCESSUAL", unique=true, nullable=false)
	private Integer idSubGrupoProcessual;

	@Column(name="DESC_SUB_GRUPO_PROCESSUAL", length=500)
	private String descSubGrupoProcessual;

	//bi-directional many-to-one association to Processo
	@OneToMany(mappedBy="subGrupoProcessual")
	private List<Processo> processos;

	public SubGrupoProcessual() {
	}
	
	public SubGrupoProcessual(int idSubGrupoProcessual) {
		super();
		this.idSubGrupoProcessual = idSubGrupoProcessual;
	}
	
	public SubGrupoProcessual(String descSubGrupoProcessual) {
		super();
		this.descSubGrupoProcessual = descSubGrupoProcessual;
	}


	public Integer getIdSubGrupoProcessual() {
		return this.idSubGrupoProcessual;
	}

	public void setIdSubGrupoProcessual(Integer idSubGrupoProcessual) {
		this.idSubGrupoProcessual = idSubGrupoProcessual;
	}

	public String getDescSubGrupoProcessual() {
		return this.descSubGrupoProcessual;
	}

	public void setDescSubGrupoProcessual(String descSubGrupoProcessual) {
		this.descSubGrupoProcessual = descSubGrupoProcessual;
	}

	public List<Processo> getProcessos() {
		return this.processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

	public Processo addProcesso(Processo processo) {
		getProcessos().add(processo);
		processo.setSubGrupoProcessual(this);

		return processo;
	}

	public Processo removeProcesso(Processo processo) {
		getProcessos().remove(processo);
		processo.setSubGrupoProcessual(null);

		return processo;
	}

}