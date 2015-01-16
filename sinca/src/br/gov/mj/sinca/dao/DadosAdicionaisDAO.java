package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.DadosAdicionais;

public class DadosAdicionaisDAO extends SincaAbastractDAO<DadosAdicionais> {
	public DadosAdicionaisDAO() {
		super();
	}

	public DadosAdicionaisDAO(EntityManager manager) {
		super(manager);
	}
}
