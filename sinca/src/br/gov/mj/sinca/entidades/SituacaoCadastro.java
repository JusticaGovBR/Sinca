package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the situacao_profissional database table.
 * 
 */
@Entity
@Table(name = "situacao_cadastro")
@NamedQuery(name = "SituacaoCadastro.findAll", query = "SELECT s FROM SituacaoCadastro s")
public class SituacaoCadastro implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "COD_SITUACAO_CADASTRO")
    private Integer codSituacao;

    @Column(name = "DESCRICAO")
    private String descSituacao;

    public SituacaoCadastro() {
    }

    
    public Integer getCodSituacao() {
        return codSituacao;
    }

    public void setCodSituacao(Integer codSituacao) {
        this.codSituacao = codSituacao;
    }



    public String getDescSituacao() {
	return this.descSituacao;
    }

    public void setDescSituacao(String descSituacao) {
	this.descSituacao = descSituacao;
    }

}