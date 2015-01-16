package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.GrupoSocial;

public class GrupoSocialDAO extends SincaAbastractDAO<GrupoSocial> {
	public GrupoSocialDAO() {
		super();
	}

	public GrupoSocialDAO(EntityManager manager) {
		super(manager);
	}
}
