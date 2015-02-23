package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.AnaliseProcesso;

public class AnaliseProcessoDAO extends SincaAbastractDAO<AnaliseProcesso> {
    
	public AnaliseProcessoDAO() {
		super();
	}

	public AnaliseProcessoDAO(EntityManager manager) {
		super(manager);
	}
}
