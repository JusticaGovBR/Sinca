package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.TipoEndereco;

public class TipoEnderecoDAO extends SincaAbastractDAO<TipoEndereco> {
	public TipoEnderecoDAO() {
		super();
	}

	public TipoEnderecoDAO(EntityManager manager) {
		super(manager);
	}
}
