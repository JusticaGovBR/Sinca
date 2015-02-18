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
	Query query = getEntityManager().createQuery("SELECT u FROM Usuario u where u.sglUsuario=:sigla");
	query.setParameter("sigla", sigla);
	try {
	    return (Usuario) query.getSingleResult();    
	} catch (NoResultException e) {
	    return null;
	}
	
    }

}
