package main.br.com.spedro.dao;

import main.br.com.spedro.domain.Marca;

import java.util.List;

public interface IMarcaDao {

    public Marca cadastrar(Marca marca);

    public void excluir(Marca marca);

    public List<Marca> buscarTodos();
}
