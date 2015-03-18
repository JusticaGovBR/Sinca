package br.gov.mj.sinca.dao;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;

import br.gov.mj.sinca.entidades.Parametro;

public class ParametroDAO extends SincaAbastractDAO<Parametro> {
    public ParametroDAO() {
	super();
    }

    public ParametroDAO(EntityManager manager) {
	super(manager);
    }

    public HashMap<String, Parametro> buscarParamentro() {
	HashMap<String, Parametro> map = new HashMap<String, Parametro>();
	List<Parametro> lista = new ParametroDAO().lerTodos();
	for (Parametro parametro : lista) {
	    map.put(parametro.getNomeParametro(), parametro);
	}
	return map;
    }

    public static void main(String[] args) {
	List<Parametro> lista = new ParametroDAO().lerTodos();
	for (Parametro parametro : lista) {
	    System.out.println(parametro.getNomeParametro());
	}

    }
}
