package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dados_pcpa database table.
 * 
 */
@Entity
@Table(name="dados_pcpa")
@NamedQuery(name="DadosPcpa.findAll", query="SELECT d FROM DadosPcpa d")
public class DadosPcpa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String pcpa;

	private String ano_Consolida_planilhas;

	private String bairro_PCPA_S;

	private String bairro_requer_PCPA;

	@Column(name="CEP_PCPA_S")
	private String cepPcpaS;

	private String CEP_requer_PCPA;

	private String cidade_PCPA_S;

	private String cidade_requer_PCPA;

	@Column(name="com_decisao")
	private String comDecisao;

	private String CPF_anistiando;

	private String CPF_requer_PCPA;

	private String data_autuacao_PCPA;

	private String data_nascimento_anistiando;

	private String data_Nascimento_requer_PCPA;

	private String data_Portaria_anistia;

	private String dataNascimento_PCPA_S;

	private String decisao_Consolida_planilhas;

	private String descricao_Erro_Cadastro_Consolida;

	private String doenca_PCPA;

	private String endereco_PCPA_S;

	private String endereco_requer_PCPA;

	private String genero_anistiando;

	private String genero_requer_PCPA;

	private String grupo_PCPA_S;

	private String grupo_requer_PCPA;

	private String nome_anistiando;

	private String nome_Mae_requer_PCPA;

	private String nome_PCPA_S;

	private String nome_requer_PCPA;

	private String nomeMae_PCPA_S;

	private String NPortaria_anistia;

	private String numeroCPF_PCPA_S;

	private String procurador_requer_PCPA;

	private String sexo_PCPA_S;

	private String subGrupo_PCPA_S;

	private String subGrupo_requer_PCPA;

	private String telefone_PCPA_S;

	private String telefone_requer_PCPA;

	private String tipo_Requerente_PCPA_S;

	@Column(name="UF_PCPA_S")
	private String ufPcpaS;

	private String UF_requer_PCPA;

	private String valo_PMPC_Consolida;

	private String valor_PU_Consolida;

	private String valor_retoativo_Consolida;

	public DadosPcpa() {
	}

	public String getPcpa() {
		return this.pcpa;
	}

	public void setPcpa(String pcpa) {
		this.pcpa = pcpa;
	}

	public String getAno_Consolida_planilhas() {
		return this.ano_Consolida_planilhas;
	}

	public void setAno_Consolida_planilhas(String ano_Consolida_planilhas) {
		this.ano_Consolida_planilhas = ano_Consolida_planilhas;
	}

	public String getBairro_PCPA_S() {
		return this.bairro_PCPA_S;
	}

	public void setBairro_PCPA_S(String bairro_PCPA_S) {
		this.bairro_PCPA_S = bairro_PCPA_S;
	}

	public String getBairro_requer_PCPA() {
		return this.bairro_requer_PCPA;
	}

	public void setBairro_requer_PCPA(String bairro_requer_PCPA) {
		this.bairro_requer_PCPA = bairro_requer_PCPA;
	}

	public String getCepPcpaS() {
		return this.cepPcpaS;
	}

	public void setCepPcpaS(String cepPcpaS) {
		this.cepPcpaS = cepPcpaS;
	}

	public String getCEP_requer_PCPA() {
		return this.CEP_requer_PCPA;
	}

	public void setCEP_requer_PCPA(String CEP_requer_PCPA) {
		this.CEP_requer_PCPA = CEP_requer_PCPA;
	}

	public String getCidade_PCPA_S() {
		return this.cidade_PCPA_S;
	}

	public void setCidade_PCPA_S(String cidade_PCPA_S) {
		this.cidade_PCPA_S = cidade_PCPA_S;
	}

	public String getCidade_requer_PCPA() {
		return this.cidade_requer_PCPA;
	}

	public void setCidade_requer_PCPA(String cidade_requer_PCPA) {
		this.cidade_requer_PCPA = cidade_requer_PCPA;
	}

	public String getComDecisao() {
		return this.comDecisao;
	}

	public void setComDecisao(String comDecisao) {
		this.comDecisao = comDecisao;
	}

	public String getCPF_anistiando() {
		return this.CPF_anistiando;
	}

	public void setCPF_anistiando(String CPF_anistiando) {
		this.CPF_anistiando = CPF_anistiando;
	}

	public String getCPF_requer_PCPA() {
		return this.CPF_requer_PCPA;
	}

	public void setCPF_requer_PCPA(String CPF_requer_PCPA) {
		this.CPF_requer_PCPA = CPF_requer_PCPA;
	}

	public String getData_autuacao_PCPA() {
		return this.data_autuacao_PCPA;
	}

	public void setData_autuacao_PCPA(String data_autuacao_PCPA) {
		this.data_autuacao_PCPA = data_autuacao_PCPA;
	}

	public String getData_nascimento_anistiando() {
		return this.data_nascimento_anistiando;
	}

	public void setData_nascimento_anistiando(String data_nascimento_anistiando) {
		this.data_nascimento_anistiando = data_nascimento_anistiando;
	}

	public String getData_Nascimento_requer_PCPA() {
		return this.data_Nascimento_requer_PCPA;
	}

	public void setData_Nascimento_requer_PCPA(String data_Nascimento_requer_PCPA) {
		this.data_Nascimento_requer_PCPA = data_Nascimento_requer_PCPA;
	}

	public String getData_Portaria_anistia() {
		return this.data_Portaria_anistia;
	}

	public void setData_Portaria_anistia(String data_Portaria_anistia) {
		this.data_Portaria_anistia = data_Portaria_anistia;
	}

	public String getDataNascimento_PCPA_S() {
		return this.dataNascimento_PCPA_S;
	}

	public void setDataNascimento_PCPA_S(String dataNascimento_PCPA_S) {
		this.dataNascimento_PCPA_S = dataNascimento_PCPA_S;
	}

	public String getDecisao_Consolida_planilhas() {
		return this.decisao_Consolida_planilhas;
	}

	public void setDecisao_Consolida_planilhas(String decisao_Consolida_planilhas) {
		this.decisao_Consolida_planilhas = decisao_Consolida_planilhas;
	}

	public String getDescricao_Erro_Cadastro_Consolida() {
		return this.descricao_Erro_Cadastro_Consolida;
	}

	public void setDescricao_Erro_Cadastro_Consolida(String descricao_Erro_Cadastro_Consolida) {
		this.descricao_Erro_Cadastro_Consolida = descricao_Erro_Cadastro_Consolida;
	}

	public String getDoenca_PCPA() {
		return this.doenca_PCPA;
	}

	public void setDoenca_PCPA(String doenca_PCPA) {
		this.doenca_PCPA = doenca_PCPA;
	}

	public String getEndereco_PCPA_S() {
		return this.endereco_PCPA_S;
	}

	public void setEndereco_PCPA_S(String endereco_PCPA_S) {
		this.endereco_PCPA_S = endereco_PCPA_S;
	}

	public String getEndereco_requer_PCPA() {
		return this.endereco_requer_PCPA;
	}

	public void setEndereco_requer_PCPA(String endereco_requer_PCPA) {
		this.endereco_requer_PCPA = endereco_requer_PCPA;
	}

	public String getGenero_anistiando() {
		return this.genero_anistiando;
	}

	public void setGenero_anistiando(String genero_anistiando) {
		this.genero_anistiando = genero_anistiando;
	}

	public String getGenero_requer_PCPA() {
		return this.genero_requer_PCPA;
	}

	public void setGenero_requer_PCPA(String genero_requer_PCPA) {
		this.genero_requer_PCPA = genero_requer_PCPA;
	}

	public String getGrupo_PCPA_S() {
		return this.grupo_PCPA_S;
	}

	public void setGrupo_PCPA_S(String grupo_PCPA_S) {
		this.grupo_PCPA_S = grupo_PCPA_S;
	}

	public String getGrupo_requer_PCPA() {
		return this.grupo_requer_PCPA;
	}

	public void setGrupo_requer_PCPA(String grupo_requer_PCPA) {
		this.grupo_requer_PCPA = grupo_requer_PCPA;
	}

	public String getNome_anistiando() {
		return this.nome_anistiando;
	}

	public void setNome_anistiando(String nome_anistiando) {
		this.nome_anistiando = nome_anistiando;
	}

	public String getNome_Mae_requer_PCPA() {
		return this.nome_Mae_requer_PCPA;
	}

	public void setNome_Mae_requer_PCPA(String nome_Mae_requer_PCPA) {
		this.nome_Mae_requer_PCPA = nome_Mae_requer_PCPA;
	}

	public String getNome_PCPA_S() {
		return this.nome_PCPA_S;
	}

	public void setNome_PCPA_S(String nome_PCPA_S) {
		this.nome_PCPA_S = nome_PCPA_S;
	}

	public String getNome_requer_PCPA() {
		return this.nome_requer_PCPA;
	}

	public void setNome_requer_PCPA(String nome_requer_PCPA) {
		this.nome_requer_PCPA = nome_requer_PCPA;
	}

	public String getNomeMae_PCPA_S() {
		return this.nomeMae_PCPA_S;
	}

	public void setNomeMae_PCPA_S(String nomeMae_PCPA_S) {
		this.nomeMae_PCPA_S = nomeMae_PCPA_S;
	}

	public String getNPortaria_anistia() {
		return this.NPortaria_anistia;
	}

	public void setNPortaria_anistia(String NPortaria_anistia) {
		this.NPortaria_anistia = NPortaria_anistia;
	}

	public String getNumeroCPF_PCPA_S() {
		return this.numeroCPF_PCPA_S;
	}

	public void setNumeroCPF_PCPA_S(String numeroCPF_PCPA_S) {
		this.numeroCPF_PCPA_S = numeroCPF_PCPA_S;
	}

	public String getProcurador_requer_PCPA() {
		return this.procurador_requer_PCPA;
	}

	public void setProcurador_requer_PCPA(String procurador_requer_PCPA) {
		this.procurador_requer_PCPA = procurador_requer_PCPA;
	}

	public String getSexo_PCPA_S() {
		return this.sexo_PCPA_S;
	}

	public void setSexo_PCPA_S(String sexo_PCPA_S) {
		this.sexo_PCPA_S = sexo_PCPA_S;
	}

	public String getSubGrupo_PCPA_S() {
		return this.subGrupo_PCPA_S;
	}

	public void setSubGrupo_PCPA_S(String subGrupo_PCPA_S) {
		this.subGrupo_PCPA_S = subGrupo_PCPA_S;
	}

	public String getSubGrupo_requer_PCPA() {
		return this.subGrupo_requer_PCPA;
	}

	public void setSubGrupo_requer_PCPA(String subGrupo_requer_PCPA) {
		this.subGrupo_requer_PCPA = subGrupo_requer_PCPA;
	}

	public String getTelefone_PCPA_S() {
		return this.telefone_PCPA_S;
	}

	public void setTelefone_PCPA_S(String telefone_PCPA_S) {
		this.telefone_PCPA_S = telefone_PCPA_S;
	}

	public String getTelefone_requer_PCPA() {
		return this.telefone_requer_PCPA;
	}

	public void setTelefone_requer_PCPA(String telefone_requer_PCPA) {
		this.telefone_requer_PCPA = telefone_requer_PCPA;
	}

	public String getTipo_Requerente_PCPA_S() {
		return this.tipo_Requerente_PCPA_S;
	}

	public void setTipo_Requerente_PCPA_S(String tipo_Requerente_PCPA_S) {
		this.tipo_Requerente_PCPA_S = tipo_Requerente_PCPA_S;
	}

	public String getUfPcpaS() {
		return this.ufPcpaS;
	}

	public void setUfPcpaS(String ufPcpaS) {
		this.ufPcpaS = ufPcpaS;
	}

	public String getUF_requer_PCPA() {
		return this.UF_requer_PCPA;
	}

	public void setUF_requer_PCPA(String UF_requer_PCPA) {
		this.UF_requer_PCPA = UF_requer_PCPA;
	}

	public String getValo_PMPC_Consolida() {
		return this.valo_PMPC_Consolida;
	}

	public void setValo_PMPC_Consolida(String valo_PMPC_Consolida) {
		this.valo_PMPC_Consolida = valo_PMPC_Consolida;
	}

	public String getValor_PU_Consolida() {
		return this.valor_PU_Consolida;
	}

	public void setValor_PU_Consolida(String valor_PU_Consolida) {
		this.valor_PU_Consolida = valor_PU_Consolida;
	}

	public String getValor_retoativo_Consolida() {
		return this.valor_retoativo_Consolida;
	}

	public void setValor_retoativo_Consolida(String valor_retoativo_Consolida) {
		this.valor_retoativo_Consolida = valor_retoativo_Consolida;
	}

}