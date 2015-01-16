package br.gov.mj.sinca.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the pessoa_processo database table.
 * 
 */
@Entity
@Table(name = "pessoa_processo")
@NamedQuery(name = "PessoaProcesso.findAll", query = "SELECT p FROM PessoaProcesso p")
public class PessoaProcesso implements Serializable {

    private static final long serialVersionUID = -1637483991662155565L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PESSOA_PROCESSO")
    private Long idPessoaProcesso;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PESSOA")
    private Pessoa pessoa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PROCESSO")
    private Processo processo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_TIPO_PESSOA_PROCESSO")
    private TipoPessoaProcesso tipoPessoaProcesso;

    public PessoaProcesso() {
    }

    public Long getIdPessoaProcesso() {
	return this.idPessoaProcesso;
    }

    public void setIdPessoaProcesso(Long idPessoaProcesso) {
	this.idPessoaProcesso = idPessoaProcesso;
    }

    public Processo getProcesso() {
	return this.processo;
    }

    public void setProcesso(Processo processo) {
	this.processo = processo;
    }

    public TipoPessoaProcesso getTipoPessoaProcesso() {
	return this.tipoPessoaProcesso;
    }

    public void setTipoPessoaProcesso(TipoPessoaProcesso tipoPessoaProcesso) {
	this.tipoPessoaProcesso = tipoPessoaProcesso;
    }

    public Pessoa getPessoa() {
	return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
	this.pessoa = pessoa;
    }

}