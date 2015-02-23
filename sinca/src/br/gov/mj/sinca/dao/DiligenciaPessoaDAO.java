package br.gov.mj.sinca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.gov.mj.sinca.entidades.DiligenciaPessoa;
import br.gov.mj.sinca.entidades.PessoaFisica;

public class DiligenciaPessoaDAO extends SincaAbastractDAO<DiligenciaPessoa> {
    public DiligenciaPessoaDAO() {
	super();
    }

    public DiligenciaPessoaDAO(EntityManager manager) {
	super(manager);
    }

    public void excluirPorIdDiligencia(Long ID) {
	EntityManager entityManager = JPAUtil.getFactory().createEntityManager();
        	entityManager.getTransaction().begin();
        	Query query = entityManager.createNativeQuery("DELETE FROM DILIGENCIA_PESSOA WHERE ID_DILIGENCIA="+ ID);
        	query.executeUpdate();
	entityManager.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    public List<PessoaFisica> listarDiligenciados(Long idDiligencia) {
	Query query = getEntityManager().createQuery(
		"SELECT d.pessoaFisica FROM DiligenciaPessoa d where d.diligencia.idDiligencia=" + idDiligencia);
	return query.getResultList();
    }

    public static void main(String[] args) {
	List<PessoaFisica> lista = new DiligenciaPessoaDAO().listarDiligenciados(6l);
	for (PessoaFisica pessoaFisica : lista) {
	    System.out.println(pessoaFisica.getNomePessoa());
	}
    }

}
