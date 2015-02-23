package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.HitoricoRequerimento;

public class HistoricoRequerimentoDAO extends SincaAbastractDAO<HitoricoRequerimento> {
    
	public HistoricoRequerimentoDAO() {
		super();
	}

	public HistoricoRequerimentoDAO(EntityManager manager) {
		super(manager);
	}
}
