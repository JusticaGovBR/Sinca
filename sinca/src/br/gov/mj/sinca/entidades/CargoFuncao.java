package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cargo_funcao database table.
 * 
 */
@Entity
@Table(name="cargo_funcao")
@NamedQuery(name="CargoFuncao.findAll", query="SELECT c FROM CargoFuncao c")
public class CargoFuncao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_CARGO_FUNCAO", unique=true, nullable=false)
	private int idCargoFuncao;

	@Column(name="DESC_CARGO_FUNCAO", length=500)
	private String descCargoFuncao;

	//bi-directional many-to-one association to Processo
	@OneToMany(mappedBy="cargoFuncao")
	private List<Processo> processos;

	public CargoFuncao() {
	}

	
	public CargoFuncao(int idCargoFuncao) {
		super();
		this.idCargoFuncao = idCargoFuncao;
	}



	public int getIdCargoFuncao() {
		return this.idCargoFuncao;
	}

	public void setIdCargoFuncao(int idCargoFuncao) {
		this.idCargoFuncao = idCargoFuncao;
	}

	public String getDescCargoFuncao() {
		return this.descCargoFuncao;
	}

	public void setDescCargoFuncao(String descCargoFuncao) {
		this.descCargoFuncao = descCargoFuncao;
	}

	public List<Processo> getProcessos() {
		return this.processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

	public Processo addProcesso(Processo processo) {
		getProcessos().add(processo);
		processo.setCargoFuncao(this);

		return processo;
	}

	public Processo removeProcesso(Processo processo) {
		getProcessos().remove(processo);
		processo.setCargoFuncao(null);

		return processo;
	}

}