package br.com.spedro.dao.jpa;

import br.com.spedro.dao.generic.jpa.GenericJpaDAO;
import br.com.spedro.domain.jpa.ClienteJpa;
import br.com.spedro.domain.jpa.ProdutoJpa;
import br.com.spedro.domain.jpa.VendaJpa;
import br.com.spedro.exceptions.DAOException;
import br.com.spedro.exceptions.TipoChaveNaoEncontradaException;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;

public class VendaJpaDAO extends GenericJpaDAO<VendaJpa, Long> implements IVendaJpaDAO<VendaJpa>{

    public VendaJpaDAO() {
        super(VendaJpa.class, "Postgre1");
    }

    public void finalizarVenda(VendaJpa venda) throws DAOException, TipoChaveNaoEncontradaException {
        super.alterar(venda);
    }

    public void cancelarVenda(VendaJpa venda) throws DAOException, TipoChaveNaoEncontradaException {
        super.alterar(venda);
    }

    @Override
    public void excluir(VendaJpa entity) throws DAOException {
        throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
    }

    @Override
    public VendaJpa cadastrar(VendaJpa entity) throws TipoChaveNaoEncontradaException, DAOException {
        try{
            openConnection();
            entity.getProdutos().forEach(p -> {
                ProdutoJpa produto = entityManager.merge(p.getProduto());
                p.setProduto(produto);
            });
            ClienteJpa cliente = entityManager.merge(entity.getCliente());
            entity.setCliente(cliente);

            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            closeConnection();

            return entity;
        } catch (Exception e) {
            throw new DAOException("ERRO CADASTRANDO VENDA ", e);
        }
    }

    public VendaJpa consultarComCriteria(Long id){
        openConnection();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<VendaJpa> query = builder.createQuery(VendaJpa.class);
        Root<VendaJpa> root = query.from(VendaJpa.class);
        root.fetch("cliente");
        root.fetch("produtos");
        query.select(root).where(builder.equal(root.get("id"), id));

        TypedQuery<VendaJpa> typedQuery =
                entityManager.createQuery(query);
        VendaJpa venda = typedQuery.getSingleResult();

        closeConnection();

        return venda;
    }

    @Override
    public Collection<VendaJpa> buscarTodos() throws DAOException {
        openConnection();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<VendaJpa> query = builder.createQuery(VendaJpa.class);
        Root<VendaJpa> root = query.from(VendaJpa.class);
        root.fetch("cliente");
        root.fetch("produtos");
        query.select(root);

        TypedQuery<VendaJpa> typedQuery =
                entityManager.createQuery(query);
        Collection<VendaJpa> vendas = typedQuery.getResultList();

        closeConnection();

        return vendas;
    }
}
