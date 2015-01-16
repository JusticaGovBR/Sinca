package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the status_processo database table.
 * 
 */
@Entity
@Table(name="status_processo")
@NamedQuery(name="StatusProcesso.findAll", query="SELECT s FROM StatusProcesso s")
public class StatusProcesso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_STATUS_PROCESSO", unique=true, nullable=false)
	private Integer idStatusProcesso;

	@Column(name="DESC_STATUS_PROCESSO", length=500)
	private String descStatusProcesso;

	//bi-directional many-to-one association to Processo
	@OneToMany(mappedBy="statusProcesso")
	private List<Processo> processos;

	public StatusProcesso() {
	}

	
	
	public StatusProcesso(int idStatusProcesso) {
		super();
		this.idStatusProcesso = idStatusProcesso;
	}



	public Integer getIdStatusProcesso() {
		return this.idStatusProcesso;
	}

	public void setIdStatusProcesso(Integer idStatusProcesso) {
		this.idStatusProcesso = idStatusProcesso;
	}

	public String getDescStatusProcesso() {
		return this.descStatusProcesso;
	}

	public void setDescStatusProcesso(String descStatusProcesso) {
		this.descStatusProcesso = descStatusProcesso;
	}

	public List<Processo> getProcessos() {
		return this.processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

	public Processo addProcesso(Processo processo) {
		getProcessos().add(processo);
		processo.setStatusProcesso(this);

		return processo;
	}

	public Processo removeProcesso(Processo processo) {
		getProcessos().remove(processo);
		processo.setStatusProcesso(null);

		return processo;
	}

}