package br.gov.mj.sinca.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the tipo_endereco database table.
 * 
 */
@Entity
@Table(name = "tipo_pessoa")
@NamedQuery(name = "TipoEndereco.java.findAll", query = "SELECT t FROM TipoPessoa t")
public class TipoPessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "COD_TIPO")
    private Integer codTipo;

    @Column(name="DESCRICAO")
    private String descricao;

    public TipoPessoa() {
    }

    public Integer getCodTipo() {
        return codTipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setCodTipo(Integer codTipo) {
        this.codTipo = codTipo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


}