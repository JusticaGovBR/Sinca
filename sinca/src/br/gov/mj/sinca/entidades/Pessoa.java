package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the pessoa database table.
 * 
 */
@Entity
@Table(name = "pessoa")
@NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p")
public class Pessoa implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2983311101021000163L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PESSOA", unique = true, nullable = false)
    private Long idPessoa;

    @Column(name = "NUM_CPF_CNPJ")
    private String numCpfCnpj;

    @Column(name = "NOME_PESSOA")
    private String nomePessoa;

    @Column(name = "NOME_MAE")
    private String nomeMae;

    @Column(name = "NOME_PAI")
    private String nomePai;

    @Column(name = "OBITO")
    private byte obito;

    @Column(name = "OBSERVACAO_OBITO")
    private String observacaoObito;

    @Column(name = "EMAIL_PESSOA")
    private String emailPessoa;

    @Column(name = "ID_ESTADO_CIVIL")
    private Integer idEstadoCivil;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.REFRESH)
    private List<PessoaEndereco> pessoaEnderecos;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.REFRESH)
    private List<TelefonePessoa> telefonePessoas;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.REFRESH)
    private List<DocumentoPessoa> documentoPessoa;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.REFRESH)
    private List<DoencaPessoa> doencaPessoa;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_COMUNICADO_OBITO")
    private Date dataComunicadoObito;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_NASCIMENTO")
    private Date dataNascimento;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_OBITO")
    private Date dataObito;

    @Column(name = "GENERO")
    private String genero;

    @Column(name = "NACIONALIDADE")
    private String nacionalidade;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_HORA_ATUALIZACAO")
    private Date dataHoraAtualizacao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_HORA_CADASTRO")
    private Date dataHoraCadastro;

    @Column(name = "ID_USUARIO_CADASTRO")
    private Integer idUsuarioCadastro;

    @Column(name = "OBSERVACAO")
    private String observacao;

    @Column(name = "NOME_FANTASIA")
    private String nomeFantasia;

    @Column(name = "NUM_INSC_ESTADUAL")
    private String numInscEstadual;

    @Column(name = "COD_TIPO")
    private Integer codTipo;

    public Pessoa() {
    }

    public Long getIdPessoa() {
	return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
	this.idPessoa = idPessoa;
    }

    public Date getDataComunicadoObito() {
	return dataComunicadoObito;
    }

    public void setDataComunicadoObito(Date dataComunicadoObito) {
	this.dataComunicadoObito = dataComunicadoObito;
    }

    public Date getDataNascimento() {
	return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
	this.dataNascimento = dataNascimento;
    }

    public Date getDataObito() {
	return dataObito;
    }

    public void setDataObito(Date dataObito) {
	this.dataObito = dataObito;
    }

    public String getGenero() {
	return genero;
    }

    public void setGenero(String genero) {
	this.genero = genero;
    }

    public String getNacionalidade() {
	return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
	this.nacionalidade = nacionalidade;
    }

    public String getNomeMae() {
	return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
	this.nomeMae = nomeMae;
    }

    public String getNomePai() {
	return nomePai;
    }

    public void setNomePai(String nomePai) {
	this.nomePai = nomePai;
    }

    public String getNomePessoa() {
	return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
	this.nomePessoa = nomePessoa;
    }

    public String getNumCpfCnpj() {
	return numCpfCnpj;
    }

    public void setNumCpfCnpj(String numCpfCnpj) {
	this.numCpfCnpj = numCpfCnpj;
    }

    public byte getObito() {
	return obito;
    }

    public void setObito(byte obito) {
	this.obito = obito;
    }

    public String getObservacaoObito() {
	return observacaoObito;
    }

    public void setObservacaoObito(String observacaoObito) {
	this.observacaoObito = observacaoObito;
    }

    public String getEmailPessoa() {
	return emailPessoa;
    }

    public void setEmailPessoa(String emailPessoa) {
	this.emailPessoa = emailPessoa;
    }

    public Integer getIdEstadoCivil() {
	return idEstadoCivil;
    }

    public void setIdEstadoCivil(Integer idEstadoCivil) {
	this.idEstadoCivil = idEstadoCivil;
    }

    public List<PessoaEndereco> getPessoaEnderecos() {
	return pessoaEnderecos;
    }

    public void setPessoaEnderecos(List<PessoaEndereco> pessoaEnderecos) {
	this.pessoaEnderecos = pessoaEnderecos;
    }

    public List<TelefonePessoa> getTelefonePessoas() {
	return telefonePessoas;
    }

    public void setTelefonePessoas(List<TelefonePessoa> telefonePessoas) {
	this.telefonePessoas = telefonePessoas;
    }

    public List<DocumentoPessoa> getDocumentoPessoa() {
	return documentoPessoa;
    }

    public void setDocumentoPessoa(List<DocumentoPessoa> documentoPessoa) {
	this.documentoPessoa = documentoPessoa;
    }

    public Date getDataHoraAtualizacao() {
	return dataHoraAtualizacao;
    }

    public void setDataHoraAtualizacao(Date dataHoraAtualizacao) {
	this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    public Date getDataHoraCadastro() {
	return dataHoraCadastro;
    }

    public void setDataHoraCadastro(Date dataHoraCadastro) {
	this.dataHoraCadastro = dataHoraCadastro;
    }

    public Integer getIdUsuarioCadastro() {
	return idUsuarioCadastro;
    }

    public void setIdUsuarioCadastro(Integer idUsuarioCadastro) {
	this.idUsuarioCadastro = idUsuarioCadastro;
    }

    public List<DoencaPessoa> getDoencaPessoa() {
	return doencaPessoa;
    }

    public void setDoencaPessoa(List<DoencaPessoa> doencaPessoa) {
	this.doencaPessoa = doencaPessoa;
    }

    public String getObservacao() {
	return observacao;
    }

    public String getNomeFantasia() {
	return nomeFantasia;
    }

    public String getNumInscEstadual() {
	return numInscEstadual;
    }

    public void setObservacao(String observacao) {
	this.observacao = observacao;
    }

    public void setNomeFantasia(String nomeFantasia) {
	this.nomeFantasia = nomeFantasia;
    }

    public void setNumInscEstadual(String numInscEstadual) {
	this.numInscEstadual = numInscEstadual;
    }

}