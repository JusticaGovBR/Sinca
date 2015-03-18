package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.TipoJulgamentoAnalise;

public class TipoJulgamentoAnaliseDAO extends SincaAbastractDAO<TipoJulgamentoAnalise> {
	public TipoJulgamentoAnaliseDAO() {
		super();
	}

	public TipoJulgamentoAnaliseDAO(EntityManager manager) {
		super(manager);
	}
	
	public static void main(String[] args) {
	    new TipoJulgamentoAnaliseDAO().lerPorId(1l);
	}
}
