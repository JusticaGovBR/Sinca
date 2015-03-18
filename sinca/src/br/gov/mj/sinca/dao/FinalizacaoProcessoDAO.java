package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.gov.mj.sinca.entidades.FinalizacaoProcesso;

public class FinalizacaoProcessoDAO extends SincaAbastractDAO<FinalizacaoProcesso> {

    public FinalizacaoProcessoDAO() {
	super();
    }

    public FinalizacaoProcessoDAO(EntityManager manager) {
	super(manager);
    }

    public FinalizacaoProcesso buscarFinalizacaoProcesso(long idProcesso) {
	try {
	    Query query = getEntityManager().createQuery(
		    "SELECT p FROM FinalizacaoProcesso p where p.processo.idProcesso = " + idProcesso);
	    FinalizacaoProcesso finalizacao = (FinalizacaoProcesso) query.getSingleResult();
	    return finalizacao;
	} catch (NoResultException e) {
	    return new FinalizacaoProcesso();
	}
	
    }

}
