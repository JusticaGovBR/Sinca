package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the dados_adicionais database table.
 * 
 */
@Entity
@Table(name="dados_adicionais")
@NamedQuery(name="DadosAdicionais.findAll", query="SELECT d FROM DadosAdicionais d")
public class DadosAdicionais implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_DADOS_ADICIONAL", unique=true, nullable=false)
	private int idDadosAdicional;

	@Column(name="ACOMPANHAMENTO_EXTERNO", nullable=false)
	private byte acompanhamentoExterno;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_ATUACAO")
	private Date dataAtuacao;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_EMISSAO", nullable=false)
	private Date dataEmissao;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_PROTOCOLO_MJ", nullable=false)
	private Date dataProtocoloMj;

	@Column(name="DESC_RESUMO", length=1000)
	private String descResumo;

	@Column(name="PROTOCOLO_EXTERNO", nullable=false)
	private byte protocoloExterno;

	//bi-directional many-to-one association to Assunto
	@ManyToOne
	@JoinColumn(name="ID_ASSUNTO")
	private Assunto assunto;

	//bi-directional many-to-one association to Especie
	@ManyToOne
	@JoinColumn(name="ID_ESPECIE")
	private Especie especie;

	//bi-directional many-to-one association to Natureza
	@ManyToOne
	@JoinColumn(name="ID_NATUREZA")
	private Natureza natureza;

	//bi-directional many-to-one association to Procedencia
	@ManyToOne
	@JoinColumn(name="ID_PROCEDENCIA")
	private Procedencia procedencia;

	//bi-directional many-to-one association to Processo
	@ManyToOne
	@JoinColumn(name="ID_PROCESSO", nullable=false)
	private Processo processo;
	
	

	//bi-directional many-to-one association to TipoProcesso
	@ManyToOne
	@JoinColumn(name="ID_TIPO_PROCESSO")
	private TipoProcesso tipoProcesso;

	public DadosAdicionais() {
	}

	public int getIdDadosAdicional() {
		return this.idDadosAdicional;
	}

	public void setIdDadosAdicional(int idDadosAdicional) {
		this.idDadosAdicional = idDadosAdicional;
	}

	public byte getAcompanhamentoExterno() {
		return this.acompanhamentoExterno;
	}

	public void setAcompanhamentoExterno(byte acompanhamentoExterno) {
		this.acompanhamentoExterno = acompanhamentoExterno;
	}

	public Date getDataAtuacao() {
		return this.dataAtuacao;
	}

	public void setDataAtuacao(Date dataAtuacao) {
		this.dataAtuacao = dataAtuacao;
	}

	public Date getDataEmissao() {
		return this.dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataProtocoloMj() {
		return this.dataProtocoloMj;
	}

	public void setDataProtocoloMj(Date dataProtocoloMj) {
		this.dataProtocoloMj = dataProtocoloMj;
	}

	public String getDescResumo() {
		return this.descResumo;
	}

	public void setDescResumo(String descResumo) {
		this.descResumo = descResumo;
	}

	public byte getProtocoloExterno() {
		return this.protocoloExterno;
	}

	public void setProtocoloExterno(byte protocoloExterno) {
		this.protocoloExterno = protocoloExterno;
	}

	public Assunto getAssunto() {
		return this.assunto;
	}

	public void setAssunto(Assunto assunto) {
		this.assunto = assunto;
	}

	public Especie getEspecie() {
		return this.especie;
	}

	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

	public Natureza getNatureza() {
		return this.natureza;
	}

	public void setNatureza(Natureza natureza) {
		this.natureza = natureza;
	}

	public Procedencia getProcedencia() {
		return this.procedencia;
	}

	public void setProcedencia(Procedencia procedencia) {
		this.procedencia = procedencia;
	}

	public Processo getProcesso() {
		return this.processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public TipoProcesso getTipoProcesso() {
		return this.tipoProcesso;
	}

	public void setTipoProcesso(TipoProcesso tipoProcesso) {
		this.tipoProcesso = tipoProcesso;
	}

}