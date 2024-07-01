package br.com.spedro.vendas.online;

import br.com.spedro.vendas.online.config.OpenAPIConfig;
import br.com.spedro.vendas.online.domain.Produto;
import br.com.spedro.vendas.online.resources.ProdutoResources;
import br.com.spedro.vendas.online.usecase.BuscaProduto;
import br.com.spedro.vendas.online.usecase.CadastroProduto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProdutoResourceTest {

    @InjectMocks
    private ProdutoResources produtoResources;

    @MockBean
    private CadastroProduto cadastroProduto;

    @MockBean
    private BuscaProduto buscaProduto;
    @Autowired
    private PageableHandlerMethodArgumentResolverCustomizer pageableCustomizer;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCadastrar(){
        Produto produto = Produto.builder()
                .id("1")
                .nome("Monitor")
                .status(Produto.Status.ATIVO)
                .build();

        when(cadastroProduto.cadastrar(produto)).thenReturn(produto);
        ResponseEntity<Produto> result = produtoResources.cadastrar(produto);
        assertThat(result.getStatusCode().is2xxSuccessful()).isTrue();
        verify(cadastroProduto).cadastrar(produto);

        Produto produtoCadastrado = result.getBody();
        assertThat(produtoCadastrado).isNotNull();
        assertThat(produtoCadastrado.getId()).isEqualTo("1");
        assertThat(produtoCadastrado.getNome()).isEqualTo("Monitor");
        assertThat(produtoCadastrado.getStatus()).isEqualTo(Produto.Status.ATIVO);
    }

    @Test
    public void testBuscarPorCodigo(){
        Produto produto = Produto.builder()
                .id("1")
                .codigo("P1")
                .nome("Monitor")
                .status(Produto.Status.ATIVO)
                .build();

        when(buscaProduto.buscarPorCodigo(produto.getCodigo())).thenReturn(produto);
        ResponseEntity<Produto> result = produtoResources.buscarPorCodigo(produto.getCodigo());
        assertThat(result.getStatusCode().is2xxSuccessful()).isTrue();
        verify(buscaProduto).buscarPorCodigo(produto.getCodigo());

        Produto produtoConsultado = result.getBody();
        assertThat(produtoConsultado).isNotNull();
        assertThat(produtoConsultado.getId()).isEqualTo("1");
        assertThat(produtoConsultado.getNome()).isEqualTo("Monitor");
        assertThat(produtoConsultado.getCodigo()).isEqualTo("P1");
    }

    @Test
    public void testAtualizar(){
        Produto produto = Produto.builder()
                .id("1")
                .codigo("P1")
                .nome("Monitor")
                .status(Produto.Status.ATIVO)
                .build();

        when(cadastroProduto.cadastrar(produto)).thenReturn(produto);
        ResponseEntity<Produto> result = produtoResources.cadastrar(produto);
        assertThat(result.getStatusCode().is2xxSuccessful()).isTrue();
        Produto produtoAlterado = result.getBody();
        assertThat(produtoAlterado).isNotNull();
        assertThat(produtoAlterado.getCodigo()).isEqualTo("P1");

        produtoAlterado.setCodigo("P2");
        when(cadastroProduto.atualizar(produto)).thenReturn(produto);
        ResponseEntity<Produto> resultAlterado = produtoResources.atualizar(produto);
        assertThat(resultAlterado.getStatusCode().is2xxSuccessful()).isTrue();
        verify(cadastroProduto).atualizar(produto);

        Produto produtoAtualizado = resultAlterado.getBody();
        assertThat(produtoAtualizado).isNotNull();
        assertThat(produtoAtualizado.getCodigo()).isEqualTo("P2");
    }

    @Test
    public void testBuscarPorNome(){
        Produto produto = Produto.builder()
                .id("1")
                .codigo("P1")
                .nome("Monitor")
                .status(Produto.Status.ATIVO)
                .build();

        Page<Produto> pageProduto = new PageImpl<>(Collections.singletonList(produto), PageRequest.of(0, 10), 1);
        when(buscaProduto.buscarPeloNome("Mon", Pageable.unpaged())).thenReturn(pageProduto);

        ResponseEntity<Page<Produto>> result = produtoResources.buscarProdutoPeloNome("Mon", Pageable.unpaged());
        Page<Produto> produtoConsultado = result.getBody();
        verify(buscaProduto).buscarPeloNome("Mon", Pageable.unpaged());

        assertThat(produtoConsultado).isNotNull();
        assertThat(produtoConsultado.getTotalElements()).isEqualTo(1);
        assertThat(produtoConsultado.getTotalPages()).isEqualTo(1);
        assertThat(produtoConsultado.getContent().getFirst().getNome()).isEqualTo("Monitor");
    }

    @Test
    public void testRemover(){
        Produto produto = Produto.builder()
                .id("1")
                .codigo("P1")
                .nome("Monitor")
                .status(Produto.Status.ATIVO)
                .build();

        ResponseEntity<String> result = produtoResources.remover(produto.getId());
        verify(cadastroProduto).remover(produto.getId());

        assertThat(result.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(result.getBody()).isEqualTo("Removido com sucesso");
    }



}
