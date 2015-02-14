package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the doenca_pessoa database table.
 * 
 */
@Entity
@Table(name="doenca_pessoa")
@NamedQuery(name="DoencaPessoa.findAll", query="SELECT d FROM DoencaPessoa d")
public class DoencaPessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_DOENCA_PESSOA")
	private Long idDoencaPessoa;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_ENTREGA_COMPROVANTE")
	private Date dataEntregaComprovante;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_FIM")
	private Date dataFim;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_INICIO")
	private Date dataInicio;

	@Column(name="OBSERVACAO_COMPROVANTE")
	private String observacaoComprovante;

	//bi-directional many-to-one association to Doenca
	@ManyToOne
	@JoinColumn(name="ID_DOENCA")
	private Doenca doenca;

	//bi-directional many-to-one association to Pessoa
	@ManyToOne
	@JoinColumn(name="ID_PESSOA")
	private PessoaFisica pessoa;

	public DoencaPessoa() {
	}

	public Long getIdDoencaPessoa() {
		return this.idDoencaPessoa;
	}

	public void setIdDoencaPessoa(Long idDoencaPessoa) {
		this.idDoencaPessoa = idDoencaPessoa;
	}

	public Date getDataEntregaComprovante() {
		return this.dataEntregaComprovante;
	}

	public void setDataEntregaComprovante(Date dataEntregaComprovante) {
		this.dataEntregaComprovante = dataEntregaComprovante;
	}

	public Date getDataFim() {
		return this.dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getDataInicio() {
		return this.dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getObservacaoComprovante() {
		return this.observacaoComprovante;
	}

	public void setObservacaoComprovante(String observacaoComprovante) {
		this.observacaoComprovante = observacaoComprovante;
	}

	public Doenca getDoenca() {
		return this.doenca;
	}

	public void setDoenca(Doenca doenca) {
		this.doenca = doenca;
	}

	public PessoaFisica getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(PessoaFisica pessoa) {
		this.pessoa = pessoa;
	}

}