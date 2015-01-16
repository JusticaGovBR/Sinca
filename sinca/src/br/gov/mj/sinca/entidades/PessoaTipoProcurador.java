package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the pessoa_tipo_procurador database table.
 * 
 */
@Entity
@Table(name="pessoa_tipo_procurador")
@NamedQuery(name="PessoaTipoProcurador.findAll", query="SELECT p FROM PessoaTipoProcurador p")
public class PessoaTipoProcurador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PESSOA_TIPO_PROCURADOR")
	private Long idPessoaTipoProcurador;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_COMPROVANTE")
	private Date dataComprovante;

	@Column(name="DATA_HORA_INCLUSAO")
	private Timestamp dataHoraInclusao;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_PROCURACAO")
	private Date dataProcuracao;

	@Column(name="ID_PESSOA")
	private Pessoa pessoa;

	@Column(name="ID_TIPO_PROCURADOR")
	private Integer idTipoProcurador;

	@Column(name="OBSERVACAO_COMPROVANTE")
	private String observacaoComprovante;

	public PessoaTipoProcurador() {
	}

	public Long getIdPessoaTipoProcurador() {
	    return idPessoaTipoProcurador;
	}

	public void setIdPessoaTipoProcurador(Long idPessoaTipoProcurador) {
	    this.idPessoaTipoProcurador = idPessoaTipoProcurador;
	}

	public Date getDataComprovante() {
	    return dataComprovante;
	}

	public void setDataComprovante(Date dataComprovante) {
	    this.dataComprovante = dataComprovante;
	}

	public Timestamp getDataHoraInclusao() {
	    return dataHoraInclusao;
	}

	public void setDataHoraInclusao(Timestamp dataHoraInclusao) {
	    this.dataHoraInclusao = dataHoraInclusao;
	}

	public Date getDataProcuracao() {
	    return dataProcuracao;
	}

	public void setDataProcuracao(Date dataProcuracao) {
	    this.dataProcuracao = dataProcuracao;
	}

	public Pessoa getPessoa() {
	    return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
	    this.pessoa = pessoa;
	}

	public Integer getIdTipoProcurador() {
	    return idTipoProcurador;
	}

	public void setIdTipoProcurador(Integer idTipoProcurador) {
	    this.idTipoProcurador = idTipoProcurador;
	}

	public String getObservacaoComprovante() {
	    return observacaoComprovante;
	}

	public void setObservacaoComprovante(String observacaoComprovante) {
	    this.observacaoComprovante = observacaoComprovante;
	}


}