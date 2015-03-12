package br.gov.mj.sinca.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.PessoaFisica;
import br.gov.mj.sinca.entidades.Usuario;

public class UsuarioDAO extends SincaAbastractDAO<Usuario> {

    public UsuarioDAO() {
	super();
    }

    public UsuarioDAO(EntityManager manager) {
	super(manager);
    }
    
    @SuppressWarnings("unchecked")
    public List<Usuario> listaUsuarioPorNomeLk(String nome) {
	Query query = getEntityManager().createQuery("SELECT u FROM Usuario u where u.descUsuario like '" + nome + "%'");
	return query.getResultList();
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
	dao.listaUsuarioPorNomeLk("sebastiao");
    }

}
