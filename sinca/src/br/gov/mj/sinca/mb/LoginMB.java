package br.gov.mj.sinca.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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

    private String nome = "sebastiao.costa";
    private String senha;
    private Usuario usuario;
    
    public String acaoAutenticar() {
	senha = "123456";
	Usuario usuario = new UsuarioDAO().buscarUsuario(nome);

	if (usuario == null) {
	    JSFUtil.retornarMensagem(null, "Usuário não existe.");
	    return "login?faces-redirect=true";
	} else if (usuario.senhaCorreta(this.getSenha())) {
	    this.setUsuario(usuario);
	    return "index?faces-redirect=true";
	} else {
	    JSFUtil.retornarMensagem(null, "Senha inválida.");
	    return "login?faces-redirect=true";
	}
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

}
