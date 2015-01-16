package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.HistoricoTabela;

public class HistoricoTabelaDAO extends SincaAbastractDAO<HistoricoTabela> {
    

	public HistoricoTabelaDAO() {
		super();
	}

	public HistoricoTabelaDAO(EntityManager manager) {
		super(manager);
	}
	

}
