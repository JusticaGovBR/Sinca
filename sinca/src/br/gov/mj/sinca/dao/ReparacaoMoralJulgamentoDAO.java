package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.ReparacaoMoralJulgamento;

public class ReparacaoMoralJulgamentoDAO extends SincaAbastractDAO<ReparacaoMoralJulgamento> {
    
	public ReparacaoMoralJulgamentoDAO() {
		super();
	}

	public ReparacaoMoralJulgamentoDAO(EntityManager manager) {
		super(manager);
	}
}
