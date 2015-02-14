package br.gov.mj.sinca.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.gov.mj.sinca.entidades.Processo;

public class ProcessoDAO extends SincaAbastractDAO<Processo> {

    public ProcessoDAO() {
	super();
    }

    public ProcessoDAO(EntityManager manager) {
	super(manager);
    }

    @SuppressWarnings("unchecked")
    public List<Processo> bustaProcessoPor(Integer numCA, String numProcessoSEI) {
	List<Processo> lista = null;
	String where = "";
	Query query = null;
	if (numCA != null && numProcessoSEI == null) {
	    where = "where p.numProtocoloCa=:numCA";
	    query = getEntityManager().createQuery("SELECT p FROM Processo p " + where);
	    query.setParameter("numCA", numCA);
	} else if (numCA == null && numProcessoSEI != null) {
	    where = "where p.numProtocoloMj=:numProcessoSei";
	    query = getEntityManager().createQuery("SELECT p FROM Processo p " + where);
	    query.setParameter("numProcessoSei", numProcessoSEI);
	} else if (numCA != null && numProcessoSEI != null) {
	    where = "where p.numProtocoloMj=:numProcessoSei and p.numProtocoloCa=:numCA";
	    query = getEntityManager().createQuery("SELECT p FROM Processo p " + where);

	    query.setParameter("numCA", numCA);
	    query.setParameter("numProcessoSei", numProcessoSEI);
	}

	lista = query.getResultList();
	return lista;
    }

    @SuppressWarnings("unchecked")
    public List<String> listarDataProtocoloCa() {
	Query query = getEntityManager().
		createNativeQuery("SELECT DISTINCT\r\n" + 
			"    DATE_FORMAT(DATA_PROTOCOLO_CA,'%d/%m/%Y') AS DATA_CADASTRO\r\n" + 
			"FROM\r\n" + 
			"    Processo\r\n" + 
			"WHERE\r\n" + 
			"    DATA_PROTOCOLO_CA IS NOT NULL\r\n" + 
			"ORDER BY\r\n" + 
			"    DATA_PROTOCOLO_CA");
	List<String> listaData = query.getResultList();
	return listaData;
    }

    @SuppressWarnings("unchecked")
    public List<String> listarDataProtocoloSEI() {
	Query query = getEntityManager().
		createNativeQuery("SELECT DISTINCT\r\n" + 
			"    DATE_FORMAT(DATA_PROTOCOLO_CA,'%d/%m/%Y') AS DATA_CADASTRO\r\n" + 
			"FROM\r\n" + 
			"    Processo\r\n" + 
			"WHERE\r\n" + 
			"    DATA_PROTOCOLO_CA IS NOT NULL\r\n" + 
			"ORDER BY\r\n" + 
			"    DATA_PROTOCOLO_CA");
	List<String> listaData = query.getResultList();
	return listaData;
    }

    public Integer bustaUltimoNumeroProcessoCA() {
	Query query = getEntityManager().createQuery("SELECT max(p.numProtocoloCa) FROM Processo p");
	Integer id = (Integer) query.getSingleResult();
	return id;
    }

    public static void main(String[] args) {
	System.out.println(new ProcessoDAO().bustaUltimoNumeroProcessoCA());
	// System.out.println(new ProcessoDAO().lerTodos().size());
	
    }
}
