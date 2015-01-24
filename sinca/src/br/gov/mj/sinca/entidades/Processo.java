package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
 * The persistent class for the processo database table.
 * 
 */
@Entity
@Table(name = "processo")
@NamedQuery(name = "Processo.findAll", query = "SELECT p FROM Processo p")
public class Processo implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = -6371588063806171655L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROCESSO", unique = true, nullable = false)
    private Long idProcesso;

    @Column(name = "NUM_PROTOCOLO_CA")
    private int numProtocoloCa;
    
    @Column(name = "NUM_PROTOCOLO_MJ", length = 20)
    private String numProtocoloMj;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_PROTOCOLO_CA")
    private Date dataProtocoloCa;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_PROTOCOLO_MJ")
    private Date dataProtocoloMJ;


    @Column(name = "ID_LOCALIZACAO")
    private Integer idLocalizacao;


    // bi-directional many-to-one association to DadosAdicionais
    @OneToMany(mappedBy = "processo")
    private List<DadosAdicionais> dadosAdicionais;

    // bi-directional many-to-one association to DadosAdicionais
    @OneToMany(mappedBy = "processo")
    private List<PessoaProcesso> pessoaProcesso;

    // bi-directional many-to-one association to CargoFuncao
    @ManyToOne
    @JoinColumn(name = "ID_CARGO_FUNCAO")
    private CargoFuncao cargoFuncao;

    // bi-directional many-to-one association to GrupoProcessual
    @ManyToOne
    @JoinColumn(name = "ID_GRUPO_PROCESSUAL")
    private GrupoProcessual grupoProcessual;

    // bi-directional many-to-one association to GrupoSocial
    @ManyToOne
    @JoinColumn(name = "ID_GRUPO_SOCIAL")
    private GrupoSocial grupoSocial;

    // bi-directional many-to-one association to StatusProcesso
    @ManyToOne
    @JoinColumn(name = "ID_STATUS_PROCESSO")
    private StatusProcesso statusProcesso;

    // bi-directional many-to-one association to StatusProcesso
    @ManyToOne
    @JoinColumn(name = "COD_SITUACAO_CADASTRO")
    private SituacaoCadastro situacaoCadastro;

    // bi-directional many-to-one association to SubGrupoProcessual
    @ManyToOne
    @JoinColumn(name = "ID_SUB_GRUPO_PROCESSUAL")
    private SubGrupoProcessual subGrupoProcessual;

    // bi-directional many-to-one association to SubGrupoSocial
    @ManyToOne
    @JoinColumn(name = "ID_SUB_GRUPO_SOCIAL")
    private SubGrupoSocial subGrupoSocial;

    // bi-directional many-to-one association to SubStatusProcesso
    @ManyToOne
    @JoinColumn(name = "ID_SUB_STATUS_PROCESSO")
    private SubStatusProcesso subStatusProcesso;
    
    @Column(name = "DESC_COMPLEMENTO_STATUS", length = 1000)
    private String descComplementoStatus;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_CADASTRO")
    private Date dataCadastro;

    @Column(name = "DATA_HORA_INCLUSAO", nullable = false)
    private Timestamp dataHoraInclusao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_HORA_ATUALIZACAO")
    private Date dataHoraAtualizacao;

    // bi-directional many-to-one association to Usuario
    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usario;

    
    public Processo() {
    }

    public List<PessoaProcesso> getPessoaProcesso() {
	return pessoaProcesso;
    }

    public void setPessoaProcesso(List<PessoaProcesso> pessoaProcesso) {
	this.pessoaProcesso = pessoaProcesso;
    }

    public Long getIdProcesso() {
	return this.idProcesso;
    }

    public void setIdProcesso(Long idProcesso) {
	this.idProcesso = idProcesso;
    }

    public Date getDataCadastro() {
	return this.dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
	this.dataCadastro = dataCadastro;
    }

    public Timestamp getDataHoraInclusao() {
	return this.dataHoraInclusao;
    }

    public void setDataHoraInclusao(Timestamp dataHoraInclusao) {
	this.dataHoraInclusao = dataHoraInclusao;
    }

    public Date getDataProtocoloCa() {
	return this.dataProtocoloCa;
    }

    public void setDataProtocoloCa(Date dataProtocoloCa) {
	this.dataProtocoloCa = dataProtocoloCa;
    }

    public Date getDataProtocoloMJ() {
	return dataProtocoloMJ;
    }

    public void setDataProtocoloMJ(Date dataProtocoloMJ) {
	this.dataProtocoloMJ = dataProtocoloMJ;
    }

    public String getDescComplementoStatus() {
	return this.descComplementoStatus;
    }

    public void setDescComplementoStatus(String descComplementoStatus) {
	this.descComplementoStatus = descComplementoStatus;
    }

    public Integer getIdLocalizacao() {
	return this.idLocalizacao;
    }

    public void setIdLocalizacao(Integer idLocalizacao) {
	this.idLocalizacao = idLocalizacao;
    }

    public int getNumProtocoloCa() {
	return this.numProtocoloCa;
    }

    public void setNumProtocoloCa(int numProtocoloCa) {
	this.numProtocoloCa = numProtocoloCa;
    }

    public String getNumProtocoloMj() {
	return this.numProtocoloMj;
    }

    public void setNumProtocoloMj(String numProtocoloMj) {
	this.numProtocoloMj = numProtocoloMj;
    }

    public List<DadosAdicionais> getDadosAdicionais() {
	return this.dadosAdicionais;
    }

    public void setDadosAdicionais(List<DadosAdicionais> dadosAdicionais) {
	this.dadosAdicionais = dadosAdicionais;
    }

    public DadosAdicionais addDadosAdicionai(DadosAdicionais dadosAdicionai) {
	getDadosAdicionais().add(dadosAdicionai);
	dadosAdicionai.setProcesso(this);

	return dadosAdicionai;
    }

    public DadosAdicionais removeDadosAdicionai(DadosAdicionais dadosAdicionai) {
	getDadosAdicionais().remove(dadosAdicionai);
	dadosAdicionai.setProcesso(null);

	return dadosAdicionai;
    }

    public CargoFuncao getCargoFuncao() {
	return this.cargoFuncao;
    }

    public void setCargoFuncao(CargoFuncao cargoFuncao) {
	this.cargoFuncao = cargoFuncao;
    }

    public GrupoProcessual getGrupoProcessual() {
	return this.grupoProcessual;
    }

    public void setGrupoProcessual(GrupoProcessual grupoProcessual) {
	this.grupoProcessual = grupoProcessual;
    }

    public GrupoSocial getGrupoSocial() {
	return this.grupoSocial;
    }

    public void setGrupoSocial(GrupoSocial grupoSocial) {
	this.grupoSocial = grupoSocial;
    }

    public StatusProcesso getStatusProcesso() {
	return this.statusProcesso;
    }

    public void setStatusProcesso(StatusProcesso statusProcesso) {
	this.statusProcesso = statusProcesso;
    }

    public SubGrupoProcessual getSubGrupoProcessual() {
	return this.subGrupoProcessual;
    }

    public void setSubGrupoProcessual(SubGrupoProcessual subGrupoProcessual) {
	this.subGrupoProcessual = subGrupoProcessual;
    }

    public SubGrupoSocial getSubGrupoSocial() {
	return this.subGrupoSocial;
    }

    public void setSubGrupoSocial(SubGrupoSocial subGrupoSocial) {
	this.subGrupoSocial = subGrupoSocial;
    }

    public SubStatusProcesso getSubStatusProcesso() {
	return this.subStatusProcesso;
    }

    public void setSubStatusProcesso(SubStatusProcesso subStatusProcesso) {
	this.subStatusProcesso = subStatusProcesso;
    }

    public Usuario getUsario() {
	return this.usario;
    }

    public void setUsario(Usuario usario) {
	this.usario = usario;
    }

    public Date getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    public void setDataHoraAtualizacao(Date dataHoraAtualizacao) {
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    public SituacaoCadastro getSituacaoCadastro() {
        return situacaoCadastro;
    }

    public void setSituacaoCadastro(SituacaoCadastro situacaoCadastro) {
        this.situacaoCadastro = situacaoCadastro;
    }
    

}