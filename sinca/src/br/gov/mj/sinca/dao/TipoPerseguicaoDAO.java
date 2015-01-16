package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.TipoPerseguicao;

public class TipoPerseguicaoDAO extends SincaAbastractDAO<TipoPerseguicao> {
	public TipoPerseguicaoDAO() {
		super();
	}

	public TipoPerseguicaoDAO(EntityManager manager) {
		super(manager);
	}
}
