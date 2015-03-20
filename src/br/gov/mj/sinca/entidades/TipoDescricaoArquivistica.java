package br.gov.mj.sinca.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the tipo_descricao_arquivistica database table.
 * 
 */
@Entity
@Table(name = "tipo_descricao_arquivistica")
@NamedQuery(name = "TipoDescricaoArquivistica.findAll", query = "SELECT t FROM TipoDescricaoArquivistica t")
public class TipoDescricaoArquivistica implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DESCRICAO_ARQUIVISTICA")
    private Integer idDescricaoArquivistica;

    private String decricao;

    public TipoDescricaoArquivistica() {
    }

    public Integer getIdDescricaoArquivistica() {
	return this.idDescricaoArquivistica;
    }

    public void setIdDescricaoArquivistica(Integer idDescricaoArquivistica) {
	this.idDescricaoArquivistica = idDescricaoArquivistica;
    }

    public String getDecricao() {
	return this.decricao;
    }

    public void setDecricao(String decricao) {
	this.decricao = decricao;
    }

}