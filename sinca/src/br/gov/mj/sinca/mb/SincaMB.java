package br.gov.mj.sinca.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.gov.mj.sinca.entidades.PessoaFisica;
import br.gov.mj.sinca.util.JSFUtil;
import br.gov.mj.sinca.ws.sei.RetornoConsultaProcedimento;
import br.gov.mj.sinca.ws.sei.SeiServiceLocator;

@ManagedBean(name = "sincaMB")
@RequestScoped
public class SincaMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2233176971399563838L;

	private Date dataAtual;

	private String dataAutuacao;

	private String tipo;

	private boolean mesmoRequerente;
	
	private String nomeAnistiado;

	private String numCpfAnistiado;

	private String numProcessoSei;

	private List<PessoaFisica> pessoas;
	
	public String acaoConsultarProcesso() throws IOException{
	   return "pages/processos/consultarProcesso.jsf";
	}
	
	public String acaoManterProcesso() throws IOException{
	    //return "pages/processos/manterPessoaProcesso.jsf";
	    //JSFUtil.getExternalContext().getContextName();
	    return "pages/processos/manterPessoaProcesso.jsf";
	}
	
	
	public String consultarProcesso() {
		try {
			// 08000.000002/2014-14
			JSFUtil.retornarMensagem(null, "Consultado WebServices do SEI, processo:"+numProcessoSei);
			SeiServiceLocator locator = new SeiServiceLocator();
			RetornoConsultaProcedimento retorno = locator.getSeiPortService()
					.consultarProcedimento("SEI", "lu", "110000834",
							numProcessoSei, null, null, null, null, null, null,
							null, null, null);
			if (retorno != null) {
				System.out.println("retorno " + retorno.getLinkAcesso() + " "
						+ retorno.getDataAutuacao());
				setDataAutuacao(retorno.getDataAutuacao());
				dataAutuacao = retorno.getDataAutuacao();
			}

		} catch (Exception e) {
			
		}
		return null;
	}

	public List<PessoaFisica> carregaPessoas() {
		if (pessoas == null) {
			//pessoas = pessoaDAO.lerTodos();
		}
		return pessoas;
	 }
	
        public List<PessoaFisica> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<PessoaFisica> pessoas) {
		this.pessoas = pessoas;
	}

	public String getNomeAnistiado() {
		return nomeAnistiado;
	}

	public void setNomeAnistiado(String nomeAnistiado) {
		this.nomeAnistiado = nomeAnistiado;
	}

	public String getNumCpfAnistiado() {
		return numCpfAnistiado;
	}

	public void setNumCpfAnistiado(String numCpfAnistiado) {
		this.numCpfAnistiado = numCpfAnistiado;
	}

	public boolean isMesmoRequerente() {
		return mesmoRequerente;
	}

	public void setMesmoRequerente(boolean mesmoRequerente) {
		this.mesmoRequerente = mesmoRequerente;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}

	public String getNumProcessoSei() {
		return numProcessoSei;
	}

	public void setNumProcessoSei(String numProcessoSei) {
		this.numProcessoSei = numProcessoSei;
	}

	public String getDataAutuacao() {
		return dataAutuacao;
	}

	public void setDataAutuacao(String dataAutuacao) {
		this.dataAutuacao = dataAutuacao;
	}

	public static void main(String[] args) {
		SincaMB mb = new SincaMB();
		List<PessoaFisica> lista = mb.carregaPessoas();
		for (PessoaFisica pessoa : lista) {
			System.out.println(pessoa.getNomePessoa());
		}
	}

}
