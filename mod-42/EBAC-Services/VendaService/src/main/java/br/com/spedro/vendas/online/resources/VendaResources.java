package br.com.spedro.vendas.online.resources;

import br.com.spedro.vendas.online.domain.Venda;
import br.com.spedro.vendas.online.dto.VendaDTO;
import br.com.spedro.vendas.online.usecase.BuscaVenda;
import br.com.spedro.vendas.online.usecase.CadastroVenda;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/venda")
public class VendaResources {

    private CadastroVenda cadastroVenda;
    private BuscaVenda buscaVenda;

    @Autowired
    public VendaResources(CadastroVenda cadastroVenda, BuscaVenda buscaVenda){
        this.cadastroVenda = cadastroVenda;
        this.buscaVenda = buscaVenda;
    }

    @GetMapping
    public ResponseEntity<Page<Venda>> buscarVenda(Pageable pageable) {
        return ResponseEntity.ok(buscaVenda.buscarTodasVendas(pageable));
    }

    @PostMapping
    public ResponseEntity<Venda> cadastrar(@RequestBody @Valid VendaDTO vendaDTO){
        return ResponseEntity.ok(cadastroVenda.cadastrar(vendaDTO));
    }

    @PutMapping("/{id}/{codigoProduto}/{quantidade}/addProduto")
    public ResponseEntity<Venda> adicionarProduto(
            @PathVariable(name = "id", required = true) String id,
            @PathVariable(name = "codigoProduto", required = true) String codigoProduto,
            @PathVariable(name = "quantidade", required = true) Integer quantidade) {
        return ResponseEntity.ok(cadastroVenda.adicionarProduto(id, codigoProduto, quantidade));
    }

}
