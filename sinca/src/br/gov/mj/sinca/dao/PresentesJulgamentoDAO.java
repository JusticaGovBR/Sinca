package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.PresentesJulgamento;

public class PresentesJulgamentoDAO extends SincaAbastractDAO<PresentesJulgamento> {

    public PresentesJulgamentoDAO() {
	super();
    }

    public PresentesJulgamentoDAO(EntityManager manager) {
	super(manager);
    }
    public static void main(String[] args) {
	new PresentesJulgamentoDAO().lerTodos();
    }
    
}
