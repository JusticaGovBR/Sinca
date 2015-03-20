package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.gov.mj.sinca.entidades.RecomendacaoAnalise;

public class RecomendacaoAnaliseDAO extends SincaAbastractDAO<RecomendacaoAnalise> {

    public RecomendacaoAnaliseDAO() {
	super();
    }

    public RecomendacaoAnaliseDAO(EntityManager manager) {
	super(manager);
    }

    public RecomendacaoAnalise buscarRecomendacaoAnalise(long idAnalise) {
	try {
	    Query query = getEntityManager().createQuery(
		    "SELECT p FROM RecomendacaoAnalise p where p.analiseProcesso.idAnalise = " + idAnalise);
	    RecomendacaoAnalise recomendacao = (RecomendacaoAnalise) query.getSingleResult();
	    return recomendacao;
	} catch (NoResultException e) {
	    return new RecomendacaoAnalise();
	}

    }
    
    
}
