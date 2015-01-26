package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the lote_processo database table.
 * 
 */
@Entity
@Table(name="lote_processo")
@NamedQuery(name="LoteProcesso.findAll", query="SELECT l FROM LoteProcesso l")
public class LoteProcesso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_LOTE")
	private Integer idLote;

	private String descricao;

	//bi-directional many-to-one association to Processo
	@OneToMany(mappedBy="loteProcesso")
	private List<Processo> processos;

	public LoteProcesso() {
	}

	public Integer getIdLote() {
		return this.idLote;
	}

	public void setIdLote(Integer idLote) {
		this.idLote = idLote;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Processo> getProcessos() {
		return this.processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

	public Processo addProcesso(Processo processo) {
		getProcessos().add(processo);
		processo.setLoteProcesso(this);

		return processo;
	}

	public Processo removeProcesso(Processo processo) {
		getProcessos().remove(processo);
		processo.setLoteProcesso(null);

		return processo;
	}

}