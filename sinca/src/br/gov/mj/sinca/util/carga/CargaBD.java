package br.gov.mj.sinca.util.carga;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;

import br.gov.mj.sinca.dao.DadosPcpaDAO;
import br.gov.mj.sinca.dao.EnderecoDAO;
import br.gov.mj.sinca.dao.GrupoProcesssualDAO;
import br.gov.mj.sinca.dao.PessoaDAO;
import br.gov.mj.sinca.dao.PessoaEnderecoDAO;
import br.gov.mj.sinca.dao.PessoaProcessoDAO;
import br.gov.mj.sinca.dao.ProcessoDAO;
import br.gov.mj.sinca.dao.SubGrupoProcesssualDAO;
import br.gov.mj.sinca.dao.TelefonePessoaDAO;
import br.gov.mj.sinca.dao.TipoEnderecoDAO;
import br.gov.mj.sinca.dao.TipoPessoaProcessoDAO;
import br.gov.mj.sinca.dao.TipoTelefoneDAO;
import br.gov.mj.sinca.entidades.DadosPcpa;
import br.gov.mj.sinca.entidades.Endereco;
import br.gov.mj.sinca.entidades.GrupoProcessual;
import br.gov.mj.sinca.entidades.Pessoa;
import br.gov.mj.sinca.entidades.PessoaEndereco;
import br.gov.mj.sinca.entidades.PessoaProcesso;
import br.gov.mj.sinca.entidades.Processo;
import br.gov.mj.sinca.entidades.SubGrupoProcessual;
import br.gov.mj.sinca.entidades.TelefonePessoa;
import br.gov.mj.sinca.entidades.TipoPessoaProcesso;
import br.gov.mj.sinca.entidades.TipoTelefone;
import br.gov.mj.sinca.util.CpfCnpjUtil;

public class CargaBD {

    public static void main(String[] args) {
	DadosPcpaDAO pcpaDAO = new DadosPcpaDAO();
	Map<String, GrupoProcessual> mapGrupo = carregaMapaGrupoProcessual();
	Map<String, SubGrupoProcessual> mapSubGrupo = carregaMapaSubGrupoProcessual();

	List<DadosPcpa> dados = pcpaDAO.lerTodos();

	for (DadosPcpa pcpa : dados) {
	    try {
		if (Integer.valueOf(pcpa.getPcpa()) > 25194) {

		    Processo processo = new Processo();
		    processo.setNumProtocoloCa(Integer.valueOf(pcpa.getPcpa()));
		    setaDatas(pcpa, processo);
		    if (mapGrupo.containsKey(pcpa.getGrupo_requer_PCPA())) {
			processo.setGrupoProcessual(mapGrupo.get(pcpa.getGrupo_requer_PCPA()));
		    } else {
			processo.setGrupoProcessual(mapGrupo.get("Não informado"));

		    }
		    if (mapSubGrupo.containsKey(pcpa.getSubGrupo_requer_PCPA())) {
			processo.setSubGrupoProcessual(mapSubGrupo.get(pcpa.getSubGrupo_requer_PCPA()));
		    } else {
			processo.setSubGrupoProcessual(mapSubGrupo.get("Não informado"));
		    }
		    new ProcessoDAO().abrirTransacao();
		    processo.setDataHoraInclusao(new Timestamp(new Date().getTime()));
		    processo = new ProcessoDAO().salvar(processo);
		    
		    
		    Pessoa pessoaReq = new Pessoa();
		    pessoaReq.setDataHoraCadastro(new Date());
		    pessoaReq.setDataNascimento(setaDataPessoa(pcpa.getData_Nascimento_requer_PCPA()));
		    if (pcpa.getCPF_requer_PCPA() != null && pcpa.getCPF_requer_PCPA().length() >= 10) {
			pessoaReq.setNumCpfCnpj(CpfCnpjUtil.FormatCPF(pcpa.getCPF_requer_PCPA()));
		    } else {
			pessoaReq.setNumCpfCnpj(pcpa.getCPF_requer_PCPA());
		    }

		    pessoaReq.setNomePessoa(pcpa.getNome_requer_PCPA());
		    pessoaReq.setNomeMae(pcpa.getNome_Mae_requer_PCPA());
		    if (pcpa.getGenero_requer_PCPA() != null && pcpa.getGenero_requer_PCPA().length() < 2) {
			pessoaReq.setGenero(pcpa.getGenero_requer_PCPA());
		    }
		    pessoaReq = new PessoaDAO().salvar(pessoaReq);

		    Endereco endReq = new Endereco();
		    endReq.setLogradouro(pcpa.getEndereco_requer_PCPA());
		    endReq.setCidade(pcpa.getCidade_requer_PCPA());
		    endReq.setBairro(pcpa.getBairro_requer_PCPA());
		    endReq.setCep(pcpa.getCEP_requer_PCPA());
		    endReq.setUf(pcpa.getUF_requer_PCPA());
		    endReq.setTipoEndereco(new TipoEnderecoDAO().lerPorId(4));
		    endReq = new EnderecoDAO().salvar(endReq);

		    TelefonePessoa teleReq = new TelefonePessoa();
		    if(pcpa.getTelefone_requer_PCPA()!=null && pcpa.getTelefone_requer_PCPA().length()<20){
		    teleReq.setNumTelefone(pcpa.getTelefone_requer_PCPA());
		    teleReq.setTipoTelefone(new TipoTelefoneDAO().lerPorId(5));
		    teleReq.setPessoa(pessoaReq);
		    teleReq = new TelefonePessoaDAO().salvar(teleReq);
		    }

		    PessoaEndereco pessaEndReq = new PessoaEndereco();
		    pessaEndReq.setPessoa(pessoaReq);
		    pessaEndReq.setEndereco(endReq);
		    pessaEndReq = new PessoaEnderecoDAO().salvar(pessaEndReq);

		    PessoaProcesso pessoaProcReq = new PessoaProcesso();
		    pessoaProcReq.setProcesso(processo);
		    pessoaProcReq.setPessoa(pessoaReq);
		    pessoaProcReq.setTipoPessoaProcesso(new TipoPessoaProcessoDAO().lerPorId(2));
		    pessoaProcReq = new PessoaProcessoDAO().salvar(pessoaProcReq);

		    if (pcpa.getNome_anistiando() != null && !pcpa.getNome_anistiando().equals("")) {
			Pessoa pessoaAnist = new Pessoa();
			pessoaAnist.setDataHoraCadastro(new Date());
			pessoaAnist.setDataNascimento(setaDataPessoa(pcpa.getData_nascimento_anistiando()));

			if (pcpa.getCPF_anistiando() != null && pcpa.getCPF_anistiando().length() >= 10) {
			    pessoaAnist.setNumCpfCnpj(CpfCnpjUtil.FormatCPF(pcpa.getCPF_anistiando()));
			} else {
			    pessoaAnist.setNumCpfCnpj((pcpa.getCPF_anistiando()));
			}

			pessoaAnist.setNomePessoa(pcpa.getNome_anistiando());
			pessoaAnist.setGenero(pcpa.getGenero_anistiando());
			pessoaAnist = new PessoaDAO().salvar(pessoaAnist);

			PessoaProcesso pessoaProcAnist = new PessoaProcesso();
			pessoaProcAnist.setProcesso(processo);
			pessoaProcAnist.setPessoa(pessoaAnist);
			pessoaProcAnist.setTipoPessoaProcesso(new TipoPessoaProcessoDAO().lerPorId(1));
			pessoaProcAnist = new PessoaProcessoDAO().salvar(pessoaProcAnist);
		    }

		    // Requerente Secundário
		    if (pcpa.getNome_PCPA_S() != null) {
			Pessoa pessoaReqS = new Pessoa();
			pessoaReqS.setDataHoraCadastro(new Date());
			pessoaReqS.setDataNascimento(setaDataPessoa(pcpa.getDataNascimento_PCPA_S()));

			if (pcpa.getNumeroCPF_PCPA_S() != null && pcpa.getNumeroCPF_PCPA_S().length() >= 10) {
			    pessoaReqS.setNumCpfCnpj(CpfCnpjUtil.FormatCPF(pcpa.getNumeroCPF_PCPA_S()));
			} else {
			    pessoaReqS.setNumCpfCnpj(pcpa.getNumeroCPF_PCPA_S());
			}
			pessoaReqS.setNomePessoa(pcpa.getNome_PCPA_S());
			pessoaReqS.setNomeMae(pcpa.getNomeMae_PCPA_S());
			if (pcpa.getSexo_PCPA_S() != null && pcpa.getSexo_PCPA_S().length() < 2) {
			    pessoaReqS.setGenero(pcpa.getSexo_PCPA_S());
			}
			pessoaReqS = new PessoaDAO().salvar(pessoaReqS);

			endReq = new Endereco();
			endReq.setLogradouro(pcpa.getEndereco_PCPA_S());
			endReq.setCidade(pcpa.getCidade_PCPA_S());
			endReq.setBairro(pcpa.getBairro_PCPA_S());
			endReq.setCep(pcpa.getCepPcpaS());
			endReq.setUf(pcpa.getUfPcpaS());
			endReq.setTipoEndereco(new TipoEnderecoDAO().lerPorId(4));
			endReq = new EnderecoDAO().salvar(endReq);

			teleReq = new TelefonePessoa();
			if(pcpa.getTelefone_PCPA_S()!=null && pcpa.getTelefone_PCPA_S().length()<20){
			teleReq.setNumTelefone(pcpa.getTelefone_PCPA_S());
			teleReq.setTipoTelefone(new TipoTelefone(5));
			teleReq.setPessoa(pessoaReqS);
			teleReq = new TelefonePessoaDAO().salvar(teleReq);
			}
			pessaEndReq = new PessoaEndereco();
			pessaEndReq.setPessoa(pessoaReqS);
			pessaEndReq.setEndereco(endReq);
			pessaEndReq = new PessoaEnderecoDAO().salvar(pessaEndReq);

			pessoaProcReq = new PessoaProcesso();
			pessoaProcReq.setProcesso(processo);
			pessoaProcReq.setPessoa(pessoaReqS);
			pessoaProcReq.setTipoPessoaProcesso(new TipoPessoaProcesso(2));
			pessoaProcReq = new PessoaProcessoDAO().salvar(pessoaProcReq);
		    }
		    //new ProcessoDAO().gravarTransacao();
		}

	    } catch (Exception e) {
		e.printStackTrace();
		new ProcessoDAO().desfazerTransacao();
	    }
	}
    }

    private static Date setaDataPessoa(String data_Nascimento_requer_PCPA) {
	try {
	    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(data_Nascimento_requer_PCPA);
	} catch (Exception e) {
	    // TODO: handle exception
	}
	return null;
    }

    private static void setaDatas(DadosPcpa pcpa, Processo processo) {
	try {
	    processo.setDataCadastro(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(pcpa.getData_autuacao_PCPA()));
	    processo.setDataProtocoloCa(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(pcpa.getData_autuacao_PCPA()));
	    processo.setDataHoraInclusao(new Timestamp(new Date().getTime()));
	} catch (Exception e) {
	    try {
		processo.setDataCadastro(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1900-01-01"));
		processo.setDataProtocoloCa(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1900-01-01"));
	    } catch (ParseException e1) {
	    }
	}

    }

    private static Map<String, GrupoProcessual> carregaMapaGrupoProcessual() {
	Map<String, GrupoProcessual> map = new HashMap<String, GrupoProcessual>();
	List<GrupoProcessual> listaGrupo = new GrupoProcesssualDAO().lerTodos();
	for (GrupoProcessual grupo : listaGrupo) {
	    map.put(grupo.getDescGrupoProcessual(), grupo);
	}
	return map;
    }

    private static Map<String, SubGrupoProcessual> carregaMapaSubGrupoProcessual() {
	Map<String, SubGrupoProcessual> map = new HashMap<String, SubGrupoProcessual>();
	List<SubGrupoProcessual> listaGrupo = new SubGrupoProcesssualDAO().lerTodos();
	for (SubGrupoProcessual grupo : listaGrupo) {
	    map.put(grupo.getDescSubGrupoProcessual(), grupo);
	}
	return map;
    }

    public static void main2(String[] args) {
	CargaBD carga = new CargaBD();
	try {
	    List<String> linhas = FileUtils.readLines(new File("D:\\dump.sql"), Charsets.ISO_8859_1);
	    int i = 1;
	    Connection conn = null;
	    for (String linha : linhas) {
		if (linha != null)
		    if (conn == null || conn.isClosed()) {
			conn = carga.getConnectio();
			conn.setAutoCommit(false);
		    }
		Statement statement = conn.createStatement();
		statement.execute(linha);
		System.out.println("Inserindo : " + i);
		if (i > 3000) {
		    conn.commit();
		    conn.close();
		    System.out.println("Comitando <><><> : " + i);
		    i = 0;
		}
		i++;
	    }
	    if (conn == null || conn.isClosed()) {
		conn = carga.getConnectio();
		conn.setAutoCommit(false);
	    }
	    conn.commit();
	    conn.close();

	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    private Connection getConnectio() {
	Connection con = null;
	try {
	    Class.forName("com.mysql.jdbc.Driver");
	    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sinca", "root", "root");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return con;
    }

}
