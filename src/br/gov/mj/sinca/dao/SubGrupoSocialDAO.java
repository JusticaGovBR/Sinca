package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.SubGrupoSocial;

public class SubGrupoSocialDAO extends SincaAbastractDAO<SubGrupoSocial> {
	public SubGrupoSocialDAO() {
		super();
	}

	public SubGrupoSocialDAO(EntityManager manager) {
		super(manager);
	}
}
