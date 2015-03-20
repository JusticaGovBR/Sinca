package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.SubStatusProcesso;

public class SubStatusProcessoDAO extends SincaAbastractDAO<SubStatusProcesso> {
	
	public SubStatusProcessoDAO() {
		super();
	}

	public SubStatusProcessoDAO(EntityManager manager) {
		super(manager);
	}
}
