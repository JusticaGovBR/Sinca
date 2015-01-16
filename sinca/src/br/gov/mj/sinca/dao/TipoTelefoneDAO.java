package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.TipoTelefone;

public class TipoTelefoneDAO extends SincaAbastractDAO<TipoTelefone> {
	public TipoTelefoneDAO() {
		super();
	}

	public TipoTelefoneDAO(EntityManager manager) {
		super(manager);
	}
}
