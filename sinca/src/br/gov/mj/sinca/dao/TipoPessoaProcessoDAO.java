package br.gov.mj.sinca.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.TipoPessoaProcesso;

public class TipoPessoaProcessoDAO extends SincaAbastractDAO<TipoPessoaProcesso> {
	public TipoPessoaProcessoDAO() {
		super();
	}

	public TipoPessoaProcessoDAO(EntityManager manager) {
		super(manager);
	}
	public static void main(String[] args) {
	    //new TipoPessoaProcessoDAO().lerTodos();
	    List<TipoPessoaProcesso> lista =  new TipoPessoaProcessoDAO().lerTodos();//new TipoPessoaProcessoDAO().lerPorId(1).getPessoaProcessos();
	    for (TipoPessoaProcesso pessoaProcesso : lista) {
		 System.out.println(""+pessoaProcesso.getPessoaProcessos());
	    }

	}
	
}
