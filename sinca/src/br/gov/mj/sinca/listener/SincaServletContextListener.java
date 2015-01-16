package br.gov.mj.sinca.listener;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.gov.mj.sinca.ConstantSinca;
import br.gov.mj.sinca.dao.EstadoCivilDAO;
import br.gov.mj.sinca.dao.EstadoUfDAO;
import br.gov.mj.sinca.dao.LocalizacaoDAO;

public class SincaServletContextListener implements ServletContextListener{

	
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	
	public void contextInitialized(ServletContextEvent context) {
		carregaListaDominio(context);
	}

	private void carregaListaDominio(ServletContextEvent context) {
		try {
		    System.out.println("Carregando lista de dominios...."+new Date());
			
			context.getServletContext().setAttribute(ConstantSinca.LISTA_ESTADO_CIVIL, new EstadoCivilDAO().lerTodos());
			//context.getServletContext().setAttribute(ConstantSinca.LISTA_LOCALIZACAO, new LocalizacaoDAO().lerTodos());
			//context.getServletContext().setAttribute(ConstantSinca.LISTA_UF, new EstadoUfDAO().lerTodos());
			
		      System.out.println("Contexto iniciado e lista de dominios carregadas...."+new Date());	
			
		} catch (Exception e) {
			System.err.println("Erro ao carregar as listas "+e.getMessage());
		}
	}
	
	
}
