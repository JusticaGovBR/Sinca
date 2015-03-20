package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.gov.mj.sinca.entidades.ReparacaoDireitoAnalise;

public class ReparacaoDireitoAnaliseDAO extends SincaAbastractDAO<ReparacaoDireitoAnalise> {

    public ReparacaoDireitoAnaliseDAO() {
	super();
    }

    public ReparacaoDireitoAnaliseDAO(EntityManager manager) {
	super(manager);
    }

    public ReparacaoDireitoAnalise buscarReparacaoDireitoAnalise(long idRecomendacao) {
	Query query = getEntityManager().createQuery(
		"SELECT p FROM ReparacaoDireitoAnalise p where p.recomendacaoAnalise.idRecomendacao = " + idRecomendacao);
	return (ReparacaoDireitoAnalise) query.getSingleResult();
    }

    public ReparacaoDireitoAnalise removerReparacaoDireitoAnalise(long idRecomendacao) {
	Query query = getEntityManager().createQuery(
		"DELETE FROM ReparacaoDireitoAnalise  where p.recomendacaoAnalise.idRecomendacao = " + idRecomendacao);
	return (ReparacaoDireitoAnalise) query.getSingleResult();
    }

}
