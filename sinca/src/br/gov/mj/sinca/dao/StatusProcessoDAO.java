package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.StatusProcesso;

public class StatusProcessoDAO extends SincaAbastractDAO<StatusProcesso> {
	public StatusProcessoDAO() {
		super();
	}

	public StatusProcessoDAO(EntityManager manager) {
		super(manager);
	}
}
