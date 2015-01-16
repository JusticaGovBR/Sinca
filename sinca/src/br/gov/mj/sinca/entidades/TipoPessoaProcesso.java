package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the tipo_pessoa_processo database table.
 * 
 */
@Entity
@Table(name = "tipo_pessoa_processo")
@NamedQuery(name = "TipoPessoaProcesso.findAll", query = "SELECT t FROM TipoPessoaProcesso t")
public class TipoPessoaProcesso implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5941086721904138694L;

    @Id
    @Column(name = "ID_TIPO_PESSOA_PROCESSO")
    private Integer idTipoPessoaProcesso;

    @Column(name = "DESC_TIPO")
    private String descTipo;

    @OneToMany(mappedBy = "tipoPessoaProcesso")
    private List<PessoaProcesso> pessoaProcessos;

    public TipoPessoaProcesso() {
    }

    public TipoPessoaProcesso(Integer idTipoPessoaProcesso) {
	super();
	this.idTipoPessoaProcesso = idTipoPessoaProcesso;
    }

    public Integer getIdTipoPessoaProcesso() {
	return this.idTipoPessoaProcesso;
    }

    public void setIdTipoPessoaProcesso(Integer idTipoPessoaProcesso) {
	this.idTipoPessoaProcesso = idTipoPessoaProcesso;
    }

    public String getDescTipo() {
	return this.descTipo;
    }

    public void setDescTipo(String descTipo) {
	this.descTipo = descTipo;
    }

    public List<PessoaProcesso> getPessoaProcessos() {
	return this.pessoaProcessos;
    }

    public void setPessoaProcessos(List<PessoaProcesso> pessoaProcessos) {
	this.pessoaProcessos = pessoaProcessos;
    }

    public PessoaProcesso addPessoaProcesso(PessoaProcesso pessoaProcesso) {
	getPessoaProcessos().add(pessoaProcesso);
	pessoaProcesso.setTipoPessoaProcesso(this);

	return pessoaProcesso;
    }

    public PessoaProcesso removePessoaProcesso(PessoaProcesso pessoaProcesso) {
	getPessoaProcessos().remove(pessoaProcesso);
	pessoaProcesso.setTipoPessoaProcesso(null);

	return pessoaProcesso;
    }

}