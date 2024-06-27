package br.com.spedro.vendas.online.ClienteService.usecase;

import br.com.spedro.vendas.online.ClienteService.domain.Cliente;
import br.com.spedro.vendas.online.ClienteService.repository.IClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroCliente {

    private IClienteRepository clienteRepository;

    @Autowired
    public CadastroCliente(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente cadastrar(@Valid Cliente cliente) {
        return this.clienteRepository.insert(cliente);
    }

    public Cliente atualizar(@Valid Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }

    public void remover(String id) {
        this.clienteRepository.deleteById(id);
    }

}
