	package br.gov.mj.sinca.ws.sei;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

import javax.xml.rpc.ServiceException;

import br.gov.mj.sinca.dao.LocalizacaoDAO;
import br.gov.mj.sinca.entidades.Localizacao;

public class TesteSeiWS {


    public static void main(String[] args) {
	SeiServiceLocator locator = new SeiServiceLocator();
	try {
	    
	    RetornoConsultaProcedimento retorno = locator.getSeiPortService().consultarProcedimento("SEI", "lu",
		    "110000834", "08000.000010/2014-61", null, null, null, null, null, null, null, null, null);
	} catch (RemoteException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (ServiceException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }	
    
    public static void main2(String[] args) {
	SeiServiceLocator locator = new SeiServiceLocator();
	try {
	    
	    RetornoConsultaProcedimento retorno = locator.getSeiPortService().consultarProcedimento("SEI", "lu",
		    "110000834", "08000.000010/2014-61", null, null, null, null, null, null, null, null, null);

	    
//	    RetornoConsultaProcedimento retorno = locator.getSeiPortService().consultarProcedimento("SEI", "lu",
//		    "110000834", "08000.000010/2014-61", null, null, null, null, null, null, null, null, null);
//	    
	    
	    //locator.getSeiPortService().incluirDocumento(siglaSistema, identificacaoServico, idUnidade, documento)
	    
	    System.out.println(retorno.getLinkAcesso());
	    // 08000.000010/2014-61

	    Usuario[] usuario = locator.getSeiPortService().listarUsuarios("SEI", "lu", "110000834", "");
	    for (Usuario us : usuario) {
		System.out.println("Usuario :" + us.getNome() + " sigla " + us.getSigla() + " id " + us.getIdUsuario());
	    }

	    // locator.getSeiPortService().consultarProcedimento(siglaSistema,
	    // identificacaoServico, idUnidade,
	    // protocoloProcedimento, sinRetornarAssuntos,
	    // sinRetornarInteressados, sinRetornarObservacoes,
	    // sinRetornarAndamentoGeracao, sinRetornarAndamentoConclusao,
	    // sinRetornarUltimoAndamento,
	    // sinRetornarUnidadesProcedimentoAberto,
	    // sinRetornarProcedimentosRelacionados,
	    // sinRetornarProcedimentosAnexados);

	    /*
	     * RetornoConsultaProcedimento retorno =
	     * locator.getSeiPortService().consultarProcedimento("SEI", "lu",
	     * "110000834", "08000.000002/2014-14", null, null, null, null,
	     * null, null, null, null, null);
	     * 
	     * System.out.println("retorno "+retorno.getLinkAcesso()+" - "+retorno
	     * .getTipoProcedimento().getNome());
	     * 
	     * RetornoConsultaDocumento retornoDoc =
	     * locator.getSeiPortService().consultarDocumento("SEI", "lu",
	     * "110000834", "0000001", null, null, null);
	     * 
	     * locator.getSeiPortService().listarTiposProcedimento("SEI", "lu",
	     * "110000834", null);
	     */

	    // "08000.000002/2014-14"
	    // RetornoConsultaProcedimento retorno =
	    // locator.getSeiPortService().consultarProcedimento("SEI", "lu",
	    // "110000834", "08000.000002/2014-14", null, null, null, null,
	    // null, null, null, null, null);
	    // retorno.getUltimoAndamento();
	    //
	    // locator.getSeiPortService().listarTiposProcedimento("SEI", "lu",
	    // "110000834", null);
	    // locator.getSeiPortService().listarSeries("SEI", "lu",
	    // "110000834", null);

	    List<Unidade> listaUnidades = Arrays.asList(locator.getSeiPortService().listarUnidades("SEI", "lu", null,
		    null));
	    LocalizacaoDAO localizacaoDAO = new LocalizacaoDAO();
	    for (Unidade unidade : listaUnidades) {
		System.out.println(unidade.getIdUnidade() + " Desc " + unidade.getDescricao()+" Singla "+unidade.getSigla());
		 Localizacao localizacao = new Localizacao();
		 localizacao.setDescLocalizacao(unidade.getDescricao());
		 localizacao.setSigla(unidade.getSigla());
		 localizacao.setIdLocalizacao(Integer.valueOf(unidade.getIdUnidade()));
		 
		 localizacaoDAO.abrirTransacao();
		 localizacaoDAO.getEntityManager().merge(localizacao);
		 localizacaoDAO.gravarTransacao();
	    }
	    
	    System.out.println("############################## USUÁRIOS DA COMISSÃO DE ANISTIA...");

	    for (Unidade unidade : listaUnidades) {
		 if(unidade.getDescricao().contains("anistia")){
		    usuario = locator.getSeiPortService().listarUsuarios("SEI", "lu", unidade.getIdUnidade(), "");
        	    System.out.println("");
        	    for (Usuario us : usuario) {
        		System.out.println("Usuario :" + us.getNome() + " sigla " + us.getSigla() + " id " + us.getIdUsuario()+" Unidade "+unidade.getDescricao());
        	    }
    	        }
	    }
	    // System.out.println(retornoDoc.getDocumentoFormatado());

	} catch (RemoteException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (ServiceException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }
}
