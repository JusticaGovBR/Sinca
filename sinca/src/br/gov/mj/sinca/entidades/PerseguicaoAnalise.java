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
 * The persistent class for the perseguicao_analise database table.
 * 
 */
@Entity
@Table(name = "perseguicao_analise")
@NamedQuery(name = "PerseguicaoAnalise.findAll", query = "SELECT p FROM PerseguicaoAnalise p")
public class PerseguicaoAnalise implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PERSEGUICAO")
    private Long idPerseguicao;

    @ManyToOne
    @JoinColumn(name = "COD_TIPO")
    private TipoPerseguicao tipoPerseguicao;

    private String complemento;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_FIM")
    private Date dataFim;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_INICIO")
    private Date dataInicio;

    private String descricao;

    private String local;

    @Column(name = "PAGINA_PROCESSO")
    private String paginaProcesso;

    // bi-directional many-to-one association to AnaliseProcesso
    @ManyToOne
    @JoinColumn(name = "ID_ANALISE")
    private AnaliseProcesso analiseProcesso;

    public PerseguicaoAnalise() {
    }

    public Long getIdPerseguicao() {
	return this.idPerseguicao;
    }

    public void setIdPerseguicao(Long idPerseguicao) {
	this.idPerseguicao = idPerseguicao;
    }

    public TipoPerseguicao getTipoPerseguicao() {
	return tipoPerseguicao;
    }

    public void setTipoPerseguicao(TipoPerseguicao tipoPerseguicao) {
	this.tipoPerseguicao = tipoPerseguicao;
    }

    public String getComplemento() {
	return this.complemento;
    }

    public void setComplemento(String complemento) {
	this.complemento = complemento;
    }

    public Date getDataFim() {
	return this.dataFim;
    }

    public void setDataFim(Date dataFim) {
	this.dataFim = dataFim;
    }

    public Date getDataInicio() {
	return this.dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
	this.dataInicio = dataInicio;
    }

    public String getDescricao() {
	return this.descricao;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    public String getLocal() {
	return this.local;
    }

    public void setLocal(String local) {
	this.local = local;
    }

    public String getPaginaProcesso() {
	return this.paginaProcesso;
    }

    public void setPaginaProcesso(String paginaProcesso) {
	this.paginaProcesso = paginaProcesso;
    }

    public AnaliseProcesso getAnaliseProcesso() {
	return this.analiseProcesso;
    }

    public void setAnaliseProcesso(AnaliseProcesso analiseProcesso) {
	this.analiseProcesso = analiseProcesso;
    }

}