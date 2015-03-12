package br.gov.mj.sinca.mb;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import br.gov.mj.corporativo.servicos.exceptions.ErroInternoException;
import br.gov.mj.corporativo.servicos.exceptions.ParametroInvalidoException;
import br.gov.mj.seguranca.ejb.SegurancaDelegate;
import br.gov.mj.seguranca.exceptions.IdentificadorSenhaIncorretosException;
import br.gov.mj.seguranca.exceptions.LogonBloqueadoException;
import br.gov.mj.seguranca.exceptions.SessaoInvalidaException;
import br.gov.mj.seguranca.exceptions.UsuarioNaoEncontradoException;
import br.gov.mj.seguranca.pojo.Sessao;
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
	logger = Logger.getLogger(LoginMB.class);
	logger.debug("Init :: " + LoginMB.class.getName());
    }

    @SuppressWarnings({ "deprecation", "null" })
    public void acaoAutenticar() {
	Usuario usuario = null;
	br.gov.mj.seguranca.pojo.Usuario usuarioMJ = null;
	try {
	    if (nome.equalsIgnoreCase("sebastiao.costa") || nome.equalsIgnoreCase("usr_sinca")) {
		usuario = new UsuarioDAO().buscarUsuario(nome);
		if (usuario != null && usuario.getIdUsuario().intValue() > 0 && usuario.senhaCorreta(senha)) {
		    JSFUtil.getHttpSession().setAttribute("USUARIO_AUTENTICADO", this.autenticado);
		    logger.debug("Redirecionando::" + nome);
		    usuario.setDataUltimoAcesso(new Date());

		    new UsuarioDAO().salvar(usuario);
		    this.setUsuarioSinca(usuario);
		    this.autenticado = true;

		    NavigationHandler nh = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		    nh.handleNavigation(FacesContext.getCurrentInstance(), null, "principal");
		} else {
		    JSFUtil.retornarMensagem(null, "Usuário o Senha Inválida! ");
		}
	    } else {
		Sessao sessao = SegurancaDelegate.getInstancia().autenticarUsuario(nome, senha.toCharArray(),
			JSFUtil.getServletRequest().getRemoteHost(), JSFUtil.getServletRequest().getRemoteAddr());

		usuarioMJ = SegurancaDelegate.getInstancia().getUsuarioPorIdentificador(nome, sessao);

		if (usuarioMJ != null && usuarioMJ.getNome() != null && !usuarioMJ.getNome().equals("")) {
		    System.out.println("usuarioMJ <> " + usuarioMJ.getIdentificador() + " nome :: "
			    + usuarioMJ.getNome() + " <> " + usuarioMJ.getNomePessoa());
		    logger.debug("Autenticando usuário LDAP:: " + nome);
		    usuario = new UsuarioDAO().buscarUsuario(nome);
		    if (usuario == null || usuario.getIdUsuario() == null) {
			usuario = new Usuario();
			usuario.setDataCadastro(new Date());
		    }
		    usuario.setSglUsuario(usuarioMJ.getIdentificador());
		    usuario.setDescUsuario(usuarioMJ.getNome());
		    usuario.setDataUltimoAcesso(new Date());
		    this.setUsuarioSinca(usuario);
		    this.autenticado = true;

		    JSFUtil.getHttpSession().setAttribute("USUARIO_AUTENTICADO", this.autenticado);
		    logger.debug("Redirecionando::" + nome);
		    new UsuarioDAO().salvar(usuario);
		    NavigationHandler nh = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		    nh.handleNavigation(FacesContext.getCurrentInstance(), null, "principal");

		}
	    }
	} catch (IdentificadorSenhaIncorretosException ex) {
	    logger.error("Error :: " + nome + " ERROR :" + ex.getMessage());
	    ex.printStackTrace();
	    JSFUtil.retornarMensagem(null, "Erro ao Acessar o LDAP: " + ex.getMessage());
	} catch (LogonBloqueadoException ex) {
	    logger.error("Error :: " + nome + " ERROR :" + ex.getMessage());
	    ex.printStackTrace();
	    JSFUtil.retornarMensagem(null, "Erro ao Acessar o LDAP: " + ex.getMessage());
	} catch (ParametroInvalidoException ex) {
	    logger.error("Error :: " + nome + " ERROR :" + ex.getMessage());
	    ex.printStackTrace();
	    JSFUtil.retornarMensagem(null, "Erro ao Acessar o LDAP: " + ex.getMessage());
	} catch (ErroInternoException ex) {
	    logger.error("Error :: " + nome + " ERROR :" + ex.getMessage());
	    ex.printStackTrace();
	    JSFUtil.retornarMensagem(null, "Erro ao Acessar o LDAP: " + ex.getMessage());
	} catch (SessaoInvalidaException ex) {
	    logger.error("Error :: " + nome + " ERROR :" + ex.getMessage());
	    ex.printStackTrace();
	    JSFUtil.retornarMensagem(null, "Erro ao Acessar o LDAP: " + ex.getMessage());
	} catch (UsuarioNaoEncontradoException ex) {
	    logger.error("Error :: " + nome + " ERROR :" + ex.getMessage());
	    ex.printStackTrace();
	    JSFUtil.retornarMensagem(null, "Erro ao Acessar o LDAP: " + ex.getMessage());
	} catch (Exception ex) {
	    logger.error("Error :: " + nome + " ERROR :" + ex.getMessage());
	    ex.printStackTrace();
	    JSFUtil.retornarMensagem(null, "Erro ao Acessar o LDAP: " + ex.getMessage());
	} finally {
	    this.senha = "";
	}
	logger.debug("Autenticando usuário :: " + nome);
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

    public void setUsuarioSinca(Usuario usuario) {
	this.usuario = usuario;
    }

    public boolean isAutenticado() {
	return autenticado;
    }

    public void setAutenticado(boolean autenticado) {
	this.autenticado = autenticado;
    }

}
