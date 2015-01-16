package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.TipoProcurador;

public class TipoProcuradorDAO extends SincaAbastractDAO<TipoProcurador> {
	public TipoProcuradorDAO() {
		super();
	}

	public TipoProcuradorDAO(EntityManager manager) {
		super(manager);
	}
}
