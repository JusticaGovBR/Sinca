package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the analista_processo database table.
 * 
 */
@Entity
@Table(name = "analista_processo")
@NamedQuery(name = "AnalistaProcesso.findAll", query = "SELECT a FROM AnalistaProcesso a")
public class AnalistaProcesso implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ANALISTA_PROCESSO")
    private Long idAnalistaProcesso;

    @Column(name = "ID_ANALISE")
    private Long idAnalise;

    @ManyToOne
    @JoinColumn(name = "ID_PESSOA")
    private Pessoa pessoa;

    @Column(name = "ID_SUB_STATUS_PROCESSO")
    private Integer idSubStatusProcesso;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_CADASTRO")
    private Date dataCadastro;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_CONLUSAO")
    private Date dataConlusao;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_RECEBIMENTO")
    private Date dataRecebimento;

    private String acoes;

    private String complemento;

    public AnalistaProcesso() {
    }

    public Long getIdAnalistaProcesso() {
	return idAnalistaProcesso;
    }

    public Long getIdAnalise() {
	return idAnalise;
    }

    public Integer getIdSubStatusProcesso() {
	return idSubStatusProcesso;
    }

    public Date getDataCadastro() {
	return dataCadastro;
    }

    public Date getDataConlusao() {
	return dataConlusao;
    }

    public Date getDataRecebimento() {
	return dataRecebimento;
    }

    public String getAcoes() {
	return acoes;
    }

    public String getComplemento() {
	return complemento;
    }

    public void setIdAnalistaProcesso(Long idAnalistaProcesso) {
	this.idAnalistaProcesso = idAnalistaProcesso;
    }

    public void setIdAnalise(Long idAnalise) {
	this.idAnalise = idAnalise;
    }

    public void setIdSubStatusProcesso(Integer idSubStatusProcesso) {
	this.idSubStatusProcesso = idSubStatusProcesso;
    }

    public void setDataCadastro(Date dataCadastro) {
	this.dataCadastro = dataCadastro;
    }

    public void setDataConlusao(Date dataConlusao) {
	this.dataConlusao = dataConlusao;
    }

    public void setDataRecebimento(Date dataRecebimento) {
	this.dataRecebimento = dataRecebimento;
    }

    public void setAcoes(String acoes) {
	this.acoes = acoes;
    }

    public void setComplemento(String complemento) {
	this.complemento = complemento;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

}