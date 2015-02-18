package br.gov.mj.sinca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.gov.mj.sinca.entidades.PessoaFisica;
import br.gov.mj.sinca.entidades.PessoaJuridica;

public class PessoaJuridicaDAO extends SincaAbastractDAO<PessoaJuridica> {

    public PessoaJuridicaDAO() {
	super();
    }

    public PessoaJuridicaDAO(EntityManager manager) {
	super(manager);
    }

    @SuppressWarnings("unchecked")
    public List<PessoaJuridica> listaPessoaPorNomeLk(String nome) {
	Query query = getEntityManager().createQuery("SELECT p FROM PessoaJuridica p where p.nomePessoa like '" + nome + "%'");
	return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<PessoaFisica> listaPessoaJuridicaCNPJ(String numCnpj) {
	Query query = getEntityManager().createQuery("SELECT p FROM PessoaJuridica p where p.numCnpj '"+numCnpj+"'");
	return query.getResultList();
    }
    
    public static void main(String[] args) {
	PessoaJuridicaDAO dao = new PessoaJuridicaDAO();
	List<PessoaJuridica> lista = dao.listaPessoaPorNomeLk("Arquivo Nascional");
	for (PessoaJuridica pessoa : lista) {
	     System.out.println(pessoa.getNomePessoa());
	}
    }

}
