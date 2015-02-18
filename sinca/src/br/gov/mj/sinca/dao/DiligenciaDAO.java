package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.Diligencia;

public class DiligenciaDAO extends SincaAbastractDAO<Diligencia> {
	public DiligenciaDAO() {
		super();
	}

	public DiligenciaDAO(EntityManager manager) {
		super(manager);
	}
	
		
}
