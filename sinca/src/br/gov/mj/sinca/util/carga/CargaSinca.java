package br.gov.mj.sinca.util.carga;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;

import br.gov.mj.sinca.dao.GrupoProcesssualDAO;
import br.gov.mj.sinca.dao.SubGrupoProcesssualDAO;
import br.gov.mj.sinca.dao.TipoPessoaProcessoDAO;
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

public class CargaSinca {

	private Connection connection;

	public static void main(String[] args) {
		carregaGrupo();
		carregaSubGrupo();
		
	}

	private static void carregaSubGrupo() {
		try {
			SubGrupoProcesssualDAO dao = new SubGrupoProcesssualDAO();

			List<String> listaLinhas = FileUtils.readLines(new File(
					"D:\\SINCA_01dez2014-BD-00-PCPA.csv"),Charsets.ISO_8859_1);
			Map<String, GrupoProcessual> map = new HashMap<String, GrupoProcessual>();
			for (String linha : listaLinhas) {
				if (linha.split(";") != null && linha.split(";").length > 20) {
					String nomeGrupo = linha.split(";")[20];
					if (!map.containsKey(nomeGrupo)) {
						map.put(nomeGrupo, new GrupoProcessual(nomeGrupo));
					}
				}
			}
			for (String chave : map.keySet()) {
				dao.salvar(new SubGrupoProcessual(chave));
				// System.out.println(chave);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void carregaGrupo() {
		try {
			GrupoProcesssualDAO dao = new GrupoProcesssualDAO();

			List<String> listaLinhas = FileUtils.readLines(new File(
					"D:\\Documentos\\DOCUMENTOS_PENUD\\TABELAS_CARGA\\Grupo-requer-PCPA.txt"),Charsets.UTF_8);
			Map<String, GrupoProcessual> map = new HashMap<String, GrupoProcessual>();
			for (String linha : listaLinhas) {
				if (linha != null && !linha.trim().equals("")
						&& !map.containsKey(linha)) {
					map.put(linha, new GrupoProcessual(linha));
				}
			}
			for (String chave : map.keySet()) {
				dao.salvar(new GrupoProcessual(chave));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main1(String[] args) {

		try {
			EntityManagerFactory emfactory = Persistence
					
					.createEntityManagerFactory("sincaEM");
			EntityManager entityManager = emfactory.createEntityManager();

			List<String> listaLinhas = FileUtils.readLines(new File(
					"d:/SINCA_01dez2014-BD-00-PCPA.csv"),Charsets.ISO_8859_1);

		
			for (String linha : listaLinhas) {

				try {

					Processo processo = new Processo();
					processo.setNumProtocoloCa(Integer.valueOf(linha.split(";")[0]));
					processo.setDataProtocoloCa(formataData(linha.split(";")[1]));
					processo.setDataHoraInclusao(new Timestamp(new Date().getTime()));

					if (processo.getNumProtocoloCa() > 0) {
						System.out.println(linha.split(";")[0]);

						if (!entityManager.getTransaction().isActive())
							entityManager.getTransaction().begin();

						Pessoa pesReq = new Pessoa();

						pesReq.setNomePessoa(linha.split(";")[3]);

						if (linha.split(";")[4].length() <= 20) {
							pesReq.setNumCpfCnpj(linha.split(";")[4]);
						}

						if (linha.split(";")[5].length() == 1) {
							pesReq.setGenero(linha.split(";")[5]);
						}

						pesReq.setDataNascimento(formataData(linha.split(";")[6]));
						
						pesReq.setNomeMae(linha.split(";")[7]);
						

						entityManager.persist(pesReq);

						Endereco endereco = new Endereco();
						endereco.setLogradouro((linha.split(";")[23]));
						endereco.setBairro(linha.split(";")[24]);
						endereco.setCidade(linha.split(";")[25]);
						endereco.setUf(linha.split(";")[26]);

						endereco.setCep(truncaCampo(linha.split(";"), 27, 20));

						entityManager.persist(endereco);

						PessoaEndereco pesEndReq = new PessoaEndereco();
						pesEndReq.setEndereco(endereco);
						pesEndReq.setPessoa(pesReq);

						entityManager.persist(pesEndReq);

						entityManager.flush();

						TelefonePessoa telPessoa = new TelefonePessoa();
						telPessoa.setPessoa(pesReq);
						telPessoa.setTipoTelefone(new TipoTelefone());
						telPessoa.getTipoTelefone().setCodTipo(5);
						
						telPessoa.setNumTelefone(linha.split(";")[28]);

						if (telPessoa.getNumTelefone() != null
								&& telPessoa.getIdTelefonePessoa() != 0)
							entityManager.persist(telPessoa);

						// processo.setPessoaRequerente(pesReq);

						entityManager.persist(processo);
						entityManager.flush();
						entityManager.clear();
						
						PessoaProcesso pessoaProcReq  = new PessoaProcesso();
						pessoaProcReq.setPessoa(pesReq);
						pessoaProcReq.setProcesso(processo);
						TipoPessoaProcessoDAO tipoPessoaProcessoDAO = new TipoPessoaProcessoDAO();
						TipoPessoaProcesso tipoPessoaProcessoRq = tipoPessoaProcessoDAO.lerPorId(1);
						pessoaProcReq.setTipoPessoaProcesso(tipoPessoaProcessoRq);
						entityManager.persist(pessoaProcReq);


						if (linha.split(";").length < 29) {
							fecharTransacao(entityManager);

							continue;
						}
						Pessoa pesProcurador = new Pessoa();
						pesProcurador.setNomePessoa(linha.split(";")[29]);

						entityManager.persist(pesProcurador);

						if (linha.split(";").length < 31) {
							fecharTransacao(entityManager);
							continue;
						}

						GrupoProcessual grupoProcessual = new GrupoProcessual();
						grupoProcessual
								.setDescGrupoProcessual(linha.split(";")[31]);

						entityManager.persist(grupoProcessual);

						SubGrupoProcessual subGrupo = new SubGrupoProcessual();
						subGrupo.setDescSubGrupoProcessual(linha.split(";")[32]);

						entityManager.persist(subGrupo);

						Pessoa pessAnistiado = new Pessoa();
						pessAnistiado.setNomePessoa(linha.split(";")[33]);
						pessAnistiado.setDataNascimento(formataData(linha
								.split(";")[34]));

						pessAnistiado.setGenero(truncaCampo(linha.split(";"),
								35, 1));

						pessAnistiado.setNumCpfCnpj(truncaCampo(
								linha.split(";"), 36, 20));
						pessAnistiado.setNomeMae(linha.split(";")[37]);

						entityManager.persist(pessAnistiado);
						// processo.setPessoaAnistiado(pessAnistiado);
						entityManager.merge(processo);
						//entityManager.flush();
						
						PessoaProcesso pessoaProcAnist  = new PessoaProcesso();
						pessoaProcAnist.setPessoa(pessAnistiado);
						pessoaProcAnist.setProcesso(processo);
						TipoPessoaProcesso tipoPessoaProcessoAnist = tipoPessoaProcessoDAO.lerPorId(2);
						pessoaProcAnist.setTipoPessoaProcesso(tipoPessoaProcessoAnist);
						entityManager.persist(pessoaProcAnist);
						entityManager.flush();

						Endereco endAnist = new Endereco();
						endAnist.setLogradouro(linha.split(";")[38]);
						endAnist.setBairro(linha.split(";")[39]);
						endAnist.setCidade(linha.split(";")[40]);

						endAnist.setUf(truncaCampo(linha.split(";"), 41, 1));

						endAnist.setCep(truncaCampo(linha.split(";"), 42, 30));

						entityManager.persist(endAnist);

						PessoaEndereco pesEndAnist = new PessoaEndereco();
						pesEndAnist.setEndereco(endAnist);
						pesEndAnist.setPessoa(pessAnistiado);

						entityManager.persist(pesEndAnist);

						TelefonePessoa telPessAnit = new TelefonePessoa();
						telPessAnit.setPessoa(pessAnistiado);
						telPessAnit.setTipoTelefone(new TipoTelefone());
						telPessAnit.getTipoTelefone().setCodTipo(5);

						telPessAnit.setNumTelefone(truncaCampo(
								linha.split(";"), 43, 20));

						entityManager.persist(telPessAnit);
					}

				} catch (Exception e) {
					e.printStackTrace();
					fecharTransacao(entityManager);
				}
				fecharTransacao(entityManager);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void fecharTransacao(EntityManager entityManager) {
		if (entityManager.getTransaction().isActive()) {
			entityManager.clear();
			entityManager.flush();
			;
			entityManager.getTransaction().commit();
			System.out.println("Fechando a trasacao");
		}
		entityManager.getTransaction().begin();
	}

	private static Date formataData(String data) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(data);
		} catch (ParseException e) {
			return null;
		}
	}

	private Connection obterConexao() {
		try {
			if (connection == null || connection.isClosed()) {
				Class.forName("org.firebirdsql.jdbc.FBDriver");
				connection = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/sinca", "root", "root");
			}
			return connection;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static String truncaCampo(String a[], int possicao, int tamanho) {
		try {
			if (a.length < possicao) {
				return "-";
			} else {
				return a[possicao].substring(0, tamanho);
			}

		} catch (Exception e) {
			return null;
		}
	}

	public Connection getConnection() {
		if (connection == null) {
			connection = obterConexao();

		}
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
