package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.Natureza;

public class NaturezaDAO extends SincaAbastractDAO<Natureza> {
	public NaturezaDAO() {
		super();
	}

	public NaturezaDAO(EntityManager manager) {
		super(manager);
	}
}
