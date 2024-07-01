package br.com.spedro.vendas.online.ClienteService.resources;

import br.com.spedro.vendas.online.ClienteService.domain.Cliente;
import br.com.spedro.vendas.online.ClienteService.usecase.BuscaCliente;
import br.com.spedro.vendas.online.ClienteService.usecase.CadastroCliente;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Retorna uma lista paginada de clientes")
    public ResponseEntity<Page<Cliente>> buscar(Pageable pageable) {
        return ResponseEntity.ok(buscaCliente.buscar(pageable));
    }

    @PostMapping
    @Operation(summary = "Cadastra um cliente")
    public ResponseEntity<Cliente> cadastrar(@RequestBody @Valid Cliente cliente) {
        return ResponseEntity.ok(cadastroCliente.cadastrar(cliente));
    }

    @PutMapping
    @Operation(summary = "Atualiza um cliente")
    public ResponseEntity<Cliente> atualizar(@RequestBody @Valid Cliente cliente){
        return ResponseEntity.ok(cadastroCliente.atualizar(cliente));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Remove um cliente do banco de dados")
    public ResponseEntity<String> excluir(@PathVariable(value = "id") String id){
        cadastroCliente.remover(id);
        return ResponseEntity.ok("Cliente excluído com sucesso!");
    }

    @GetMapping(value = "/isCadastrado/{id}")
    @Operation(summary = "Verifica se o cliente está cadastrado")
    public ResponseEntity<Boolean> isCadastrado(@PathVariable(value = "id", required = true) String id) {
        return ResponseEntity.ok(buscaCliente.isCadastrado(id));
    }

    @GetMapping(value = "/{cpf}")
    @Operation(summary = "Busca cliente pelo CPF")
    public ResponseEntity<Cliente> buscarPorCpf(@PathVariable(value = "cpf") Long cpf){
        return ResponseEntity.ok(buscaCliente.buscarPorCpf(cpf));
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca cliente pelo Id")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable(value = "id") String id){
        return ResponseEntity.ok(buscaCliente.buscarPorId(id));
    }

}
