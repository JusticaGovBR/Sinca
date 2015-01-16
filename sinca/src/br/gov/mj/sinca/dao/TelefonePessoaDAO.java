package br.gov.mj.sinca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.gov.mj.sinca.entidades.TelefonePessoa;

public class TelefonePessoaDAO extends SincaAbastractDAO<TelefonePessoa> {
    
	public TelefonePessoaDAO() {
		super();
	}

	public TelefonePessoaDAO(EntityManager manager) {
		super(manager);
	}
	
	@SuppressWarnings("unchecked")
	public List<TelefonePessoa> listarTelefonePorIdPessoa(Long idPessoa){
	        abrirTransacao();
	        Query query = getEntityManager().createQuery(
				"SELECT t FROM TelefonePessoa t where t.pessoa.idPessoa ="+idPessoa);
	        gravarTransacao();
		return query.getResultList();
	}

	
	
}
