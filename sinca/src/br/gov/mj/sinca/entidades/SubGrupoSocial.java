package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sub_grupo_social database table.
 * 
 */
@Entity
@Table(name="sub_grupo_social")
@NamedQuery(name="SubGrupoSocial.findAll", query="SELECT s FROM SubGrupoSocial s")
public class SubGrupoSocial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_SUB_GRUPO_SOCIAL", unique=true, nullable=false)
	private Integer idSubGrupoSocial;

	@Column(name="DESC_SUB_GRUPO_SOCIAL", length=500)
	private String descSubGrupoSocial;

	//bi-directional many-to-one association to Processo
	@OneToMany(mappedBy="subGrupoSocial")
	private List<Processo> processos;

	public SubGrupoSocial() {
	}
	

	public SubGrupoSocial(Integer idSubGrupoSocial) {
		super();
		this.idSubGrupoSocial = idSubGrupoSocial;
	}


	public Integer getIdSubGrupoSocial() {
		return this.idSubGrupoSocial;
	}

	public void setIdSubGrupoSocial(Integer idSubGrupoSocial) {
		this.idSubGrupoSocial = idSubGrupoSocial;
	}

	public String getDescSubGrupoSocial() {
		return this.descSubGrupoSocial;
	}

	public void setDescSubGrupoSocial(String descSubGrupoSocial) {
		this.descSubGrupoSocial = descSubGrupoSocial;
	}

	public List<Processo> getProcessos() {
		return this.processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

	public Processo addProcesso(Processo processo) {
		getProcessos().add(processo);
		processo.setSubGrupoSocial(this);

		return processo;
	}

	public Processo removeProcesso(Processo processo) {
		getProcessos().remove(processo);
		processo.setSubGrupoSocial(null);

		return processo;
	}

}