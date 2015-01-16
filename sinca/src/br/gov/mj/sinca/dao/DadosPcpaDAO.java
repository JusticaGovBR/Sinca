package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.DadosPcpa;

public class DadosPcpaDAO extends SincaAbastractDAO<DadosPcpa> {
	public DadosPcpaDAO() {
		super();
	}

	public DadosPcpaDAO(EntityManager manager) {
		super(manager);
	}
}
