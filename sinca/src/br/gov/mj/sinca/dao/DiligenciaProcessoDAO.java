package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.Diligencia;
import br.gov.mj.sinca.entidades.DiligenciaProcesso;

public class DiligenciaProcessoDAO extends SincaAbastractDAO<DiligenciaProcesso> {
	public DiligenciaProcessoDAO() {
		super();
	}

	public DiligenciaProcessoDAO(EntityManager manager) {
		super(manager);
	}
	
		
}
