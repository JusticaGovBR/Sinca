package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the conselheiros_julgamento database table.
 * 
 */
@Entity
@Table(name="conselheiros_julgamento")
@NamedQuery(name="ConselheirosJulgamento.findAll", query="SELECT c FROM ConselheirosJulgamento c")
public class ConselheirosJulgamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_CONSELHEIROS")
	private String idConselheiros;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_ATUALIZACAO")
	private Date dataAtualizacao;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_CADASTRO")
	private Date dataCadastro;

	@Column(name="ID_USUARIO")
	private int idUsuario;

	//bi-directional many-to-one association to JulgamentoProcesso
	@ManyToOne
	@JoinColumn(name="ID_JULGAMENTO_PROCESSO")
	private JulgamentoProcesso julgamentoProcesso;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="ID_USUARIO_CONSELHEIRO")
	private Usuario usuario;

	public ConselheirosJulgamento() {
	}

	public String getIdConselheiros() {
		return this.idConselheiros;
	}

	public void setIdConselheiros(String idConselheiros) {
		this.idConselheiros = idConselheiros;
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

	public JulgamentoProcesso getJulgamentoProcesso() {
		return this.julgamentoProcesso;
	}

	public void setJulgamentoProcesso(JulgamentoProcesso julgamentoProcesso) {
		this.julgamentoProcesso = julgamentoProcesso;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}