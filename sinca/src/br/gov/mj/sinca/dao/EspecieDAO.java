package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.Especie;

public class EspecieDAO extends SincaAbastractDAO<Especie> {
	public EspecieDAO() {
		super();
	}

	public EspecieDAO(EntityManager manager) {
		super(manager);
	}
}
