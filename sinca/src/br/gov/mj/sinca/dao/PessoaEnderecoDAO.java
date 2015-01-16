package br.gov.mj.sinca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.gov.mj.sinca.entidades.PessoaEndereco;

public class PessoaEnderecoDAO extends SincaAbastractDAO<PessoaEndereco> {
	public PessoaEnderecoDAO() {
		super();
	}

	public PessoaEnderecoDAO(EntityManager manager) {
		super(manager);
	}
	@SuppressWarnings("unchecked")
	public List<PessoaEndereco> listarPorIdPessoa(Long idPessoa){
	        Query query = getEntityManager().createQuery(
				"SELECT p FROM PessoaEndereco p where p.pessoa.idPessoa ="+idPessoa);
		return query.getResultList();
	}
	
	public static void main(String[] args) {
	    PessoaEnderecoDAO  dao = new PessoaEnderecoDAO();
	    dao.listarPorIdPessoa(49716l);
	}
}
