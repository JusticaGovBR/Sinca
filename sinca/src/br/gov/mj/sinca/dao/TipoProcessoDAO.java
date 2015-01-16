package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.TipoProcesso;

public class TipoProcessoDAO extends SincaAbastractDAO<TipoProcesso> {
	public TipoProcessoDAO() {
		super();
	}

	public TipoProcessoDAO(EntityManager manager) {
		super(manager);
	}
}
