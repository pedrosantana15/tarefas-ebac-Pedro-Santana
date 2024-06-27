package br.com.spedro.vendas.online.usecase;

import br.com.spedro.vendas.online.domain.Venda;
import br.com.spedro.vendas.online.repository.IVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BuscaVenda {

    IVendaRepository vendaRepository;

    @Autowired
    public BuscaVenda(IVendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    public Page<Venda> buscarTodasVendas(Pageable pageable) {
        return vendaRepository.findAll(pageable);
    }

    public Page<Venda> buscarPorStatus(Venda.Status status, Pageable pageable) {
        return vendaRepository.findByStatus(status, pageable);
    }

}
