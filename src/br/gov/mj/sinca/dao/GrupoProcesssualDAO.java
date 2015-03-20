package br.gov.mj.sinca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.gov.mj.sinca.entidades.GrupoProcessual;

public class GrupoProcesssualDAO extends SincaAbastractDAO<GrupoProcessual> {
	public GrupoProcesssualDAO() {
		super();
	}

	public GrupoProcesssualDAO(EntityManager manager) {
		super(manager);
	}
	
	@SuppressWarnings("unchecked")
	public List<GrupoProcessual> listaSubGrupoPorNome(String nome) {
		Query query = getEntityManager().createQuery(
				"SELECT s FROM GrupoProcessual s where s.descGrupoProcessual like '"
						+ nome + "%'");
		return query.getResultList();
	}

	public static void main(String[] args) {
		GrupoProcesssualDAO dao = new GrupoProcesssualDAO();
		List<GrupoProcessual> lista = dao
				.listaSubGrupoPorNome("Forças Auxiliares  Forças Auxiliares");
		for (GrupoProcessual subGrupo : lista) {
			System.out.println(subGrupo.getDescGrupoProcessual());
		}
	}
}
