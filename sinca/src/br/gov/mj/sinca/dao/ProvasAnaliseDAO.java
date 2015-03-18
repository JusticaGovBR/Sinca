package br.gov.mj.sinca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.gov.mj.sinca.entidades.ProvasAnalise;

public class ProvasAnaliseDAO extends SincaAbastractDAO<ProvasAnalise> {

    public ProvasAnaliseDAO() {
	super();
    }

    public ProvasAnaliseDAO(EntityManager manager) {
	super(manager);
    }

    @SuppressWarnings("unchecked")
    public List<ProvasAnalise> listarProvaPorAnalise(Long idAnalise) {
	Query query = getEntityManager().createQuery(
		"SELECT d FROM ProvasAnalise d where d.analiseProcesso.idAnalise=" + idAnalise);
	return query.getResultList();
    }

    public static void main(String[] args) {
	ProvasAnaliseDAO dao = new ProvasAnaliseDAO();
	dao.listarProvaPorAnalise(44l);
    }

}
