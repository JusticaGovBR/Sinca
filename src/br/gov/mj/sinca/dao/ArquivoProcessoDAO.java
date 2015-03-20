package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.gov.mj.sinca.entidades.ArquivoProcesso;

public class ArquivoProcessoDAO extends SincaAbastractDAO<ArquivoProcesso> {

    public ArquivoProcessoDAO() {
	super();
    }

    public ArquivoProcessoDAO(EntityManager manager) {
	super(manager);
    }

    public ArquivoProcesso buscarArquivoProcesso(long idProcesso) {
	try {
	    Query query = getEntityManager().createQuery(
		    "SELECT p FROM ArquivoProcesso p where p.processo.idProcesso = " + idProcesso);
	    ArquivoProcesso arquivoProcesso = (ArquivoProcesso) query.getSingleResult();
	    return arquivoProcesso;
	} catch (NoResultException e) {
	    return new ArquivoProcesso();
	}
	
    }

}
