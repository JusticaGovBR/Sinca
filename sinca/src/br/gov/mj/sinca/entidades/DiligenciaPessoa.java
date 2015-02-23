package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the diligencia_processo database table.
 * 
 */
@Entity
@Table(name="diligencia_pessoa")
@NamedQuery(name="DiligenciaPessoa.findAll", query="SELECT d FROM DiligenciaPessoa d")
public class DiligenciaPessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_DILIGENCIA_PROCESSO")
	private Long idDiligenciaProcesso;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_ATUALIZACAO")
	private Date dataAtualizacao;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_CADASTRO")
	private Date dataCadastro;

	@Column(name="ID_USUARIO")
	private int idUsuario;

	@ManyToOne
	@JoinColumn(name="ID_DILIGENCIA")
	private Diligencia diligencia;

	@ManyToOne
	@JoinColumn(name="ID_PESSO_FISICA")
	private PessoaFisica pessoaFisica;

	public DiligenciaPessoa() {
	}

	public Long getIdDiligenciaProcesso() {
		return this.idDiligenciaProcesso;
	}

	public void setIdDiligenciaProcesso(Long idDiligenciaProcesso) {
		this.idDiligenciaProcesso = idDiligenciaProcesso;
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

	public PessoaFisica getPessoaFisica() {
		return this.pessoaFisica;
	}

	public Diligencia getDiligencia() {
	    return diligencia;
	}

	public void setDiligencia(Diligencia diligencia) {
	    this.diligencia = diligencia;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

}