package br.com.spedro.vendas.online.resources;

import br.com.spedro.vendas.online.domain.Produto;
import br.com.spedro.vendas.online.usecase.BuscaProduto;
import br.com.spedro.vendas.online.usecase.CadastroProduto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoResources {

    private CadastroProduto cadastroProduto;
    private BuscaProduto buscaProduto;

    @Autowired
    public ProdutoResources(CadastroProduto cadastroProduto, BuscaProduto buscaProduto) {
        this.cadastroProduto = cadastroProduto;
        this.buscaProduto = buscaProduto;
    }

    @GetMapping
    @Operation(summary = "Busca todos os produtos cadastrados")
    public ResponseEntity<Page<Produto>> buscarTodos(Pageable pageable) {
        return ResponseEntity.ok(buscaProduto.buscarTodos(pageable));
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca um produto pelo seu identificador único")
    public ResponseEntity<Produto> buscarPorId(String id) {
        return ResponseEntity.ok(buscaProduto.buscarPorId(id));
    }

    @GetMapping(value = "/status/{status}")
    @Operation(summary = "Busca produtos pelo status ativo ou inativo")
    public ResponseEntity<Page<Produto>> buscarPorStatus(Produto.Status status, Pageable pageable) {
        return ResponseEntity.ok(buscaProduto.buscarPorStatus(status, pageable));
    }

    @GetMapping(value = "/nome/{nome}")
    @Operation(summary = "Busca produtos pelo nome")
    public ResponseEntity<Page<Produto>> buscarPorNome(String nome, Pageable pageable) {
        return ResponseEntity.ok(buscaProduto.buscarPorNome(nome, pageable));
    }

    @PostMapping
    @Operation(summary = "Cadastra o produto no banco de dados")
    public ResponseEntity<Produto> cadastrar(@RequestBody @Valid Produto produto) {
        return ResponseEntity.ok(cadastroProduto.cadastrar(produto));
    }

    @PutMapping
    @Operation(summary = "Atualiza as informações de um produto")
    public ResponseEntity<Produto> atualizar(@RequestBody @Valid Produto produto) {
        return ResponseEntity.ok(cadastroProduto.atualizar(produto));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Inativa um produto")
    public ResponseEntity<String> remover(@PathVariable(value = "id") String id) {
        cadastroProduto.remover(id);
        return ResponseEntity.ok("Removido com sucesso!");
    }

}
