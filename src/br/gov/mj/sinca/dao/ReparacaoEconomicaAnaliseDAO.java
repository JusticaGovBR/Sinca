package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.gov.mj.sinca.entidades.ReparacaoEconomicaAnalise;

public class ReparacaoEconomicaAnaliseDAO extends SincaAbastractDAO<ReparacaoEconomicaAnalise> {

    public ReparacaoEconomicaAnaliseDAO() {
	super();
    }

    public ReparacaoEconomicaAnaliseDAO(EntityManager manager) {
	super(manager);
    }

    public ReparacaoEconomicaAnalise buscarRecomendacaoAnalise(long idRecomendacao) {
	Query query = getEntityManager().createQuery(
		"SELECT p FROM ReparacaoEconomicaAnalise p where p.recomendacaoAnalise.idRecomendacao = "
			+ idRecomendacao);
	return (ReparacaoEconomicaAnalise) query.getSingleResult();
    }

    public static void main(String[] args) {
	ReparacaoEconomicaAnaliseDAO  dao = new ReparacaoEconomicaAnaliseDAO();
	dao.buscarRecomendacaoAnalise(2l);
    }
    
}
