package br.gov.mj.sinca.entidades;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the sub_status_processo database table.
 * 
 */
@Entity
@Table(name="sub_status_processo")
@NamedQuery(name="SubStatusProcesso.findAll", query="SELECT s FROM SubStatusProcesso s")
public class SubStatusProcesso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_SUB_STATUS_PROCESSO", unique=true, nullable=false)
	private Integer idSubStatusProcesso;

	@Column(name="DESC_SUB_STATUS_PROCESSO", length=500)
	private String descSubStatusProcesso;

	//bi-directional many-to-one association to Processo
	@OneToMany(mappedBy="subStatusProcesso")
	private List<Processo> processos;
	

	public SubStatusProcesso() {
		super();
	}

	public SubStatusProcesso(Integer idSubStatusProcesso) {
		this.idSubStatusProcesso = idSubStatusProcesso;
	}

	public Integer getIdSubStatusProcesso() {
		return this.idSubStatusProcesso;
	}

	public void setIdSubStatusProcesso(Integer idSubStatusProcesso) {
		this.idSubStatusProcesso = idSubStatusProcesso;
	}

	public String getDescSubStatusProcesso() {
		return this.descSubStatusProcesso;
	}

	public void setDescSubStatusProcesso(String descSubStatusProcesso) {
		this.descSubStatusProcesso = descSubStatusProcesso;
	}

	public List<Processo> getProcessos() {
		return this.processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

	public Processo addProcesso(Processo processo) {
		getProcessos().add(processo);
		processo.setSubStatusProcesso(this);

		return processo;
	}

	public Processo removeProcesso(Processo processo) {
		getProcessos().remove(processo);
		processo.setSubStatusProcesso(null);

		return processo;
	}

}