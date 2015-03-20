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
	    DoencaPessoaDAO dao =  new DoencaPessoaDAO();
	    List<DoencaPessoa> d = dao.lerTodos();
	    dao.logger.info("Lendo Doença");
	    for (DoencaPessoa doencaPessoa : d) {
		dao.logger.info(doencaPessoa.getDoenca());
	    }
	}	
}
