package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


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
	@Column(name="ID_DOCUMENTO")
	private Integer idDocumento;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_EMISSAO")
	private Date dataEmissao;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_VALIDADE")
	private Date dataValidade;

	@Column(name="DESC_ORGAO")
	private String descOrgao;

	@Column(name="NUMERO_DOCUMENTO")
	private String numeroDocumento;

	private String observacao;

	@Column(name="PAIS_ORIGEM")
	private String paisOrigem;

	private String secao;

	@Column(name="ZONA_ELEITORAL")
	private String zonaEleitoral;

	//bi-directional many-to-one association to Pessoa
	@ManyToOne
	@JoinColumn(name="ID_PESSOA")
	private PessoaFisica pessoa;

	//bi-directional many-to-one association to TipoDocumento
	@ManyToOne
	@JoinColumn(name="ID_TIPO_DOCUMENTO")
	private TipoDocumento tipoDocumento;

	public DocumentoPessoa() {
	}

	public Integer getIdDocumento() {
		return this.idDocumento;
	}

	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	public Date getDataEmissao() {
		return this.dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataValidade() {
		return this.dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public String getDescOrgao() {
		return this.descOrgao;
	}

	public void setDescOrgao(String descOrgao) {
		this.descOrgao = descOrgao;
	}

	public String getNumeroDocumento() {
		return this.numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getPaisOrigem() {
		return this.paisOrigem;
	}

	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}

	public String getSecao() {
		return this.secao;
	}

	public void setSecao(String secao) {
		this.secao = secao;
	}

	public String getZonaEleitoral() {
		return this.zonaEleitoral;
	}

	public void setZonaEleitoral(String zonaEleitoral) {
		this.zonaEleitoral = zonaEleitoral;
	}

	public PessoaFisica getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(PessoaFisica pessoa) {
		this.pessoa = pessoa;
	}

	public TipoDocumento getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

}