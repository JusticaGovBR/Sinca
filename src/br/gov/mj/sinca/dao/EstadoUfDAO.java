package br.gov.mj.sinca.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.Estado;

public class EstadoUfDAO extends SincaAbastractDAO<Estado> {
	public EstadoUfDAO() {
		super();
	}

	public EstadoUfDAO(EntityManager manager) {
		super(manager);
	}

	public static void main(String[] args) {
		EstadoUfDAO dao = new EstadoUfDAO();
		List<Estado> listaEstado = dao.lerTodos();
		for (Estado estado : listaEstado) {
			System.out.println(estado.getSigla());
		}
	}
}
