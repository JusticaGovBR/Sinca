package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the analise_processo database table.
 * 
 */
@Entity
@Table(name="analise_processo")
@NamedQuery(name="AnaliseProcesso.findAll", query="SELECT a FROM AnaliseProcesso a")
public class AnaliseProcesso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ANALISE")
	private Long idAnalise;

	@Column(name="ATO_REPARATORIO")
	private String atoReparatorio;

	@Column(name="BOL_DECISAO_ANTERIOR")
	private byte bolDecisaoAnterior;

	@Column(name="BOL_OUTROS_PEDIDOS")
	private byte bolOutrosPedidos;

	@Column(name="BOL_PEDIDO_INTERESSADO")
	private byte bolPedidoInteressado;

	@Column(name="BOL_PUBLICACAO_PORTARIA")
	private byte bolPublicacaoPortaria;

	@Column(name="BOL_REPARACAO_ECONOMICA")
	private byte bolReparacaoEconomica;

	@Column(name="BOL_REPARACAO_MORAL")
	private byte bolReparacaoMoral;

	@Column(name="BOL_REQ_ANISTIADO")
	private byte bolReqAnistiado;

	@Column(name="BOL_RESTITUICAO_DIREITOS")
	private byte bolRestituicaoDireitos;

	private String complemento;

	@Column(name="COMPLEMENTO_PEDIDO")
	private String complementoPedido;

	@Column(name="CONCLUSOES_FINAIS")
	private String conclusoesFinais;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_ANISTIA")
	private Date dataAnistia;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_ATUALIZACAO")
	private Date dataAtualizacao;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_CADASTRO")
	private Date dataCadastro;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_PRIMEIRO_PROTOCOLO")
	private Date dataPrimeiroProtocolo;

	@Column(name="DECRICAO_PORTARI")
	private String decricaoPortari;

	private String desicao;

	@Column(name="ID_USUARIO")
	private int idUsuario;

	private String observacoes;

	@Column(name="ORGAO_ANISTIA")
	private String orgaoAnistia;

	@Lob
	@Column(name="RESULMO_PEDIDO")
	private String resulmoPedido;

	//bi-directional many-to-one association to Processo
	@ManyToOne
	@JoinColumn(name="ID_PROCESSO")
	private Processo processo;

	//bi-directional many-to-one association to PerseguicaoAnalise
	@OneToMany(mappedBy="analiseProcesso")
	private List<PerseguicaoAnalise> perseguicaoAnalises;

	//bi-directional many-to-one association to ReparacaoAnalise
	@OneToMany(mappedBy="analiseProcesso")
	private List<ReparacaoAnalise> reparacaoAnalises;

	//bi-directional many-to-one association to AtosExcecaoAnalise
	@OneToMany(mappedBy="analiseProcesso")
	private List<AtosExcecaoAnalise> atosExcecaoAnalises;

	//bi-directional many-to-one association to ProvasAnalise
	@OneToMany(mappedBy="analiseProcesso")
	private List<ProvasAnalise> provasAnalises;

	//bi-directional many-to-one association to RecomendacaoAnalise
	@OneToMany(mappedBy="analiseProcesso")
	private List<RecomendacaoAnalise> recomendacaoAnalises;

	public AnaliseProcesso() {
	}

	public Long getIdAnalise() {
		return this.idAnalise;
	}

	public void setIdAnalise(Long idAnalise) {
		this.idAnalise = idAnalise;
	}

	public String getAtoReparatorio() {
		return this.atoReparatorio;
	}

	public void setAtoReparatorio(String atoReparatorio) {
		this.atoReparatorio = atoReparatorio;
	}

	public byte getBolDecisaoAnterior() {
		return this.bolDecisaoAnterior;
	}

	public void setBolDecisaoAnterior(byte bolDecisaoAnterior) {
		this.bolDecisaoAnterior = bolDecisaoAnterior;
	}

	public byte getBolOutrosPedidos() {
		return this.bolOutrosPedidos;
	}

	public void setBolOutrosPedidos(byte bolOutrosPedidos) {
		this.bolOutrosPedidos = bolOutrosPedidos;
	}

	public byte getBolPedidoInteressado() {
		return this.bolPedidoInteressado;
	}

	public void setBolPedidoInteressado(byte bolPedidoInteressado) {
		this.bolPedidoInteressado = bolPedidoInteressado;
	}

	public byte getBolPublicacaoPortaria() {
		return this.bolPublicacaoPortaria;
	}

	public void setBolPublicacaoPortaria(byte bolPublicacaoPortaria) {
		this.bolPublicacaoPortaria = bolPublicacaoPortaria;
	}

	public byte getBolReparacaoEconomica() {
		return this.bolReparacaoEconomica;
	}

	public void setBolReparacaoEconomica(byte bolReparacaoEconomica) {
		this.bolReparacaoEconomica = bolReparacaoEconomica;
	}

	public byte getBolReparacaoMoral() {
		return this.bolReparacaoMoral;
	}

	public void setBolReparacaoMoral(byte bolReparacaoMoral) {
		this.bolReparacaoMoral = bolReparacaoMoral;
	}

	public byte getBolReqAnistiado() {
		return this.bolReqAnistiado;
	}

	public void setBolReqAnistiado(byte bolReqAnistiado) {
		this.bolReqAnistiado = bolReqAnistiado;
	}

	public byte getBolRestituicaoDireitos() {
		return this.bolRestituicaoDireitos;
	}

	public void setBolRestituicaoDireitos(byte bolRestituicaoDireitos) {
		this.bolRestituicaoDireitos = bolRestituicaoDireitos;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getComplementoPedido() {
		return this.complementoPedido;
	}

	public void setComplementoPedido(String complementoPedido) {
		this.complementoPedido = complementoPedido;
	}

	public String getConclusoesFinais() {
		return this.conclusoesFinais;
	}

	public void setConclusoesFinais(String conclusoesFinais) {
		this.conclusoesFinais = conclusoesFinais;
	}

	public Date getDataAnistia() {
		return this.dataAnistia;
	}

	public void setDataAnistia(Date dataAnistia) {
		this.dataAnistia = dataAnistia;
	}

	public Date getDataAtualizacao() {
		return this.dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataPrimeiroProtocolo() {
		return this.dataPrimeiroProtocolo;
	}

	public void setDataPrimeiroProtocolo(Date dataPrimeiroProtocolo) {
		this.dataPrimeiroProtocolo = dataPrimeiroProtocolo;
	}

	public String getDecricaoPortari() {
		return this.decricaoPortari;
	}

	public void setDecricaoPortari(String decricaoPortari) {
		this.decricaoPortari = decricaoPortari;
	}

	public String getDesicao() {
		return this.desicao;
	}

	public void setDesicao(String desicao) {
		this.desicao = desicao;
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getObservacoes() {
		return this.observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public String getOrgaoAnistia() {
		return this.orgaoAnistia;
	}

	public void setOrgaoAnistia(String orgaoAnistia) {
		this.orgaoAnistia = orgaoAnistia;
	}

	public String getResulmoPedido() {
		return this.resulmoPedido;
	}

	public void setResulmoPedido(String resulmoPedido) {
		this.resulmoPedido = resulmoPedido;
	}

	public Processo getProcesso() {
		return this.processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public List<PerseguicaoAnalise> getPerseguicaoAnalises() {
		return this.perseguicaoAnalises;
	}

	public void setPerseguicaoAnalises(List<PerseguicaoAnalise> perseguicaoAnalises) {
		this.perseguicaoAnalises = perseguicaoAnalises;
	}

	public PerseguicaoAnalise addPerseguicaoAnalis(PerseguicaoAnalise perseguicaoAnalis) {
		getPerseguicaoAnalises().add(perseguicaoAnalis);
		perseguicaoAnalis.setAnaliseProcesso(this);

		return perseguicaoAnalis;
	}

	public PerseguicaoAnalise removePerseguicaoAnalis(PerseguicaoAnalise perseguicaoAnalis) {
		getPerseguicaoAnalises().remove(perseguicaoAnalis);
		perseguicaoAnalis.setAnaliseProcesso(null);

		return perseguicaoAnalis;
	}

	public List<ReparacaoAnalise> getReparacaoAnalises() {
		return this.reparacaoAnalises;
	}

	public void setReparacaoAnalises(List<ReparacaoAnalise> reparacaoAnalises) {
		this.reparacaoAnalises = reparacaoAnalises;
	}

	public ReparacaoAnalise addReparacaoAnalis(ReparacaoAnalise reparacaoAnalis) {
		getReparacaoAnalises().add(reparacaoAnalis);
		reparacaoAnalis.setAnaliseProcesso(this);

		return reparacaoAnalis;
	}

	public ReparacaoAnalise removeReparacaoAnalis(ReparacaoAnalise reparacaoAnalis) {
		getReparacaoAnalises().remove(reparacaoAnalis);
		reparacaoAnalis.setAnaliseProcesso(null);

		return reparacaoAnalis;
	}

	public List<AtosExcecaoAnalise> getAtosExcecaoAnalises() {
		return this.atosExcecaoAnalises;
	}

	public void setAtosExcecaoAnalises(List<AtosExcecaoAnalise> atosExcecaoAnalises) {
		this.atosExcecaoAnalises = atosExcecaoAnalises;
	}

	public AtosExcecaoAnalise addAtosExcecaoAnalis(AtosExcecaoAnalise atosExcecaoAnalis) {
		getAtosExcecaoAnalises().add(atosExcecaoAnalis);
		atosExcecaoAnalis.setAnaliseProcesso(this);

		return atosExcecaoAnalis;
	}

	public AtosExcecaoAnalise removeAtosExcecaoAnalis(AtosExcecaoAnalise atosExcecaoAnalis) {
		getAtosExcecaoAnalises().remove(atosExcecaoAnalis);
		atosExcecaoAnalis.setAnaliseProcesso(null);

		return atosExcecaoAnalis;
	}

	public List<ProvasAnalise> getProvasAnalises() {
		return this.provasAnalises;
	}

	public void setProvasAnalises(List<ProvasAnalise> provasAnalises) {
		this.provasAnalises = provasAnalises;
	}

	public ProvasAnalise addProvasAnalis(ProvasAnalise provasAnalis) {
		getProvasAnalises().add(provasAnalis);
		provasAnalis.setAnaliseProcesso(this);

		return provasAnalis;
	}

	public ProvasAnalise removeProvasAnalis(ProvasAnalise provasAnalis) {
		getProvasAnalises().remove(provasAnalis);
		provasAnalis.setAnaliseProcesso(null);

		return provasAnalis;
	}

	public List<RecomendacaoAnalise> getRecomendacaoAnalises() {
		return this.recomendacaoAnalises;
	}

	public void setRecomendacaoAnalises(List<RecomendacaoAnalise> recomendacaoAnalises) {
		this.recomendacaoAnalises = recomendacaoAnalises;
	}

	public RecomendacaoAnalise addRecomendacaoAnalis(RecomendacaoAnalise recomendacaoAnalis) {
		getRecomendacaoAnalises().add(recomendacaoAnalis);
		recomendacaoAnalis.setAnaliseProcesso(this);

		return recomendacaoAnalis;
	}

	public RecomendacaoAnalise removeRecomendacaoAnalis(RecomendacaoAnalise recomendacaoAnalis) {
		getRecomendacaoAnalises().remove(recomendacaoAnalis);
		recomendacaoAnalis.setAnaliseProcesso(null);

		return recomendacaoAnalis;
	}

}