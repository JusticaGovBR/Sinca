package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.SituacaoCadastro;

public class SituacaoCadastroDAO extends SincaAbastractDAO<SituacaoCadastro> {
	public SituacaoCadastroDAO() {
		super();
	}

	public SituacaoCadastroDAO(EntityManager manager) {
		super(manager);
	}
}
