package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.TipoDocumento;

public class TipoDocumentoPessoaDAO extends SincaAbastractDAO<TipoDocumento> {
	public TipoDocumentoPessoaDAO() {
		super();
	}

	public TipoDocumentoPessoaDAO(EntityManager manager) {
		super(manager);
	}
}
