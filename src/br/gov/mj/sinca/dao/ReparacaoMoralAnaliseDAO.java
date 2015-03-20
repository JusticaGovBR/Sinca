package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.gov.mj.sinca.entidades.ReparacaoMoralAnalise;

public class ReparacaoMoralAnaliseDAO extends SincaAbastractDAO<ReparacaoMoralAnalise> {

    public ReparacaoMoralAnaliseDAO() {
	super();
    }

    public ReparacaoMoralAnaliseDAO(EntityManager manager) {
	super(manager);
    }

    public ReparacaoMoralAnalise buscarRecomendacaoAnalise(long idRecomendacao) {
	Query query = getEntityManager().createQuery(
		"SELECT p FROM ReparacaoMoralAnalise p where p.recomendacaoAnalise.idRecomendacao = "
			+ idRecomendacao);
	return (ReparacaoMoralAnalise) query.getSingleResult();
    }
}
