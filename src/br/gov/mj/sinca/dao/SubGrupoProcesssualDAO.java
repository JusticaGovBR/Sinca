package br.gov.mj.sinca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.gov.mj.sinca.entidades.SubGrupoProcessual;

public class SubGrupoProcesssualDAO extends
		SincaAbastractDAO<SubGrupoProcessual> {
	public SubGrupoProcesssualDAO() {
		super();
	}

	public SubGrupoProcesssualDAO(EntityManager manager) {
		super(manager);
	}

	@SuppressWarnings("unchecked")
	public List<SubGrupoProcessual> listaSubGrupoPorNome(String nome) {
		Query query = getEntityManager().createQuery(
				"SELECT s FROM SubGrupoProcessual s where s.descSubGrupoProcessual like '"
						+ nome + "%'");
		return query.getResultList();
	}

	public static void main(String[] args) {
		SubGrupoProcesssualDAO dao = new SubGrupoProcesssualDAO();
		List<SubGrupoProcessual> lista = dao
				.listaSubGrupoPorNome("Forças Auxiliares");
		for (SubGrupoProcessual subGrupo : lista) {
			System.out.println(subGrupo.getDescSubGrupoProcessual());
		}
	}
}
