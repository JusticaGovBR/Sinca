package br.gov.mj.sinca.entidades;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the pessoa database table.
 * 
 */
@Entity
@NamedQuery(name="Pessoa.findAll", query="SELECT p FROM Pessoa p")
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PESSOA")
	private Long idPessoa;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_COMUNICADO_OBITO")
	private Date dataComunicadoObito;

	@Column(name="DATA_HORA_ATUALIZACAO")
	private Timestamp dataHoraAtualizacao;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_HORA_CADASTRO")
	private Date dataHoraCadastro;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_NASCIMENTO")
	private Date dataNascimento;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_OBITO")
	private Date dataObito;

	@Column(name="EMAIL_PESSOA")
	private String emailPessoa;

	private String genero;

	@Column(name="ID_USUARIO_CADASTRO")
	private Integer idUsuarioCadastro;

	private String nacionalidade;

	@Column(name="NOME_MAE")
	private String nomeMae;

	@Column(name="NOME_PAI")
	private String nomePai;

	@Column(name="NOME_PESSOA")
	private String nomePessoa;

	@Column(name="NUM_CPF")
	private String numCpf;

	private byte obito;

	private String observacao;

	@Column(name="OBSERVACAO_OBITO")
	private String observacaoObito;

	//bi-directional many-to-one association to AnalistaProcesso
	@OneToMany(mappedBy="pessoa")
	private List<AnalistaProcesso> analistaProcessos;

	//bi-directional many-to-one association to DocumentoPessoa
	@OneToMany(mappedBy="pessoa")
	private List<DocumentoPessoa> documentoPessoas;

	//bi-directional many-to-one association to DoencaPessoa
	@OneToMany(mappedBy="pessoa")
	private List<DoencaPessoa> doencaPessoas;

	@Column(name="ID_ESTADO_CIVIL")
	private Integer idEstadoCivil;

	
	//bi-directional many-to-one association to PessoaEndereco
	@OneToMany(mappedBy="pessoa")
	private List<PessoaEndereco> pessoaEnderecos;

	//bi-directional many-to-one association to PessoaProcesso
	@OneToMany(mappedBy="pessoa")
	private List<PessoaProcesso> pessoaProcessos;

	//bi-directional many-to-one association to TelefonePessoa
	@OneToMany(mappedBy="pessoa")
	private List<TelefonePessoa> telefonePessoas;

	public Pessoa() {
	}

	public Long getIdPessoa() {
	    return idPessoa;
	}

	public Date getDataComunicadoObito() {
	    return dataComunicadoObito;
	}

	public Timestamp getDataHoraAtualizacao() {
	    return dataHoraAtualizacao;
	}

	public Date getDataHoraCadastro() {
	    return dataHoraCadastro;
	}

	public Date getDataNascimento() {
	    return dataNascimento;
	}

	public Date getDataObito() {
	    return dataObito;
	}

	public String getEmailPessoa() {
	    return emailPessoa;
	}

	public String getGenero() {
	    return genero;
	}

	public Integer getIdUsuarioCadastro() {
	    return idUsuarioCadastro;
	}

	public String getNacionalidade() {
	    return nacionalidade;
	}

	public String getNomeMae() {
	    return nomeMae;
	}

	public String getNomePai() {
	    return nomePai;
	}

	public String getNomePessoa() {
	    return nomePessoa;
	}

	public String getNumCpf() {
	    return numCpf;
	}

	public byte getObito() {
	    return obito;
	}

	public String getObservacao() {
	    return observacao;
	}

	public String getObservacaoObito() {
	    return observacaoObito;
	}

	public List<AnalistaProcesso> getAnalistaProcessos() {
	    return analistaProcessos;
	}

	public List<DocumentoPessoa> getDocumentoPessoas() {
	    return documentoPessoas;
	}

	public List<DoencaPessoa> getDoencaPessoas() {
	    return doencaPessoas;
	}

	public Integer getIdEstadoCivil() {
	    return idEstadoCivil;
	}

	public List<PessoaEndereco> getPessoaEnderecos() {
	    return pessoaEnderecos;
	}

	public List<PessoaProcesso> getPessoaProcessos() {
	    return pessoaProcessos;
	}

	public List<TelefonePessoa> getTelefonePessoas() {
	    return telefonePessoas;
	}

	public void setIdPessoa(Long idPessoa) {
	    this.idPessoa = idPessoa;
	}

	public void setDataComunicadoObito(Date dataComunicadoObito) {
	    this.dataComunicadoObito = dataComunicadoObito;
	}

	public void setDataHoraAtualizacao(Timestamp dataHoraAtualizacao) {
	    this.dataHoraAtualizacao = dataHoraAtualizacao;
	}

	public void setDataHoraCadastro(Date dataHoraCadastro) {
	    this.dataHoraCadastro = dataHoraCadastro;
	}

	public void setDataNascimento(Date dataNascimento) {
	    this.dataNascimento = dataNascimento;
	}

	public void setDataObito(Date dataObito) {
	    this.dataObito = dataObito;
	}

	public void setEmailPessoa(String emailPessoa) {
	    this.emailPessoa = emailPessoa;
	}

	public void setGenero(String genero) {
	    this.genero = genero;
	}

	public void setIdUsuarioCadastro(Integer idUsuarioCadastro) {
	    this.idUsuarioCadastro = idUsuarioCadastro;
	}

	public void setNacionalidade(String nacionalidade) {
	    this.nacionalidade = nacionalidade;
	}

	public void setNomeMae(String nomeMae) {
	    this.nomeMae = nomeMae;
	}

	public void setNomePai(String nomePai) {
	    this.nomePai = nomePai;
	}

	public void setNomePessoa(String nomePessoa) {
	    this.nomePessoa = nomePessoa;
	}

	public void setNumCpf(String numCpf) {
	    this.numCpf = numCpf;
	}

	public void setObito(byte obito) {
	    this.obito = obito;
	}

	public void setObservacao(String observacao) {
	    this.observacao = observacao;
	}

	public void setObservacaoObito(String observacaoObito) {
	    this.observacaoObito = observacaoObito;
	}

	public void setAnalistaProcessos(List<AnalistaProcesso> analistaProcessos) {
	    this.analistaProcessos = analistaProcessos;
	}

	public void setDocumentoPessoas(List<DocumentoPessoa> documentoPessoas) {
	    this.documentoPessoas = documentoPessoas;
	}

	public void setDoencaPessoas(List<DoencaPessoa> doencaPessoas) {
	    this.doencaPessoas = doencaPessoas;
	}

	public void setIdEstadoCivil(Integer idEstadoCivil) {
	    this.idEstadoCivil = idEstadoCivil;
	}

	public void setPessoaEnderecos(List<PessoaEndereco> pessoaEnderecos) {
	    this.pessoaEnderecos = pessoaEnderecos;
	}

	public void setPessoaProcessos(List<PessoaProcesso> pessoaProcessos) {
	    this.pessoaProcessos = pessoaProcessos;
	}

	public void setTelefonePessoas(List<TelefonePessoa> telefonePessoas) {
	    this.telefonePessoas = telefonePessoas;
	}

	
}