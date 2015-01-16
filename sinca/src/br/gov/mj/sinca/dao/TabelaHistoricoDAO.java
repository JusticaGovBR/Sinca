package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.TabelaHistorico;

public class TabelaHistoricoDAO extends SincaAbastractDAO<TabelaHistorico> {
    

	public TabelaHistoricoDAO() {
		super();
	}

	public TabelaHistoricoDAO(EntityManager manager) {
		super(manager);
	}
	

}
