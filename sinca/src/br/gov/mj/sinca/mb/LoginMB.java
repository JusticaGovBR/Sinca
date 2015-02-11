package br.gov.mj.sinca.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
    

    public String acaoAutenticar() {
	Usuario usuario = new UsuarioDAO().buscarUsuario(nome);
	
	logger.debug("Autenticando usuário :: "+nome);
	
	if (usuario == null) {
	    JSFUtil.retornarMensagem(null, "Usuário não existe.");
	    return "login?faces-redirect=true";
	} else if (usuario.senhaCorreta(this.getSenha())) {
	    this.setUsuario(usuario);
	    this.autenticado = true;
	    return "index?faces-redirect=true";
	} else {
	    JSFUtil.retornarMensagem(null, "Senha inválida.");
	    return "login?faces-redirect=true";
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

    public static void main(String[] args) {
	LoginMB loginMB = new LoginMB();
	loginMB.Init();
    }
    
}
