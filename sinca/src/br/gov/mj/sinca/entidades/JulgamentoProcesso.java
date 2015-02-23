package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the julgamento_processo database table.
 * 
 */
@Entity
@Table(name="julgamento_processo")
@NamedQuery(name="JulgamentoProcesso.findAll", query="SELECT j FROM JulgamentoProcesso j")
public class JulgamentoProcesso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_JULGAMENTO_PROCESSO")
	private Long idJulgamentoProcesso;

	@Column(name="BOL_HOUVE_ALTERACAO_PROPOSTA")
	private byte bolHouveAlteracaoProposta;

	@Column(name="BOL_HOUVE_NOVAS_DILIGENCIAS")
	private byte bolHouveNovasDiligencias;

	@Column(name="BOL_HOUVE_PEDIDO_VISTA")
	private byte bolHouvePedidoVista;

	@Column(name="BOL_HOUVE_REVISAO_MINUTA")
	private byte bolHouveRevisaoMinuta;

	@Column(name="BOL_JULGAMENTO_ADIADO")
	private byte bolJulgamentoAdiado;

	@Column(name="BOL_REPARACAO_ECONOMICA")
	private BigInteger bolReparacaoEconomica;

	@Column(name="BOL_REPARACAO_MORAL")
	private BigInteger bolReparacaoMoral;

	@Column(name="BOL_RESTITUICAO_DIREITOS")
	private BigInteger bolRestituicaoDireitos;

	@Column(name="CD_TIPO_JULGAMENTO")
	private int cdTipoJulgamento;

	@Column(name="COMPLEMENTO_ALTERACAO_PROPOSTA")
	private String complementoAlteracaoProposta;

	@Column(name="COMPLEMENTO_DILIGENCIA")
	private String complementoDiligencia;

	@Column(name="COMPLEMENTO_JULGAMENTO")
	private String complementoJulgamento;

	@Column(name="COMPLEMENTO_REPARACAO")
	private String complementoReparacao;

	@Column(name="COMPLEMENTO_REVISAO")
	private BigInteger complementoRevisao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_ATUALIZACAO")
	private Date dataAtualizacao;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_CADASTRO")
	private Date dataCadastro;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_DISTRIBUICAO")
	private Date dataDistribuicao;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_JULGAMENTO")
	private Date dataJulgamento;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_PREVISAO_PAUTA")
	private Date dataPrevisaoPauta;

	@Column(name="ID_USUARIO")
	private int idUsuario;

	@Column(name="ID_USUARIO_RELATOR")
	private BigInteger idUsuarioRelator;

	@Column(name="ID_USUARIO_VISTAS")
	private int idUsuarioVistas;

	//bi-directional many-to-one association to ConselheirosJulgamento
	@OneToMany(mappedBy="julgamentoProcesso")
	private List<ConselheirosJulgamento> conselheirosJulgamentos;

	//bi-directional many-to-one association to TipoDesicaoJulgamento
	@ManyToOne
	@JoinColumn(name="COD_TIPO_DESICAO")
	private TipoDesicaoJulgamento tipoDesicaoJulgamento;

	//bi-directional many-to-one association to Processo
	@ManyToOne
	@JoinColumn(name="ID_PROCESSO")
	private Processo processo;

	//bi-directional many-to-one association to ReparacaoDireitoJulgamento
	@OneToMany(mappedBy="julgamentoProcesso")
	private List<ReparacaoDireitoJulgamento> reparacaoDireitoJulgamentos;

	//bi-directional many-to-one association to ReparacaoEconomicaJulgamento
	@OneToMany(mappedBy="julgamentoProcesso")
	private List<ReparacaoEconomicaJulgamento> reparacaoEconomicaJulgamentos;

	//bi-directional many-to-one association to ReparacaoMoralJulgamento
	@OneToMany(mappedBy="julgamentoProcesso")
	private List<ReparacaoMoralJulgamento> reparacaoMoralJulgamentos;

	public JulgamentoProcesso() {
	}

	public Long getIdJulgamentoProcesso() {
		return this.idJulgamentoProcesso;
	}

	public void setIdJulgamentoProcesso(Long idJulgamentoProcesso) {
		this.idJulgamentoProcesso = idJulgamentoProcesso;
	}

	public byte getBolHouveAlteracaoProposta() {
		return this.bolHouveAlteracaoProposta;
	}

	public void setBolHouveAlteracaoProposta(byte bolHouveAlteracaoProposta) {
		this.bolHouveAlteracaoProposta = bolHouveAlteracaoProposta;
	}

	public byte getBolHouveNovasDiligencias() {
		return this.bolHouveNovasDiligencias;
	}

	public void setBolHouveNovasDiligencias(byte bolHouveNovasDiligencias) {
		this.bolHouveNovasDiligencias = bolHouveNovasDiligencias;
	}

	public byte getBolHouvePedidoVista() {
		return this.bolHouvePedidoVista;
	}

	public void setBolHouvePedidoVista(byte bolHouvePedidoVista) {
		this.bolHouvePedidoVista = bolHouvePedidoVista;
	}

	public byte getBolHouveRevisaoMinuta() {
		return this.bolHouveRevisaoMinuta;
	}

	public void setBolHouveRevisaoMinuta(byte bolHouveRevisaoMinuta) {
		this.bolHouveRevisaoMinuta = bolHouveRevisaoMinuta;
	}

	public byte getBolJulgamentoAdiado() {
		return this.bolJulgamentoAdiado;
	}

	public void setBolJulgamentoAdiado(byte bolJulgamentoAdiado) {
		this.bolJulgamentoAdiado = bolJulgamentoAdiado;
	}

	public BigInteger getBolReparacaoEconomica() {
		return this.bolReparacaoEconomica;
	}

	public void setBolReparacaoEconomica(BigInteger bolReparacaoEconomica) {
		this.bolReparacaoEconomica = bolReparacaoEconomica;
	}

	public BigInteger getBolReparacaoMoral() {
		return this.bolReparacaoMoral;
	}

	public void setBolReparacaoMoral(BigInteger bolReparacaoMoral) {
		this.bolReparacaoMoral = bolReparacaoMoral;
	}

	public BigInteger getBolRestituicaoDireitos() {
		return this.bolRestituicaoDireitos;
	}

	public void setBolRestituicaoDireitos(BigInteger bolRestituicaoDireitos) {
		this.bolRestituicaoDireitos = bolRestituicaoDireitos;
	}

	public int getCdTipoJulgamento() {
		return this.cdTipoJulgamento;
	}

	public void setCdTipoJulgamento(int cdTipoJulgamento) {
		this.cdTipoJulgamento = cdTipoJulgamento;
	}

	public String getComplementoAlteracaoProposta() {
		return this.complementoAlteracaoProposta;
	}

	public void setComplementoAlteracaoProposta(String complementoAlteracaoProposta) {
		this.complementoAlteracaoProposta = complementoAlteracaoProposta;
	}

	public String getComplementoDiligencia() {
		return this.complementoDiligencia;
	}

	public void setComplementoDiligencia(String complementoDiligencia) {
		this.complementoDiligencia = complementoDiligencia;
	}

	public String getComplementoJulgamento() {
		return this.complementoJulgamento;
	}

	public void setComplementoJulgamento(String complementoJulgamento) {
		this.complementoJulgamento = complementoJulgamento;
	}

	public String getComplementoReparacao() {
		return this.complementoReparacao;
	}

	public void setComplementoReparacao(String complementoReparacao) {
		this.complementoReparacao = complementoReparacao;
	}

	public BigInteger getComplementoRevisao() {
		return this.complementoRevisao;
	}

	public void setComplementoRevisao(BigInteger complementoRevisao) {
		this.complementoRevisao = complementoRevisao;
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

	public Date getDataDistribuicao() {
		return this.dataDistribuicao;
	}

	public void setDataDistribuicao(Date dataDistribuicao) {
		this.dataDistribuicao = dataDistribuicao;
	}

	public Date getDataJulgamento() {
		return this.dataJulgamento;
	}

	public void setDataJulgamento(Date dataJulgamento) {
		this.dataJulgamento = dataJulgamento;
	}

	public Date getDataPrevisaoPauta() {
		return this.dataPrevisaoPauta;
	}

	public void setDataPrevisaoPauta(Date dataPrevisaoPauta) {
		this.dataPrevisaoPauta = dataPrevisaoPauta;
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public BigInteger getIdUsuarioRelator() {
		return this.idUsuarioRelator;
	}

	public void setIdUsuarioRelator(BigInteger idUsuarioRelator) {
		this.idUsuarioRelator = idUsuarioRelator;
	}

	public int getIdUsuarioVistas() {
		return this.idUsuarioVistas;
	}

	public void setIdUsuarioVistas(int idUsuarioVistas) {
		this.idUsuarioVistas = idUsuarioVistas;
	}

	public List<ConselheirosJulgamento> getConselheirosJulgamentos() {
		return this.conselheirosJulgamentos;
	}

	public void setConselheirosJulgamentos(List<ConselheirosJulgamento> conselheirosJulgamentos) {
		this.conselheirosJulgamentos = conselheirosJulgamentos;
	}

	public ConselheirosJulgamento addConselheirosJulgamento(ConselheirosJulgamento conselheirosJulgamento) {
		getConselheirosJulgamentos().add(conselheirosJulgamento);
		conselheirosJulgamento.setJulgamentoProcesso(this);

		return conselheirosJulgamento;
	}

	public ConselheirosJulgamento removeConselheirosJulgamento(ConselheirosJulgamento conselheirosJulgamento) {
		getConselheirosJulgamentos().remove(conselheirosJulgamento);
		conselheirosJulgamento.setJulgamentoProcesso(null);

		return conselheirosJulgamento;
	}

	public TipoDesicaoJulgamento getTipoDesicaoJulgamento() {
		return this.tipoDesicaoJulgamento;
	}

	public void setTipoDesicaoJulgamento(TipoDesicaoJulgamento tipoDesicaoJulgamento) {
		this.tipoDesicaoJulgamento = tipoDesicaoJulgamento;
	}

	public Processo getProcesso() {
		return this.processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public List<ReparacaoDireitoJulgamento> getReparacaoDireitoJulgamentos() {
		return this.reparacaoDireitoJulgamentos;
	}

	public void setReparacaoDireitoJulgamentos(List<ReparacaoDireitoJulgamento> reparacaoDireitoJulgamentos) {
		this.reparacaoDireitoJulgamentos = reparacaoDireitoJulgamentos;
	}

	public ReparacaoDireitoJulgamento addReparacaoDireitoJulgamento(ReparacaoDireitoJulgamento reparacaoDireitoJulgamento) {
		getReparacaoDireitoJulgamentos().add(reparacaoDireitoJulgamento);
		reparacaoDireitoJulgamento.setJulgamentoProcesso(this);

		return reparacaoDireitoJulgamento;
	}

	public ReparacaoDireitoJulgamento removeReparacaoDireitoJulgamento(ReparacaoDireitoJulgamento reparacaoDireitoJulgamento) {
		getReparacaoDireitoJulgamentos().remove(reparacaoDireitoJulgamento);
		reparacaoDireitoJulgamento.setJulgamentoProcesso(null);

		return reparacaoDireitoJulgamento;
	}

	public List<ReparacaoEconomicaJulgamento> getReparacaoEconomicaJulgamentos() {
		return this.reparacaoEconomicaJulgamentos;
	}

	public void setReparacaoEconomicaJulgamentos(List<ReparacaoEconomicaJulgamento> reparacaoEconomicaJulgamentos) {
		this.reparacaoEconomicaJulgamentos = reparacaoEconomicaJulgamentos;
	}

	public ReparacaoEconomicaJulgamento addReparacaoEconomicaJulgamento(ReparacaoEconomicaJulgamento reparacaoEconomicaJulgamento) {
		getReparacaoEconomicaJulgamentos().add(reparacaoEconomicaJulgamento);
		reparacaoEconomicaJulgamento.setJulgamentoProcesso(this);

		return reparacaoEconomicaJulgamento;
	}

	public ReparacaoEconomicaJulgamento removeReparacaoEconomicaJulgamento(ReparacaoEconomicaJulgamento reparacaoEconomicaJulgamento) {
		getReparacaoEconomicaJulgamentos().remove(reparacaoEconomicaJulgamento);
		reparacaoEconomicaJulgamento.setJulgamentoProcesso(null);

		return reparacaoEconomicaJulgamento;
	}

	public List<ReparacaoMoralJulgamento> getReparacaoMoralJulgamentos() {
		return this.reparacaoMoralJulgamentos;
	}

	public void setReparacaoMoralJulgamentos(List<ReparacaoMoralJulgamento> reparacaoMoralJulgamentos) {
		this.reparacaoMoralJulgamentos = reparacaoMoralJulgamentos;
	}

	public ReparacaoMoralJulgamento addReparacaoMoralJulgamento(ReparacaoMoralJulgamento reparacaoMoralJulgamento) {
		getReparacaoMoralJulgamentos().add(reparacaoMoralJulgamento);
		reparacaoMoralJulgamento.setJulgamentoProcesso(this);

		return reparacaoMoralJulgamento;
	}

	public ReparacaoMoralJulgamento removeReparacaoMoralJulgamento(ReparacaoMoralJulgamento reparacaoMoralJulgamento) {
		getReparacaoMoralJulgamentos().remove(reparacaoMoralJulgamento);
		reparacaoMoralJulgamento.setJulgamentoProcesso(null);

		return reparacaoMoralJulgamento;
	}

}