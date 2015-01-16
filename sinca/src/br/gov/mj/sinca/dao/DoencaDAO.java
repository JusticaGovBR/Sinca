package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.Doenca;

public class DoencaDAO extends SincaAbastractDAO<Doenca> {
	public DoencaDAO() {
		super();
	}

	public DoencaDAO(EntityManager manager) {
		super(manager);
	}
	
		
}
