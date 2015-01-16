package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the documento_pessoa database table.
 * 
 */
@Entity
@Table(name="documento_pessoa")
@NamedQuery(name="DocumentoPessoa.findAll", query="SELECT d FROM DocumentoPessoa d")
public class DocumentoPessoa implements Serializable {
    
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_DOCUMENTO", unique=true, nullable=false)
	private Integer idDocumento;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_EMISSAO")
	private Date dataEmissao;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_VALIDADE")
	private Date dataValidade;

	@Column(name="DESC_ORGAO", length=200)
	private String descOrgao;

	@ManyToOne
	@JoinColumn(name="ID_PESSOA")
	private Pessoa pessoa;

	@ManyToOne
	@JoinColumn(name="ID_TIPO_DOCUMENTO")
	private TipoDocumento tipoDocumento;

	@Column(name="NUMERO_DOCUMENTO", length=100)
	private String numeroDocumento;

	@Column(length=100)
	private String observacao;

	@Column(name="PAIS_ORIGEM", length=100)
	private String paisOrigem;

	@Column(length=100)
	private String secao;

	@Column(name="ZONA_ELEITORAL", length=100)
	private String zonaEleitoral;

	public DocumentoPessoa() {
	}

	public Integer getIdDocumento() {
	    return idDocumento;
	}

	public void setIdDocumento(Integer idDocumento) {
	    this.idDocumento = idDocumento;
	}

	public Date getDataEmissao() {
	    return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
	    this.dataEmissao = dataEmissao;
	}

	public Date getDataValidade() {
	    return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
	    this.dataValidade = dataValidade;
	}

	public String getDescOrgao() {
	    return descOrgao;
	}

	public void setDescOrgao(String descOrgao) {
	    this.descOrgao = descOrgao;
	}

	public Pessoa getPessoa() {
	    return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
	    this.pessoa = pessoa;
	}

	public TipoDocumento getTipoDocumento() {
	    return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
	    this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
	    return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
	    this.numeroDocumento = numeroDocumento;
	}

	public String getObservacao() {
	    return observacao;
	}

	public void setObservacao(String observacao) {
	    this.observacao = observacao;
	}

	public String getPaisOrigem() {
	    return paisOrigem;
	}

	public void setPaisOrigem(String paisOrigem) {
	    this.paisOrigem = paisOrigem;
	}

	public String getSecao() {
	    return secao;
	}

	public void setSecao(String secao) {
	    this.secao = secao;
	}

	public String getZonaEleitoral() {
	    return zonaEleitoral;
	}

	public void setZonaEleitoral(String zonaEleitoral) {
	    this.zonaEleitoral = zonaEleitoral;
	}
}