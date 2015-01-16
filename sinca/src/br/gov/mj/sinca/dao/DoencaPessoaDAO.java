package br.gov.mj.sinca.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.DoencaPessoa;

public class DoencaPessoaDAO extends SincaAbastractDAO<DoencaPessoa> {
	public DoencaPessoaDAO() {
		super();
	}

	public DoencaPessoaDAO(EntityManager manager) {
		super(manager);
	}
	
	public static void main(String[] args) {
	    List<DoencaPessoa> d = new DoencaPessoaDAO().lerTodos();
	    for (DoencaPessoa doencaPessoa : d) {
		 System.out.println(doencaPessoa.getDoenca());
	    }
	}	
}
