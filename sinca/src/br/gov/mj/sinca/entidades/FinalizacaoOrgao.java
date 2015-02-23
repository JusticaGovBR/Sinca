package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the finalizacao_orgao database table.
 * 
 */
@Entity
@Table(name="finalizacao_orgao")
@NamedQuery(name="FinalizacaoOrgao.findAll", query="SELECT f FROM FinalizacaoOrgao f")
public class FinalizacaoOrgao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_FINALIZACAO_ORGAO")
	private int idFinalizacaoOrgao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_ATUALIZACAO")
	private Date dataAtualizacao;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_CADASTRO")
	private Date dataCadastro;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_ENVIO")
	private Date dataEnvio;

	@Column(name="ID_USUARIO")
	private int idUsuario;

	//bi-directional many-to-one association to FinalizacaoProcesso
	@ManyToOne
	@JoinColumn(name="ID_FINALIZACAO_PROCESSO")
	private FinalizacaoProcesso finalizacaoProcesso;

	//bi-directional many-to-one association to PessoaJuridica
	@ManyToOne
	@JoinColumn(name="ID_PESSOA_JURIDICA")
	private PessoaJuridica pessoaJuridica;

	public FinalizacaoOrgao() {
	}

	public int getIdFinalizacaoOrgao() {
		return this.idFinalizacaoOrgao;
	}

	public void setIdFinalizacaoOrgao(int idFinalizacaoOrgao) {
		this.idFinalizacaoOrgao = idFinalizacaoOrgao;
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

	public Date getDataEnvio() {
		return this.dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public FinalizacaoProcesso getFinalizacaoProcesso() {
		return this.finalizacaoProcesso;
	}

	public void setFinalizacaoProcesso(FinalizacaoProcesso finalizacaoProcesso) {
		this.finalizacaoProcesso = finalizacaoProcesso;
	}

	public PessoaJuridica getPessoaJuridica() {
		return this.pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}

}