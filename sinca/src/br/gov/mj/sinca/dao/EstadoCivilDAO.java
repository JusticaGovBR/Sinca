package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.EstadoCivil;

public class EstadoCivilDAO extends SincaAbastractDAO<EstadoCivil> {
	public EstadoCivilDAO() {
		super();
	}

	public EstadoCivilDAO(EntityManager manager) {
		super(manager);
	}
	
	public static void main(String[] args) {
	    new EstadoCivilDAO().abrirTransacao();
	    new EstadoCivilDAO().lerTodos();
	    new EstadoCivilDAO().desfazerTransacao();
	}
}
