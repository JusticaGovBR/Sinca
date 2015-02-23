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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the processo database table.
 * 
 */
@Entity
@NamedQuery(name = "Processo.findAll", query = "SELECT p FROM Processo p")
public class Processo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2431687116096187362L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROCESSO")
    private Long idProcesso;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_CADASTRO")
    private Date dataCadastro;

    @Column(name = "DATA_HORA_ATUALIZACAO")
    private Timestamp dataHoraAtualizacao;

    @Column(name = "DATA_HORA_INCLUSAO")
    private Timestamp dataHoraInclusao;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_PROTOCOLO_CA")
    private Date dataProtocoloCa;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_PROTOCOLO_MJ")
    private Date dataProtocoloMJ;

    @Column(name = "DESC_COMPLEMENTO_STATUS")
    private String descComplementoStatus;

    @Column(name = "ID_LOCALIZACAO")
    private Long idLocalizacao;

    @Column(name = "NUM_PROTOCOLO_CA")
    private Integer numProtocoloCa;

    @Column(name = "NUM_PROTOCOLO_MJ")
    private String numProtocoloMj;

    @Column(name = "PRIORIDADE_BLOCO")
    private String prioridadeBloco;

    @Column(name = "PRIORIDADE_PLENARIO")
    private String prioridadePlenario;

    @Column(name = "PRIORIDADE_TURMA")
    private String prioridadeTurma;

    @OneToMany(mappedBy = "processo")
    private List<PessoaProcesso> pessoaProcessos;

    @ManyToOne
    @JoinColumn(name = "ID_CARGO_FUNCAO")
    private CargoFuncao cargoFuncao;

    @ManyToOne
    @JoinColumn(name = "ID_GRUPO_PROCESSUAL")
    private GrupoProcessual grupoProcessual;

    @ManyToOne
    @JoinColumn(name = "ID_GRUPO_SOCIAL")
    private GrupoSocial grupoSocial;

    @ManyToOne
    @JoinColumn(name = "ID_STATUS_PROCESSO")
    private StatusProcesso statusProcesso;

    @ManyToOne
    @JoinColumn(name = "ID_SUB_GRUPO_PROCESSUAL")
    private SubGrupoProcessual subGrupoProcessual;

    @ManyToOne
    @JoinColumn(name = "ID_SUB_GRUPO_SOCIAL")
    private SubGrupoSocial subGrupoSocial;

    @ManyToOne
    @JoinColumn(name = "ID_SUB_STATUS_PROCESSO")
    private SubStatusProcesso subStatusProcesso;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;
    
    @Column(name = "ID_USUARIO_COMFIRMACAO")
    private Integer idUsuarioComfirmacao;


    @ManyToOne
    @JoinColumn(name = "ID_LOTE")
    private LoteProcesso loteProcesso;

    @ManyToOne
    @JoinColumn(name = "COD_SITUACAO_CADASTRO")
    private SituacaoCadastro situacaoCadastro;

    public Processo() {
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

    public Timestamp getDataHoraAtualizacao() {
	return this.dataHoraAtualizacao;
    }

    public void setDataHoraAtualizacao(Timestamp dataHoraAtualizacao) {
	this.dataHoraAtualizacao = dataHoraAtualizacao;
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
	return this.dataProtocoloMJ;
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

    public Long getIdLocalizacao() {
	return this.idLocalizacao;
    }

    public void setIdLocalizacao(Long idLocalizacao) {
	this.idLocalizacao = idLocalizacao;
    }

    public Integer getNumProtocoloCa() {
	return this.numProtocoloCa;
    }

    public void setNumProtocoloCa(Integer numProtocoloCa) {
	this.numProtocoloCa = numProtocoloCa;
    }

    public String getNumProtocoloMj() {
	return this.numProtocoloMj;
    }

    public void setNumProtocoloMj(String numProtocoloMj) {
	this.numProtocoloMj = numProtocoloMj;
    }

    public String getPrioridadeBloco() {
	return this.prioridadeBloco;
    }

    public void setPrioridadeBloco(String prioridadeBloco) {
	this.prioridadeBloco = prioridadeBloco;
    }

    public String getPrioridadePlenario() {
	return this.prioridadePlenario;
    }

    public void setPrioridadePlenario(String prioridadePlenario) {
	this.prioridadePlenario = prioridadePlenario;
    }

    public String getPrioridadeTurma() {
	return this.prioridadeTurma;
    }

    public void setPrioridadeTurma(String prioridadeTurma) {
	this.prioridadeTurma = prioridadeTurma;
    }


    public Integer getIdUsuarioComfirmacao() {
        return idUsuarioComfirmacao;
    }

    public void setIdUsuarioComfirmacao(Integer idUsuarioComfirmacao) {
        this.idUsuarioComfirmacao = idUsuarioComfirmacao;
    }

    public List<PessoaProcesso> getPessoaProcessos() {
	return this.pessoaProcessos;
    }

    public void setPessoaProcessos(List<PessoaProcesso> pessoaProcessos) {
	this.pessoaProcessos = pessoaProcessos;
    }

    public PessoaProcesso addPessoaProcesso(PessoaProcesso pessoaProcesso) {
	getPessoaProcessos().add(pessoaProcesso);
	pessoaProcesso.setProcesso(this);

	return pessoaProcesso;
    }

    public PessoaProcesso removePessoaProcesso(PessoaProcesso pessoaProcesso) {
	getPessoaProcessos().remove(pessoaProcesso);
	pessoaProcesso.setProcesso(null);

	return pessoaProcesso;
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

    public Usuario getUsuario() {
	return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

    public LoteProcesso getLoteProcesso() {
	return this.loteProcesso;
    }

    public void setLoteProcesso(LoteProcesso loteProcesso) {
	this.loteProcesso = loteProcesso;
    }

    public SituacaoCadastro getSituacaoCadastro() {
	return this.situacaoCadastro;
    }

    public void setSituacaoCadastro(SituacaoCadastro situacaoCadastro) {
	this.situacaoCadastro = situacaoCadastro;
    }

}