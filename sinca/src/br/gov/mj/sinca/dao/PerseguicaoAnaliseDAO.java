package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.PerseguicaoAnalise;

public class PerseguicaoAnaliseDAO extends SincaAbastractDAO<PerseguicaoAnalise> {
    
	public PerseguicaoAnaliseDAO() {
		super();
	}

	public PerseguicaoAnaliseDAO(EntityManager manager) {
		super(manager);
	}
}
