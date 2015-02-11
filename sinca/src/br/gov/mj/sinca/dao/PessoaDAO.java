package br.gov.mj.sinca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.gov.mj.sinca.entidades.Pessoa;

public class PessoaDAO extends SincaAbastractDAO<Pessoa> {

    public static Integer PESSOA_FISICA = 1;
    public static Integer PESSOA_JURIDICA = 2;
    
    public PessoaDAO() {
	super();
    }

    public PessoaDAO(EntityManager manager) {
	super(manager);
    }

    @SuppressWarnings("unchecked")
    public List<Pessoa> listaPessoaPorNomeLk(String nome) {
	Query query = getEntityManager().createQuery("SELECT p FROM Pessoa p where p.nomePessoa like '" + nome + "%'");
	return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Pessoa> listaPessoaPorNomeCpf(String numCpf, String nome) {
	List<Pessoa> lista = null;
	String where = "";
	Query query = null;
	if (numCpf != null && nome == null) {
	    where = "where p.codTipo=p.numCpfCnpj like '%"+numCpf+"%'";
	    query = getEntityManager().createQuery("SELECT p FROM Pessoa p " + where);
	} else if (numCpf == null && nome != null) {
	    where = "where p.nomePessoa=:nome";
	    query = getEntityManager().createQuery("SELECT p FROM Pessoa p " + where);
	    query.setParameter("nome", nome);
	} else if (numCpf != null && nome != null) {
	    where = "where  p.numCpfCnpj=:numCpf and p.nomePessoa=:nome";
	    query = getEntityManager().createQuery("SELECT p FROM Pessoa p " + where);

	    query.setParameter("numCpf", numCpf);
	    query.setParameter("nome", nome);
	}

	lista = query.getResultList();
	return lista;
    }
    
    public static void main(String[] args) {
	PessoaDAO dao = new PessoaDAO();
	List<Pessoa> lista = dao.listaPessoaPorNomeLk("Sebastião");
	for (Pessoa pessoa : lista) {
	     System.out.println(pessoa.getNomePessoa());
	}
    }

}
