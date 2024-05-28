package br.com.spedro.services;

import br.com.spedro.domain.Cliente;
import br.com.spedro.exceptions.TipoChaveNaoEncontradaException;

public interface IClienteService {

    Boolean salvar(Cliente cliente) throws TipoChaveNaoEncontradaException;

    Cliente buscarPorCpf(Long cpf);

    void excluir(Long cpf);

    void atualizar(Cliente cliente) throws TipoChaveNaoEncontradaException;
}
