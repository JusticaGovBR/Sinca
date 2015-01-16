package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.Assunto;

public class AssuntoDAO extends SincaAbastractDAO<Assunto> {
	public AssuntoDAO() {
		super();
	}

	public AssuntoDAO(EntityManager manager) {
		super(manager);
	}
}
