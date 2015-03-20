package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.JulgamentoProcesso;

public class JulgamentoProcessoDAO extends SincaAbastractDAO<JulgamentoProcesso> {
    
	public JulgamentoProcessoDAO() {
		super();
	}

	public JulgamentoProcessoDAO(EntityManager manager) {
		super(manager);
	}
	public static void main(String[] args) {
	    JulgamentoProcessoDAO dao = new JulgamentoProcessoDAO();
	    dao.lerPorId(3l).getReparacaoEconomicaJulgamentos();
	}
}
