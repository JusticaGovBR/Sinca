package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.Endereco;

public class EnderecoDAO extends SincaAbastractDAO<Endereco> {
	public EnderecoDAO() {
		super();
	}

	public EnderecoDAO(EntityManager manager) {
		super(manager);
	}
}
