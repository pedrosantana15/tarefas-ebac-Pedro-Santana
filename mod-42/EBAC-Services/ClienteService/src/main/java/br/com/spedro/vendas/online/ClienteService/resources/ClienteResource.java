package br.com.spedro.vendas.online.ClienteService.resources;

import br.com.spedro.vendas.online.ClienteService.domain.Cliente;
import br.com.spedro.vendas.online.ClienteService.usecase.BuscaCliente;
import br.com.spedro.vendas.online.ClienteService.usecase.CadastroCliente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteResource {

    private BuscaCliente buscaCliente;
    private CadastroCliente cadastroCliente;

    @Autowired
    public ClienteResource(BuscaCliente buscaCliente, CadastroCliente cadastroCliente) {
        this.buscaCliente = buscaCliente;
        this.cadastroCliente = cadastroCliente;
    }

    @GetMapping
    public ResponseEntity<Page<Cliente>> buscar(Pageable pageable) {
        return ResponseEntity.ok(buscaCliente.buscar(pageable));
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@RequestBody @Valid Cliente cliente) {
        return ResponseEntity.ok(cadastroCliente.cadastrar(cliente));
    }

    @GetMapping(value = "/isCadastrado/{id}")
    public ResponseEntity<Boolean> isCadastrado(@PathVariable(value = "id", required = true) String id) {
        return ResponseEntity.ok(buscaCliente.isCadastrado(id));
    }

}
