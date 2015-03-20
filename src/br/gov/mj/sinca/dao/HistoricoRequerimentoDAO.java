package br.gov.mj.sinca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.gov.mj.sinca.entidades.HistoricoRequerimento;

public class HistoricoRequerimentoDAO extends SincaAbastractDAO<HistoricoRequerimento> {
    
	public HistoricoRequerimentoDAO() {
		super();
	}

	public HistoricoRequerimentoDAO(EntityManager manager) {
		super(manager);
	}
	
	@SuppressWarnings("unchecked")
	public List<HistoricoRequerimento> listaHistorico(Long idProcesso){
		Query query = getEntityManager().createQuery(
			"SELECT h FROM HistoricoRequerimento h where h.processo.idProcesso = " + idProcesso);
		return query.getResultList();
	}
	
	public static void main(String[] args) {
	    HistoricoRequerimentoDAO  dao =  new HistoricoRequerimentoDAO();
	    dao.listaHistorico(84l);
	}
	
}
