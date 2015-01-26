package br.gov.mj.sinca.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginMB implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7487051267288610892L;

    private String nome;
    private String senha;

    public String acaoAutenticar() {
	//Usuario usuarioDoBanco = dao.lerPorLogin(this.getLogin());

//	if (usuarioDoBanco == null) {
//	    JSFUtil.retornarMensagem(null, "Usuário não existe.");
//	    return "login";
//	} else if (usuarioDoBanco.senhaCorreta(this.getSenha())) {
//	    // guardar o obteto usuário
//	    this.setUsuario(usuarioDoBanco);
//	    this.autenticado = true;
//
//	    return "home";
//	} else {
//	    JSFUtil.retornarMensagem(null, "Senha inválida.");
//	    return "login";
//	}
	return "index";
    }

    /**
	 * 
	 */
//    public String acaoLogout() {
//	this.usuario = null;
//	this.autenticado = false;
//	this.login = null;
//	this.senha = null;
//
//	// encerrar a sessão atual
//	JSFUtil.getHttpSession().invalidate();
//
//	return "login";
//    }

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

}
