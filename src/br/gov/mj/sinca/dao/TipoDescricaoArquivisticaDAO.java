package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.TipoDescricaoArquivistica;

public class TipoDescricaoArquivisticaDAO extends SincaAbastractDAO<TipoDescricaoArquivistica> {
    
	public TipoDescricaoArquivisticaDAO() {
		super();
	}

	public TipoDescricaoArquivisticaDAO(EntityManager manager) {
		super(manager);
	}

}
