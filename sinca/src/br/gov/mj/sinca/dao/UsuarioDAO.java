package br.gov.mj.sinca.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.Usuario;

public class UsuarioDAO extends SincaAbastractDAO<Usuario> {

    public UsuarioDAO() {
	super();
    }

    public UsuarioDAO(EntityManager manager) {
	super(manager);
    }

    public Usuario buscarUsuario(String sigla) {
	try {
	    Query query = getEntityManager().createQuery("select u from Usuario u where u.sglUsuario=:sigla");
	    query.setParameter("sigla", sigla);
	    return (Usuario) query.getSingleResult();
	} catch (NoResultException e) {
	    return null;
	}

    }

    public static void main(String[] args) {
	UsuarioDAO dao = new UsuarioDAO();
	dao.buscarUsuario("sebastiao.costa");
    }

}
