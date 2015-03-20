package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the finalizacao_notificacao database table.
 * 
 */
@Entity
@Table(name="finalizacao_notificacao")
@NamedQuery(name="FinalizacaoNotificacao.findAll", query="SELECT f FROM FinalizacaoNotificacao f")
public class FinalizacaoNotificacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_FINALIZACAO_NOTIFICACAO")
	private Long idFinalizacaoNotificacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_ATUALIZACAO")
	private Date dataAtualizacao;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_CADASTRO")
	private Date dataCadastro;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_ENVIO")
	private Date dataEnvio;

	private String envio;

	@Column(name="ID_USUARIO")
	private int idUsuario;

	private String tipo;

	//bi-directional many-to-one association to FinalizacaoProcesso
	@ManyToOne
	@JoinColumn(name="ID_FINALIZACAO_PROCESSO")
	private FinalizacaoProcesso finalizacaoProcesso;

	public FinalizacaoNotificacao() {
	}

	public Long getIdFinalizacaoNotificacao() {
		return this.idFinalizacaoNotificacao;
	}

	public void setIdFinalizacaoNotificacao(Long idFinalizacaoNotificacao) {
		this.idFinalizacaoNotificacao = idFinalizacaoNotificacao;
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

	public String getEnvio() {
		return this.envio;
	}

	public void setEnvio(String envio) {
		this.envio = envio;
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public FinalizacaoProcesso getFinalizacaoProcesso() {
		return this.finalizacaoProcesso;
	}

	public void setFinalizacaoProcesso(FinalizacaoProcesso finalizacaoProcesso) {
		this.finalizacaoProcesso = finalizacaoProcesso;
	}

}