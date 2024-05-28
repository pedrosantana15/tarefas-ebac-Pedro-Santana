package br.com.spedro.dao.generics;

import br.com.spedro.domain.IPersistente;
import br.com.spedro.exceptions.TipoChaveNaoEncontradaException;
import java.io.Serializable;
import java.util.Collection;

public interface IGenericDAO <T extends IPersistente, E extends Serializable> {

    public Boolean cadastrar(T entity) throws TipoChaveNaoEncontradaException;

    public void excluir(E valor);

    public void alterar(T entity) throws TipoChaveNaoEncontradaException;

    public T consultar(E valor);

    public Collection<T> buscarTodos();
}
