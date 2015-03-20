package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.TipoDesicaoJulgamento;

public class TipoDesicaoJulgamentoDAO extends SincaAbastractDAO<TipoDesicaoJulgamento> {
	public TipoDesicaoJulgamentoDAO() {
		super();
	}

	public TipoDesicaoJulgamentoDAO(EntityManager manager) {
		super(manager);
	}
	
	public static void main(String[] args) {
	    new TipoDesicaoJulgamentoDAO().lerPorId(1);
	}
}
