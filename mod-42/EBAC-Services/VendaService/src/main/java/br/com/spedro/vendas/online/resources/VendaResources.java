package br.com.spedro.vendas.online.resources;

import br.com.spedro.vendas.online.domain.Venda;
import br.com.spedro.vendas.online.dto.VendaDTO;
import br.com.spedro.vendas.online.usecase.BuscaVenda;
import br.com.spedro.vendas.online.usecase.CadastroVenda;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/venda")
public class VendaResources {

    private BuscaVenda buscaVenda;

    private CadastroVenda cadastroVenda;

    @Autowired
    public VendaResources(BuscaVenda buscaVenda,
                           CadastroVenda cadastroVenda) {
        this.buscaVenda = buscaVenda;
        this.cadastroVenda = cadastroVenda;
    }

    @GetMapping
    @Operation(summary = "Lista as vendas cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a lista de clientes"),
            @ApiResponse(responseCode = "400", description = "Requisição malformada ou erro de sintaxe",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "BAD_REQUEST"))),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "INTERNAL_SERVER_ERROR"))),
    })
    public ResponseEntity<Page<Venda>> buscar(Pageable pageable) {
        return ResponseEntity.ok(buscaVenda.buscarTodasVendas(pageable));
    }

    @PostMapping
    @Operation(summary = "Inicia uma venda")
    public ResponseEntity<Venda> cadastrar(@RequestBody @Valid VendaDTO venda) {
        return ResponseEntity.ok(cadastroVenda.cadastrar(venda));
    }

    @PutMapping("/{id}/{codigoProduto}/{quantidade}/addProduto")
    @Operation(summary = "Adiciona a quantidade de produtos desejada a uma venda")
    public ResponseEntity<Venda> adicionarProduto(
            @PathVariable(name = "id", required = true) String id,
            @PathVariable(name = "codigoProduto", required = true) String codigoProduto,
            @PathVariable(name = "quantidade", required = true) Integer quantidade) {
        return ResponseEntity.ok(cadastroVenda.adicionarProduto(id, codigoProduto, quantidade));
    }

    @PutMapping("/{id}/{codigoProduto}/{quantidade}/removeProduto")
    @Operation(summary = "Remove a quantidade de produtos desejada de uma venda")
    public ResponseEntity<Venda> removerProduto(
            @PathVariable(name = "id", required = true) String id,
            @PathVariable(name = "codigoProduto", required = true) String codigoProduto,
            @PathVariable(name = "quantidade", required = true) Integer quantidade
    ){
        return ResponseEntity.ok(cadastroVenda.removerProduto(id, codigoProduto, quantidade));
    }

    @PutMapping(value = "{id}")
    @Operation(summary = "Atualiza o status de uma venda para 'Finalizada'")
    public ResponseEntity<String> finalizarVenda(@PathVariable(value = "id") String id){
        cadastroVenda.finalizar(id);
        return ResponseEntity.ok("Venda finalizada com sucesso!");
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Busca uma lista paginada de vendas de acordo com o status escolhido")
    public ResponseEntity<Page<Venda>> buscarVendaPorStatus(@PathVariable(value = "status") Venda.Status status, Pageable pageable){
        return ResponseEntity.ok(buscaVenda.buscarPorStatus(status, pageable));
    }

    @PutMapping
    @Operation(summary = "Atualiza as informações de uma venda")
    public ResponseEntity<Venda> atualizarVenda(@RequestBody @Valid Venda venda){
        return ResponseEntity.ok(cadastroVenda.atualizar(venda));
    }

    @PutMapping("/id/{id}")
    @Operation(summary = "Atualiza o status de uma venda para 'Cancelada'")
    public ResponseEntity<String> cancelarVenda(@PathVariable(value = "id") String id){
        cadastroVenda.cancelar(id);
        return ResponseEntity.ok("Venda cancelada com sucesso!");
    }
}
