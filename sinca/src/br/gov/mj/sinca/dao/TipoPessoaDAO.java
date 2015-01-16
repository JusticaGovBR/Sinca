package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.TipoPessoa;

public class TipoPessoaDAO extends SincaAbastractDAO<TipoPessoa> {
    
        public static Integer PESSOA_FISICA = 1;
        public static Integer PESSOA_JURIDICA = 2;
    
	public TipoPessoaDAO() {
		super();
	}

	public TipoPessoaDAO(EntityManager manager) {
		super(manager);
	}
	

}
