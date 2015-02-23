package br.gov.mj.sinca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.gov.mj.sinca.entidades.Diligencia;

public class DiligenciaDAO extends SincaAbastractDAO<Diligencia> {
    public DiligenciaDAO() {
	super();
    }

    public DiligenciaDAO(EntityManager manager) {
	super(manager);
    }

    @SuppressWarnings("unchecked")
    public List<Diligencia> listarDiligenciaPorProcesso(Long idProcesso) {
	Query query = getEntityManager().createQuery(
		"SELECT d FROM Diligencia d where d.processo.idProcesso=" + idProcesso);
	return query.getResultList();
    }
    
    public static void main(String[] args) {
	DiligenciaDAO dao = new DiligenciaDAO();
	dao.listarDiligenciaPorProcesso(44l);
    }
 

}
