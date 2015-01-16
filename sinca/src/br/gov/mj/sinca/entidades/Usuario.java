package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usario database table.
 * 
 */
@Entity
@Table(name="usuario")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_USUARIO", unique=true, nullable=false)
	private Integer idUsuario;

	@Column(name="DESC_USUARIO", length=500)
	private String descUsuario;

	@Column(name="SGL_USUARIO", length=100)
	private String sglUsuario;

	//bi-directional many-to-one association to Processo
	@OneToMany(mappedBy="usario",fetch=FetchType.EAGER)
	private List<Processo> processos;

	public Usuario() {
	}

	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDescUsuario() {
		return this.descUsuario;
	}

	public void setDescUsuario(String descUsuario) {
		this.descUsuario = descUsuario;
	}

	public String getSglUsuario() {
		return this.sglUsuario;
	}

	public void setSglUsuario(String sglUsuario) {
		this.sglUsuario = sglUsuario;
	}

	public List<Processo> getProcessos() {
		return this.processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

	public Processo addProcesso(Processo processo) {
		getProcessos().add(processo);
		processo.setUsario(this);

		return processo;
	}

	public Processo removeProcesso(Processo processo) {
		getProcessos().remove(processo);
		processo.setUsario(null);

		return processo;
	}

}