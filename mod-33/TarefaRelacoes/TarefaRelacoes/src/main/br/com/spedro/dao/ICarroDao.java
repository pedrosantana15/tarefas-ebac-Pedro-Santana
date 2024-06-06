package main.br.com.spedro.dao;

import main.br.com.spedro.domain.Carro;
import main.br.com.spedro.domain.Marca;

import java.util.List;

public interface ICarroDao {

    public Carro cadastrar(Carro carro);

    public Carro buscarPorId(Long id);

    public List<Carro> buscarTodos();

    public void excluir(Carro carro);

    public Carro alterar(Carro carro);

    public List<Carro> buscarPorMarca(Marca marca);

}
