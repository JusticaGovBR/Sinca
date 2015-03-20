package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.FinalizacaoOrgao;

public class FinalizacaoOrgaoDAO extends SincaAbastractDAO<FinalizacaoOrgao> {

    public FinalizacaoOrgaoDAO() {
	super();
    }

    public FinalizacaoOrgaoDAO(EntityManager manager) {
	super(manager);
    }


}
