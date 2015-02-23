package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the arquivo_processo database table.
 * 
 */
@Entity
@Table(name="arquivo_processo")
@NamedQuery(name="ArquivoProcesso.findAll", query="SELECT a FROM ArquivoProcesso a")
public class ArquivoProcesso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ARQUIVO_PROCESSO")
	private String idArquivoProcesso;

	private String armario;

	@Column(name="BOL_EXISTE_PENDENCIA")
	private byte bolExistePendencia;

	@Column(name="BOL_POSSUI_ERRO")
	private byte bolPossuiErro;

	private String caixa;

	private String caracteristicas;

	private String complemento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_ATUALIZACAO")
	private Date dataAtualizacao;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_CADASTRO")
	private Date dataCadastro;

	@Column(name="ID_USUARIO")
	private int idUsuario;

	private String localizacao;

	@Column(name="MATERIAL_ESPECIAL")
	private String materialEspecial;

	private String prateleirA;

	private String sala;

	//bi-directional many-to-one association to TipoDescricaoArquivistica
	@ManyToOne
	@JoinColumn(name="ID_DESCRICAO_ARQUIVISTICA")
	private TipoDescricaoArquivistica tipoDescricaoArquivistica;

	//bi-directional many-to-one association to Processo
	@ManyToOne
	@JoinColumn(name="ID_PROCESSO")
	private Processo processo;

	public ArquivoProcesso() {
	}

	public String getIdArquivoProcesso() {
		return this.idArquivoProcesso;
	}

	public void setIdArquivoProcesso(String idArquivoProcesso) {
		this.idArquivoProcesso = idArquivoProcesso;
	}

	public String getArmario() {
		return this.armario;
	}

	public void setArmario(String armario) {
		this.armario = armario;
	}

	public byte getBolExistePendencia() {
		return this.bolExistePendencia;
	}

	public void setBolExistePendencia(byte bolExistePendencia) {
		this.bolExistePendencia = bolExistePendencia;
	}

	public byte getBolPossuiErro() {
		return this.bolPossuiErro;
	}

	public void setBolPossuiErro(byte bolPossuiErro) {
		this.bolPossuiErro = bolPossuiErro;
	}

	public String getCaixa() {
		return this.caixa;
	}

	public void setCaixa(String caixa) {
		this.caixa = caixa;
	}

	public String getCaracteristicas() {
		return this.caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
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

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getLocalizacao() {
		return this.localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getMaterialEspecial() {
		return this.materialEspecial;
	}

	public void setMaterialEspecial(String materialEspecial) {
		this.materialEspecial = materialEspecial;
	}

	public String getPrateleirA() {
		return this.prateleirA;
	}

	public void setPrateleirA(String prateleirA) {
		this.prateleirA = prateleirA;
	}

	public String getSala() {
		return this.sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public TipoDescricaoArquivistica getTipoDescricaoArquivistica() {
		return this.tipoDescricaoArquivistica;
	}

	public void setTipoDescricaoArquivistica(TipoDescricaoArquivistica tipoDescricaoArquivistica) {
		this.tipoDescricaoArquivistica = tipoDescricaoArquivistica;
	}

	public Processo getProcesso() {
		return this.processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

}