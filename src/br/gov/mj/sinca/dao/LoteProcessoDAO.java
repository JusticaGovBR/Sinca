package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.LoteProcesso;

public class LoteProcessoDAO extends SincaAbastractDAO<LoteProcesso> {

    public LoteProcessoDAO() {
	super();
    }

    public LoteProcessoDAO(EntityManager manager) {
	super(manager);
    }
    
}
