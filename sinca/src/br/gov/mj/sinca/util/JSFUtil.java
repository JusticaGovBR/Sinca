package br.gov.mj.sinca.util;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

public class JSFUtil {

    public static void retornarMensagem(String idComponentePagina, String mensagem) {
	FacesContext contexto = FacesContext.getCurrentInstance();
	contexto.addMessage(idComponentePagina, new FacesMessage(mensagem));
    }

    public static void retornarMensagem(String idComponentePagina, Severity severity, String mensagem) {
	FacesContext contexto = FacesContext.getCurrentInstance();
	contexto.addMessage(idComponentePagina, new FacesMessage(severity, "", mensagem));
    }

    public static void retornarMensagemModal(String title, String mensagem) {
	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, title, mensagem);
	RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public static RequestContext getRequestContext() {
	return RequestContext.getCurrentInstance();
    }

    /**
     * M�todo que l� um par�metro de um link ou bot�o passado atrav�s da TAG
     * <code>&lt;f:param&gt;</code>.
     * 
     * @param nomeDoParametro
     *            valor usado no atributo name da TAG "param"
     * @return o valor passado no envio da requisi��o como uma String
     */
    public static String getParametro(String nomeDoParametro) {
	FacesContext context = FacesContext.getCurrentInstance();
	Map<String, String> map = context.getExternalContext().getRequestParameterMap();
	String valor = map.get(nomeDoParametro);

	return valor;
    }

    /**
     * M�todo que l� um par�metro de um link ou bot�o passado atrav�s da TAG
     * <code>&lt;f:param&gt;</code>, convertendo o valor para Long.
     * 
     * @param nomeDoParametro
     *            valor usado no atributo name da TAG "param"
     * @return o valor passado no envio da requisi��o como um Long
     */
    public static Long getParametroLong(String nomeDoParametro) {
	String valor = getParametro(nomeDoParametro);
	Long valorLong;
	try {
	    valorLong = new Long(valor);
	} catch (Exception e) {
	    valorLong = null;
	}

	return valorLong;
    }

    /**
     * M�todo que l� um par�metro de um link ou bot�o passado atrav�s da TAG
     * <code>&lt;f:param&gt;</code>, convertendo o valor para Integer.
     * 
     * @param nomeDoParametro
     *            valor usado no atributo name da TAG "param"
     * @return o valor passado no envio da requisi��o como um Integer
     */
    public static Integer getParametroInteger(String nomeDoParametro) {
	String valor = getParametro(nomeDoParametro);
	Integer valorInt;
	try {
	    valorInt = new Integer(valor);
	} catch (Exception e) {
	    valorInt = null;
	}

	return valorInt;
    }


    /**
     * M�todo que pega o objeto HttpSession associado com a requisi��o atual.
     */
    public static HttpSession getHttpSession() {
	FacesContext fc = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);

	return session;
    }

    /**
     * M�todo que pega o objeto HttpServletRequest associado com a requisi��o
     * atual.
     */
    public static HttpServletRequest getServletRequest() {
	return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    /**
     * M�todo que pega o objeto HttpServletResponse associado com a requisi��o
     * atual.
     */
    public static HttpServletResponse getServletResponse() {
	return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }

    /**
     * M�todo que pega o objeto ExternalContext do contexto do Faces.
     */
    public static ExternalContext getExternalContext() {
	return FacesContext.getCurrentInstance().getExternalContext();
    }

    /**
     * M�todo que pega o objeto Map do Application do contexto do Faces.
     */
    public static Map<String, Object> getApplicationMap() {
	return FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();
    }

    /**
     * M�todo que pega o objeto Map do Session do contexto do Faces.
     */
    public static Map<String, Object> getSessionMap() {
	return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    }

    /**
     * M�todo que pega o objeto Map do Request do contexto do Faces.
     */
    public static Map<String, Object> getRequestMap() {
	return FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
    }

    /**
     * M�todo que pega o objeto Map com os Par�metros da requisi��o.
     */
    public static Map<String, String> getRequestParameterMap() {
	return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    }

    /**
     * M�todo que pega o objeto ServletContext do contexto do Faces.
     */
    public static ServletContext getServletContext() {
	return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    }

    public static void limparObjetosSessao(String nomeChave) {
	for (String chave : JSFUtil.getSessionMap().keySet()) {
	    if (chave.contains(nomeChave)) {
		System.out.println("JSFUtil.limparObjetosSessao() <> " + chave);
		getSessionMap().put(chave, null);
	    }
	}
    }

  
}