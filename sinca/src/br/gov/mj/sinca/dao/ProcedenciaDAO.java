package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.Procedencia;

public class ProcedenciaDAO extends SincaAbastractDAO<Procedencia> {
	public ProcedenciaDAO() {
		super();
	}

	public ProcedenciaDAO(EntityManager manager) {
		super(manager);
	}
}
