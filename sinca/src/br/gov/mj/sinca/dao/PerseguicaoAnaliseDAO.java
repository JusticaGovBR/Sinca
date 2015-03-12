package br.gov.mj.sinca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.gov.mj.sinca.entidades.PerseguicaoAnalise;

public class PerseguicaoAnaliseDAO extends SincaAbastractDAO<PerseguicaoAnalise> {

    public PerseguicaoAnaliseDAO() {
	super();
    }

    public PerseguicaoAnaliseDAO(EntityManager manager) {
	super(manager);
    }

    @SuppressWarnings("unchecked")
    public List<PerseguicaoAnalise> listarPerseguicaoPorAnalise(Long idAnalise) {
	Query query = getEntityManager().createQuery(
		"SELECT d FROM PerseguicaoAnalise d where d.analiseProcesso.idAnalise=" + idAnalise);
	return query.getResultList();
    }

    public static void main(String[] args) {
	PerseguicaoAnaliseDAO dao = new PerseguicaoAnaliseDAO();
	dao.listarPerseguicaoPorAnalise(44l);
    }

}
