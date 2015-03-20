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


/**
 * The persistent class for the lote_processo database table.
 * 
 */
@Entity
@Table(name="presentes_julgamento")
@NamedQuery(name="PresentesJulgamento.findAll", query="SELECT l FROM PresentesJulgamento l")
public class PresentesJulgamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PRESENTES")
	private Long idPresentes;
	
	@ManyToOne
	@JoinColumn(name="ID_JULGAMENTO_PROCESSO")
	private JulgamentoProcesso julgamentoProcesso;
	
	@Column(name="NOME")
	private String nome;
	
	@Column(name="DATA_CADASTRO")
	private Date dataCadastro;
	
	@Column(name="DATA_ATUALIZACAO")
	private Date dataAtualizacao;

	@Column(name="ID_USUARIO")
	private Integer idUsuario;

	public Long getIdPresentes() {
	    return idPresentes;
	}

	public JulgamentoProcesso getJulgamentoProcesso() {
	    return julgamentoProcesso;
	}

	public String getNome() {
	    return nome;
	}

	public Date getDataAtualizacao() {
	    return dataAtualizacao;
	}

	public Integer getIdUsuario() {
	    return idUsuario;
	}

	public void setIdPresentes(Long idPresentes) {
	    this.idPresentes = idPresentes;
	}

	public void setJulgamentoProcesso(JulgamentoProcesso julgamentoProcesso) {
	    this.julgamentoProcesso = julgamentoProcesso;
	}

	public void setNome(String nome) {
	    this.nome = nome;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
	    this.dataAtualizacao = dataAtualizacao;
	}

	public void setIdUsuario(Integer idUsuario) {
	    this.idUsuario = idUsuario;
	}

	public Date getDataCadastro() {
	    return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
	    this.dataCadastro = dataCadastro;
	}



}