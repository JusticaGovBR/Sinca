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

	@Column(name="NOME_FANTASIA")
	private String nomeFantasia;

	@Column(name="NOME_MAE")
	private String nomeMae;

	@Column(name="NOME_PAI")
	private String nomePai;

	@Column(name="NOME_PESSOA")
	private String nomePessoa;

	@Column(name="NUM_CPF_CNPJ")
	private String numCpfCnpj;

	@Column(name="NUM_INSC_ESTADUAL")
	private String numInscEstadual;

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

	@Column(name="COD_TIPO")
	private Integer codTipo;
	
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
		return this.idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public Date getDataComunicadoObito() {
		return this.dataComunicadoObito;
	}

	public void setDataComunicadoObito(Date dataComunicadoObito) {
		this.dataComunicadoObito = dataComunicadoObito;
	}

	public Timestamp getDataHoraAtualizacao() {
		return this.dataHoraAtualizacao;
	}

	public void setDataHoraAtualizacao(Timestamp dataHoraAtualizacao) {
		this.dataHoraAtualizacao = dataHoraAtualizacao;
	}

	public Date getDataHoraCadastro() {
		return this.dataHoraCadastro;
	}

	public void setDataHoraCadastro(Date dataHoraCadastro) {
		this.dataHoraCadastro = dataHoraCadastro;
	}

	public Date getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataObito() {
		return this.dataObito;
	}

	public void setDataObito(Date dataObito) {
		this.dataObito = dataObito;
	}

	public String getEmailPessoa() {
		return this.emailPessoa;
	}

	public void setEmailPessoa(String emailPessoa) {
		this.emailPessoa = emailPessoa;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Integer getIdUsuarioCadastro() {
		return this.idUsuarioCadastro;
	}

	public void setIdUsuarioCadastro(Integer idUsuarioCadastro) {
		this.idUsuarioCadastro = idUsuarioCadastro;
	}

	public String getNacionalidade() {
		return this.nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getNomeFantasia() {
		return this.nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getNomeMae() {
		return this.nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getNomePai() {
		return this.nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getNomePessoa() {
		return this.nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getNumCpfCnpj() {
		return this.numCpfCnpj;
	}

	public void setNumCpfCnpj(String numCpfCnpj) {
		this.numCpfCnpj = numCpfCnpj;
	}

	public String getNumInscEstadual() {
		return this.numInscEstadual;
	}

	public void setNumInscEstadual(String numInscEstadual) {
		this.numInscEstadual = numInscEstadual;
	}

	public byte getObito() {
		return this.obito;
	}

	public void setObito(byte obito) {
		this.obito = obito;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getObservacaoObito() {
		return this.observacaoObito;
	}

	public void setObservacaoObito(String observacaoObito) {
		this.observacaoObito = observacaoObito;
	}

	public List<AnalistaProcesso> getAnalistaProcessos() {
		return this.analistaProcessos;
	}

	public void setAnalistaProcessos(List<AnalistaProcesso> analistaProcessos) {
		this.analistaProcessos = analistaProcessos;
	}

	public AnalistaProcesso addAnalistaProcesso(AnalistaProcesso analistaProcesso) {
		getAnalistaProcessos().add(analistaProcesso);
		analistaProcesso.setPessoa(this);

		return analistaProcesso;
	}

	public AnalistaProcesso removeAnalistaProcesso(AnalistaProcesso analistaProcesso) {
		getAnalistaProcessos().remove(analistaProcesso);
		analistaProcesso.setPessoa(null);

		return analistaProcesso;
	}

	public List<DocumentoPessoa> getDocumentoPessoas() {
		return this.documentoPessoas;
	}

	public void setDocumentoPessoas(List<DocumentoPessoa> documentoPessoas) {
		this.documentoPessoas = documentoPessoas;
	}

	public DocumentoPessoa addDocumentoPessoa(DocumentoPessoa documentoPessoa) {
		getDocumentoPessoas().add(documentoPessoa);
		documentoPessoa.setPessoa(this);

		return documentoPessoa;
	}

	public DocumentoPessoa removeDocumentoPessoa(DocumentoPessoa documentoPessoa) {
		getDocumentoPessoas().remove(documentoPessoa);
		documentoPessoa.setPessoa(null);

		return documentoPessoa;
	}

	public List<DoencaPessoa> getDoencaPessoas() {
		return this.doencaPessoas;
	}

	public void setDoencaPessoas(List<DoencaPessoa> doencaPessoas) {
		this.doencaPessoas = doencaPessoas;
	}

	public DoencaPessoa addDoencaPessoa(DoencaPessoa doencaPessoa) {
		getDoencaPessoas().add(doencaPessoa);
		doencaPessoa.setPessoa(this);

		return doencaPessoa;
	}

	public DoencaPessoa removeDoencaPessoa(DoencaPessoa doencaPessoa) {
		getDoencaPessoas().remove(doencaPessoa);
		doencaPessoa.setPessoa(null);

		return doencaPessoa;
	}

		public Integer getIdEstadoCivil() {
	    return idEstadoCivil;
	}

	public void setIdEstadoCivil(Integer idEstadoCivil) {
	    this.idEstadoCivil = idEstadoCivil;
	}

	public Integer getCodTipo() {
	    return codTipo;
	}

	public void setCodTipo(Integer codTipo) {
	    this.codTipo = codTipo;
	}

	public List<PessoaEndereco> getPessoaEnderecos() {
		return this.pessoaEnderecos;
	}

	public void setPessoaEnderecos(List<PessoaEndereco> pessoaEnderecos) {
		this.pessoaEnderecos = pessoaEnderecos;
	}

	public PessoaEndereco addPessoaEndereco(PessoaEndereco pessoaEndereco) {
		getPessoaEnderecos().add(pessoaEndereco);
		pessoaEndereco.setPessoa(this);

		return pessoaEndereco;
	}

	public PessoaEndereco removePessoaEndereco(PessoaEndereco pessoaEndereco) {
		getPessoaEnderecos().remove(pessoaEndereco);
		pessoaEndereco.setPessoa(null);

		return pessoaEndereco;
	}

	public List<PessoaProcesso> getPessoaProcessos() {
		return this.pessoaProcessos;
	}

	public void setPessoaProcessos(List<PessoaProcesso> pessoaProcessos) {
		this.pessoaProcessos = pessoaProcessos;
	}

	public PessoaProcesso addPessoaProcesso(PessoaProcesso pessoaProcesso) {
		getPessoaProcessos().add(pessoaProcesso);
		pessoaProcesso.setPessoa(this);

		return pessoaProcesso;
	}

	public PessoaProcesso removePessoaProcesso(PessoaProcesso pessoaProcesso) {
		getPessoaProcessos().remove(pessoaProcesso);
		pessoaProcesso.setPessoa(null);

		return pessoaProcesso;
	}

	public List<TelefonePessoa> getTelefonePessoas() {
		return this.telefonePessoas;
	}

	public void setTelefonePessoas(List<TelefonePessoa> telefonePessoas) {
		this.telefonePessoas = telefonePessoas;
	}

	public TelefonePessoa addTelefonePessoa(TelefonePessoa telefonePessoa) {
		getTelefonePessoas().add(telefonePessoa);
		telefonePessoa.setPessoa(this);

		return telefonePessoa;
	}

	public TelefonePessoa removeTelefonePessoa(TelefonePessoa telefonePessoa) {
		getTelefonePessoas().remove(telefonePessoa);
		telefonePessoa.setPessoa(null);

		return telefonePessoa;
	}

}