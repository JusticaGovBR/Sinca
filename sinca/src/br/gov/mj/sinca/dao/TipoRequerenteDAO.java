package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.TipoRequerente;

public class TipoRequerenteDAO extends SincaAbastractDAO<TipoRequerente> {
	public TipoRequerenteDAO() {
		super();
	}

	public TipoRequerenteDAO(EntityManager manager) {
		super(manager);
	}
}
