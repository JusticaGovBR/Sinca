package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.AnalistaProcesso;

public class AnalistaProcessoDAO extends SincaAbastractDAO<AnalistaProcesso> {
    
	public AnalistaProcessoDAO() {
		super();
	}

	public AnalistaProcessoDAO(EntityManager manager) {
		super(manager);
	}
}
