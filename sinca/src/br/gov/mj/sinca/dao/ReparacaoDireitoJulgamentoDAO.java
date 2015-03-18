package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.ReparacaoDireitoJulgamento;

public class ReparacaoDireitoJulgamentoDAO extends SincaAbastractDAO<ReparacaoDireitoJulgamento> {
    
	public ReparacaoDireitoJulgamentoDAO() {
		super();
	}

	public ReparacaoDireitoJulgamentoDAO(EntityManager manager) {
		super(manager);
	}
}
