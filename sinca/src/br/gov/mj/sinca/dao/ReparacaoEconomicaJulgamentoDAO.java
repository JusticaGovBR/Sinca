package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.ReparacaoEconomicaJulgamento;

public class ReparacaoEconomicaJulgamentoDAO extends SincaAbastractDAO<ReparacaoEconomicaJulgamento> {
    
	public ReparacaoEconomicaJulgamentoDAO() {
		super();
	}

	public ReparacaoEconomicaJulgamentoDAO(EntityManager manager) {
		super(manager);
	}
}
