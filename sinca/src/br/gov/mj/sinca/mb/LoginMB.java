package br.gov.mj.sinca.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import br.gov.mj.sinca.dao.UsuarioDAO;
import br.gov.mj.sinca.entidades.Usuario;
import br.gov.mj.sinca.util.JSFUtil;

@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginMB implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7487051267288610892L;

    private String nome;
    private String senha;
    private Usuario usuario;
    private boolean autenticado = false;
    private Logger logger = null;
    
    
    @PostConstruct
    public void Init() {
	logger =  Logger.getLogger(LoginMB.class);
	logger.debug("Init :: "+LoginMB.class.getName());
    }
    

    public void acaoAutenticar() {
	Usuario usuario = null;
	try{	
	usuario = new UsuarioDAO().buscarUsuario(nome);
	}catch(Exception e){
	    logger.error("Error :: "+nome+" ERROR :"+e.getMessage());
	    e.printStackTrace();
	}
	logger.debug("Autenticando usuário :: "+nome);
	
	if (usuario == null) {
	    logger.debug("Usuário não existe. :: "+nome);
	    JSFUtil.retornarMensagem(null, "Usuário não existe.");
	    JSFUtil.getHttpSession().setAttribute("USUARIO_AUTENTICADO", false);
	    //return null;
	} else if (usuario.senhaCorreta(this.getSenha())) {
	    this.setUsuario(usuario);
	    this.autenticado = true;
	    JSFUtil.getHttpSession().setAttribute("USUARIO_AUTENTICADO", this.autenticado);
	    logger.debug("Redirecionando::"+nome);
	    NavigationHandler nh = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();  
            nh.handleNavigation(FacesContext.getCurrentInstance(), null, "principal");  
	    //return "index";
	} else {
	    JSFUtil.getHttpSession().setAttribute("USUARIO_AUTENTICADO", false);
	    logger.debug("Usuário senha incorreta. :: "+nome);
	    JSFUtil.retornarMensagem(null, "Senha inválida.");
	    //return null;
	}
    }

    public String acaoAbrirLogin() {
	return "login?faces-redirect=true";
    }

    public String acaoLogout() {
	this.usuario = null;
	this.nome = null;
	this.senha = null;
	JSFUtil.getHttpSession().invalidate();
	return "login";
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public String getSenha() {
	return senha;
    }

    public void setSenha(String senha) {
	this.senha = senha;
    }

    public Usuario getUsuario() {
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

    public boolean isAutenticado() {
	return autenticado;
    }

    public void setAutenticado(boolean autenticado) {
	this.autenticado = autenticado;
    }
     
}
