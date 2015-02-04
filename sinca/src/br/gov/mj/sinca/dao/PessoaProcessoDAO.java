package br.gov.mj.sinca.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.gov.mj.sinca.entidades.PessoaProcesso;

public class PessoaProcessoDAO extends SincaAbastractDAO<PessoaProcesso> {

    public PessoaProcessoDAO() {
	super();
    }

    public PessoaProcessoDAO(EntityManager manager) {
	super(manager);
    }

    @SuppressWarnings("unchecked")
    public List<PessoaProcesso> listarProcesso(long idProcesso) {
	Query query = getEntityManager().createQuery(
		"SELECT p FROM PessoaProcesso p where p.processo.idProcesso = " + idProcesso);
	return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<PessoaProcesso> listarProcessoPorIdPessoa(long idPessoa) {
	Query query = getEntityManager().createQuery(
		"SELECT p FROM PessoaProcesso p where p.pessoa.idPessoa = " + idPessoa);
	return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<PessoaProcesso> ListarProcesso(Integer numCA, String numProcessoSEI) {
	List<PessoaProcesso> lista = null;
	String where = "";
	Query query = null;
	if (numCA != null && numProcessoSEI == null) {
	    where = "where p.processo.numProtocoloCa=:numCA";
	    query = getEntityManager().createQuery("SELECT p FROM PessoaProcesso p " + where);
	    query.setParameter("numCA", numCA);
	} else if (numCA == null && numProcessoSEI != null) {
	    where = "where p.processo.numProtocoloMj=:numProcessoSei";
	    query = getEntityManager().createQuery("SELECT p FROM PessoaProcesso p " + where);
	    query.setParameter("numProcessoSei", numProcessoSEI);
	} else if (numCA != null && numProcessoSEI != null) {
	    where = "where p.processo.numProtocoloMj=:numProcessoSei and p.numProtocoloCa=:numCA";
	    query = getEntityManager().createQuery("SELECT p FROM PessoaProcesso p " + where);

	    query.setParameter("numCA", numCA);
	    query.setParameter("numProcessoSei", numProcessoSEI);
	}

	lista = query.getResultList();
	return lista;
    }

    @SuppressWarnings("unchecked")
    public List<PessoaProcesso> listarProcessoPorDataProtocolo(Date dataProtocoloCA, Date dataProtocoloMJ) {
	List<PessoaProcesso> lista = null;
	String where = "";
	Query query = null;
	if (dataProtocoloCA != null && dataProtocoloMJ == null) {
	    where = "where p.processo.dataProtocoloCa=:dataProtocoloCA";
	    query = getEntityManager().createQuery("SELECT p FROM PessoaProcesso p " + where);
	    query.setParameter("dataProtocoloCA", dataProtocoloCA);
	}else if (dataProtocoloCA == null && dataProtocoloMJ != null){
	    where = "where p.processo.dataProtocoloMJ=:dataProtocoloMJ";
	    query = getEntityManager().createQuery("SELECT p FROM PessoaProcesso p " + where);
	    query.setParameter("dataProtocoloMJ", dataProtocoloMJ);
	}else if(dataProtocoloCA == null && dataProtocoloMJ == null){
	    return new ArrayList<PessoaProcesso>();
	}else{
	    return new ArrayList<PessoaProcesso>();
	}
	lista = query.getResultList();
	return lista;
    }

    public static void main(String[] args) throws ParseException {
	PessoaProcessoDAO dao = new PessoaProcessoDAO();
	Date data = new SimpleDateFormat("dd/MM/yyyy").parse("28/09/2001");
	List<PessoaProcesso> lista = dao.listarProcessoPorDataProtocolo(data, null);
	for (PessoaProcesso pessoaProcesso : lista) {
	    System.out.println("" + pessoaProcesso.getProcesso().getIdProcesso() + " Nome Pessoa "
		    + pessoaProcesso.getPessoa().getNomePessoa());
	}
    }

}
