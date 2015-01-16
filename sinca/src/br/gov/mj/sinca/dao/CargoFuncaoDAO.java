package br.gov.mj.sinca.dao;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.CargoFuncao;

public class CargoFuncaoDAO extends SincaAbastractDAO<CargoFuncao> {
	public CargoFuncaoDAO() {
		super();
	}

	public CargoFuncaoDAO(EntityManager manager) {
		super(manager);
	}
}
