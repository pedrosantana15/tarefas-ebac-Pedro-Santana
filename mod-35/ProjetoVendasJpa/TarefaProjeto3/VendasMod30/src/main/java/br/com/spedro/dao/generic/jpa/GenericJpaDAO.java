package br.com.spedro.dao.generic.jpa;

import br.com.spedro.dao.Persistente;
import br.com.spedro.exceptions.DAOException;
import br.com.spedro.exceptions.MaisDeUmRegistroException;
import br.com.spedro.exceptions.TableException;
import br.com.spedro.exceptions.TipoChaveNaoEncontradaException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class GenericJpaDAO<T extends Persistente, E extends Serializable> implements IGenericJpaDAO<T, E>{

    protected EntityManagerFactory entityManagerFactory;
    protected EntityManager entityManager;
    private Class<T> persistenteClass;

    public GenericJpaDAO(Class<T> persistenteClass){
        this.persistenteClass = persistenteClass;
    }

    @Override
    public T cadastrar(T entity) throws TipoChaveNaoEncontradaException, DAOException {
        openConnection();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        closeConnection();

        return entity;
    }

    @Override
    public void excluir(T entity) throws DAOException {
        openConnection();
        entity = entityManager.merge(entity);
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
        closeConnection();
    }

    @Override
    public T alterar(T entity) throws TipoChaveNaoEncontradaException, DAOException {
        openConnection();
        entity = entityManager.merge(entity);
        entityManager.getTransaction().commit();
        closeConnection();

        return entity;
    }

    @Override
    public T consultar(E id) throws MaisDeUmRegistroException, TableException, DAOException {
        openConnection();
        T entity = entityManager.find(this.persistenteClass, id);
        entityManager.getTransaction().commit();
        closeConnection();

        return entity;
    }

    @Override
    public Collection<T> buscarTodos() throws DAOException {
        openConnection();
        List<T> list = entityManager
                .createQuery(getSelectSql(), this.persistenteClass)
                .getResultList();
        closeConnection();

        return list;
    }

    private String getSelectSql(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT o FROM ");
        sb.append(this.persistenteClass.getSimpleName());
        sb.append(" o");

        return sb.toString();
    }

    protected void openConnection(){
        entityManagerFactory =
                Persistence.createEntityManagerFactory("Pedro");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    protected void closeConnection(){
        entityManagerFactory.close();
        entityManager.close();
    }
}
