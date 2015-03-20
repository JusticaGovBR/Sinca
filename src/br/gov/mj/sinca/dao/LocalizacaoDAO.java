package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.Localizacao;

public class LocalizacaoDAO extends SincaAbastractDAO<Localizacao> {
	public LocalizacaoDAO() {
		super();
	}

	public LocalizacaoDAO(EntityManager manager) {
		super(manager);
	}
}
