package br.gov.mj.sinca.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;

public abstract class SincaAbastractDAO<T> {

    protected Class<T> persistentClass;
    private EntityManager manager;

    /**
     * M�todo construtor que
     */
    @SuppressWarnings("unchecked")
    public SincaAbastractDAO() {
	super();
	
	// pegar a classe persistente por reflex�o
	Type tipo = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	this.persistentClass = (Class<T>) tipo;

	// pega o EntityManager padr�o da ThreadAtual
	this.manager = JPAUtil.getEntityManager();
    }

    public SincaAbastractDAO(EntityManager manager) {
	this();
	this.manager = manager;
    }

    public EntityManager getEntityManager() {
	// caso o EntityManager tenha sido fechado, deve criar um novo
	if ((this.manager != null) && (!this.manager.isOpen()))
	    this.manager = JPAUtil.getEntityManager();

	return this.manager;
    }

    public T lerPorId(Object id) {
	return (T) this.getEntityManager().find(this.persistentClass, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> lerTodos() {
	List<T> resultado =  getEntityManager().createQuery("select o from " + this.persistentClass.getSimpleName() + " o")
		.getResultList();
	
	return resultado;
    }

    @SuppressWarnings("unchecked")
    public List<T> lerTodosAtivos() {
	List<T> resultado =  getEntityManager().createQuery("select o from " 
           + this.persistentClass.getSimpleName() + " o where o.ativo = 1")
		.getResultList();
	return resultado;
    }

    public T salvar(T objeto) {
	boolean transacaoAtiva = this.getEntityManager().getTransaction().isActive();

	if (!transacaoAtiva) {
	    this.abrirTransacao();
	    transacaoAtiva = this.getEntityManager().getTransaction().isActive();
	}
	objeto = this.getEntityManager().merge(objeto);

	if (transacaoAtiva)
	    this.gravarTransacao();

	return objeto;
    }

    public void excluir(T objeto) {
	boolean transacaoAtiva = this.getEntityManager().getTransaction().isActive();

	if (!transacaoAtiva)
	    this.abrirTransacao();

	this.getEntityManager().remove(objeto);

	if (!transacaoAtiva)
	    this.gravarTransacao();

    }

    public void abrirTransacao() {
	this.getEntityManager().getTransaction().begin();
    }

    public void gravarTransacao() {
	if (this.getEntityManager().getTransaction().isActive()) {
	    this.getEntityManager().flush();
	    this.getEntityManager().getTransaction().commit();
	}
    }

    public void desfazerTransacao() {
	this.getEntityManager().getTransaction().rollback();
    }

}