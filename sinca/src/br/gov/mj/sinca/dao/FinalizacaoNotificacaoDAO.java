package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.FinalizacaoNotificacao;

public class FinalizacaoNotificacaoDAO extends SincaAbastractDAO<FinalizacaoNotificacao> {

    public FinalizacaoNotificacaoDAO() {
	super();
    }

    public FinalizacaoNotificacaoDAO(EntityManager manager) {
	super(manager);
    }

}
