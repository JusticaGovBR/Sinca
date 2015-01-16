package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.TipoAnaliseJulgamento;

public class TipoAnaliseJulgamentoDAO extends SincaAbastractDAO<TipoAnaliseJulgamento> {
	public TipoAnaliseJulgamentoDAO() {
		super();
	}

	public TipoAnaliseJulgamentoDAO(EntityManager manager) {
		super(manager);
	}
}
