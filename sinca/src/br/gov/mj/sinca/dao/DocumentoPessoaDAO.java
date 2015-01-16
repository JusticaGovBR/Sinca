package br.gov.mj.sinca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.gov.mj.sinca.entidades.DocumentoPessoa;

public class DocumentoPessoaDAO extends SincaAbastractDAO<DocumentoPessoa> {
	public DocumentoPessoaDAO() {
		super();
	}

	public DocumentoPessoaDAO(EntityManager manager) {
		super(manager);
	}
	
	@SuppressWarnings("unchecked")
	public List<DocumentoPessoa> listarDocumentoPorIdPessoa(Long idPessoa){
	        Query query = getEntityManager().createQuery(
				"SELECT d FROM DocumentoPessoa d where d.pessoa.idPessoa ="+idPessoa);
		return query.getResultList();
	}

	
	
}
