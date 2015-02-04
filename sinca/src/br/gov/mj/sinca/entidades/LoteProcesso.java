package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the lote_processo database table.
 * 
 */
@Entity
@Table(name="lote_processo")
@NamedQuery(name="LoteProcesso.findAll", query="SELECT l FROM LoteProcesso l")
public class LoteProcesso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_LOTE")
	private Integer idLote;
	
	@Column(name="DESCRICAO")
	private String descricao;
	
	@Column(name="DATA_CRIACAO")
	private Date dataCriacao;
	
	@Column(name="DATA_ATUALIZACAO")
	private Date dataAtualizacao;

	@Column(name="ID_USUARIO")
	private Integer idUsuario;

	@Column(name="ATIVO")
	private Byte ativo;


	public Byte getAtivo() {
	    return ativo;
	}


	public void setAtivo(Byte ativo) {
	    this.ativo = ativo;
	}


	public LoteProcesso() {
	}


	public Integer getIdLote() {
	    return idLote;
	}


	public String getDescricao() {
	    return descricao;
	}


	public Date getDataCriacao() {
	    return dataCriacao;
	}


	public Date getDataAtualizacao() {
	    return dataAtualizacao;
	}


	public Integer getIdUsuario() {
	    return idUsuario;
	}


	public void setIdLote(Integer idLote) {
	    this.idLote = idLote;
	}


	public void setDescricao(String descricao) {
	    this.descricao = descricao;
	}


	public void setDataCriacao(Date dataCriacao) {
	    this.dataCriacao = dataCriacao;
	}


	public void setDataAtualizacao(Date dataAtualizacao) {
	    this.dataAtualizacao = dataAtualizacao;
	}


	public void setIdUsuario(Integer idUsuario) {
	    this.idUsuario = idUsuario;
	}

	


}