package br.com.spedro.vendas.online;

import br.com.spedro.vendas.online.domain.Produto;
import br.com.spedro.vendas.online.domain.Venda;
import br.com.spedro.vendas.online.dto.VendaDTO;
import br.com.spedro.vendas.online.resources.VendaResources;
import br.com.spedro.vendas.online.usecase.BuscaVenda;
import br.com.spedro.vendas.online.usecase.CadastroVenda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class VendaResourceTest {

    @InjectMocks
    private VendaResources vendaResources;

    @MockBean
    private CadastroVenda cadastroVenda;

    @MockBean
    private BuscaVenda buscaVenda;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCadastrar(){
        VendaDTO venda = VendaDTO.builder()
                .codigo("V1")
                .clienteId("1")
                .dataVenda(Instant.now())
                .build();

        Venda vendaConvert = convertToDomain(venda);

        when(cadastroVenda.cadastrar(venda)).thenReturn(vendaConvert);
        ResponseEntity<Venda> result = vendaResources.cadastrar(venda);
        assertThat(result.getStatusCode().is2xxSuccessful()).isTrue();
        verify(cadastroVenda).cadastrar(venda);

        vendaConvert = result.getBody();
        assertThat(vendaConvert).isNotNull();
        assertThat(vendaConvert.getId()).isEqualTo("1");
        assertThat(vendaConvert.getClienteId()).isEqualTo("1");
        assertThat(vendaConvert.getDataVenda()).isEqualTo(venda.getDataVenda());
    }

    @Test
    public void testAtualizar(){
        VendaDTO venda = VendaDTO.builder()
                .codigo("V1")
                .clienteId("1")
                .dataVenda(Instant.now())
                .build();

        Venda vendaConvert = convertToDomain(venda);

        when(cadastroVenda.cadastrar(venda)).thenReturn(vendaConvert);
        ResponseEntity<Venda> result = vendaResources.cadastrar(venda);
        assertThat(result.getStatusCode().is2xxSuccessful()).isTrue();
        verify(cadastroVenda).cadastrar(venda);
        assertThat(result.getBody().getCodigo()).isEqualTo("V1");

        Venda vendaAlterada = result.getBody();
        assertThat(vendaAlterada).isNotNull();
        vendaAlterada.setCodigo("V2");

        when(cadastroVenda.atualizar(vendaAlterada)).thenReturn(vendaAlterada);
        ResponseEntity<Venda> res = vendaResources.atualizarVenda(vendaAlterada);
        assertThat(res.getStatusCode().is2xxSuccessful()).isTrue();
        verify(cadastroVenda).atualizar(vendaAlterada);
        assertThat(res.getBody().getCodigo()).isEqualTo("V2");
    }

    @Test
    public void testFinalizar(){
        Venda venda = Venda.builder()
                .id("1")
                .codigo("V1")
                .clienteId("1")
                .status(Venda.Status.INICIADA)
                .dataVenda(Instant.now())
                .build();

        when(cadastroVenda.finalizar(venda.getId())).thenReturn(venda);
        ResponseEntity<String> result = vendaResources.finalizarVenda(venda.getId());
        verify(cadastroVenda).finalizar(venda.getId());
        assertThat(result.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(result.getBody()).isEqualTo("Venda finalizada com sucesso!");

        Page<Venda> vendaPage = new PageImpl<>(Collections.singletonList(venda), PageRequest.of(0, 10), 1);
        when(buscaVenda.buscarPorStatus(Venda.Status.CONCLUIDA, Pageable.unpaged())).thenReturn(vendaPage);
        ResponseEntity<Page<Venda>> vendaConsultada = vendaResources.buscarVendaPorStatus(Venda.Status.CONCLUIDA, Pageable.unpaged());
        assertThat(vendaConsultada).isNotNull();
        assertThat(vendaConsultada.getBody().getContent().getFirst().getCodigo()).isEqualTo(venda.getCodigo());
    }

    @Test
    public void testCancelarVenda(){
        Venda venda = Venda.builder()
                .id("1")
                .codigo("V1")
                .clienteId("1")
                .status(Venda.Status.INICIADA)
                .dataVenda(Instant.now())
                .build();

        when(cadastroVenda.cancelar(venda.getId())).thenReturn(venda);
        ResponseEntity<String> result = vendaResources.cancelarVenda(venda.getId());
        verify(cadastroVenda).cancelar(venda.getId());
        assertThat(result.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(result.getBody()).isEqualTo("Venda cancelada com sucesso!");

        Page<Venda> vendaPage = new PageImpl<>(Collections.singletonList(venda), PageRequest.of(0, 10), 1);
        when(buscaVenda.buscarPorStatus(Venda.Status.CANCELADA, Pageable.unpaged())).thenReturn(vendaPage);
        ResponseEntity<Page<Venda>> vendaConsultada = vendaResources.buscarVendaPorStatus(Venda.Status.CANCELADA, Pageable.unpaged());
        assertThat(vendaConsultada).isNotNull();
        assertThat(vendaConsultada.getBody().getContent().getFirst().getCodigo()).isEqualTo(venda.getCodigo());
    }

    @Test
    public void testAdicionarProduto(){
        Produto produto = buildProduto();

        Venda venda = Venda.builder()
                .id("1")
                .codigo("V1")
                .clienteId("1")
                .status(Venda.Status.INICIADA)
                .dataVenda(Instant.now())
                .produtos(new HashSet<>())
                .build();

        venda.adicionarProduto(produto, 2);

        when(cadastroVenda.adicionarProduto(venda.getId(), produto.getCodigo(), 2)).thenReturn(venda);
        ResponseEntity<Venda> result = vendaResources.adicionarProduto(venda.getId(), produto.getCodigo(), 2);
        assertThat(result.getStatusCode().is2xxSuccessful()).isTrue();
        verify(cadastroVenda).adicionarProduto(venda.getId(), produto.getCodigo(), 2);

        Venda vendaComProduto = result.getBody();
        assertThat(vendaComProduto).isNotNull();
        assertThat(vendaComProduto.getProdutos().size()).isEqualTo(1);
        assertThat(vendaComProduto.getQuantidadeTotalProdutos()).isEqualTo(2);
    }

    @Test
    public void testRemoverProduto(){
        Produto produto = buildProduto();

        Venda venda = Venda.builder()
                .id("1")
                .codigo("V1")
                .clienteId("1")
                .status(Venda.Status.INICIADA)
                .dataVenda(Instant.now())
                .produtos(new HashSet<>())
                .build();

        venda.adicionarProduto(produto, 2);

        when(cadastroVenda.removerProduto(venda.getId(), produto.getCodigo(), 1)).thenReturn(venda);
        ResponseEntity<Venda> res = vendaResources.removerProduto(venda.getId(), produto.getCodigo(), 1);
        assertThat(res.getStatusCode().is2xxSuccessful()).isTrue();
        verify(cadastroVenda).removerProduto(venda.getId(), produto.getCodigo(), 1);

        venda.removerProduto(produto, 1);

        Venda vendaComProduto = res.getBody();
        assertThat(vendaComProduto).isNotNull();
        assertThat(vendaComProduto.getProdutos().size()).isEqualTo(1);
        assertThat(vendaComProduto.getQuantidadeTotalProdutos()).isEqualTo(1);
    }

    @Test
    public void testBuscarVendaPorStatus(){
        Venda venda = Venda.builder()
                .id("1")
                .codigo("V1")
                .clienteId("1")
                .status(Venda.Status.INICIADA)
                .dataVenda(Instant.now())
                .build();

        Page<Venda> vendaPage = new PageImpl<>(Collections.singletonList(venda), PageRequest.of(0, 10), 1);
        when(buscaVenda.buscarPorStatus(Venda.Status.INICIADA, Pageable.unpaged())).thenReturn(vendaPage);
        ResponseEntity<Page<Venda>> result = vendaResources.buscarVendaPorStatus(venda.getStatus(), Pageable.unpaged());
        assertThat(result.getStatusCode().is2xxSuccessful()).isTrue();
        verify(buscaVenda).buscarPorStatus(venda.getStatus(), Pageable.unpaged());

        assertThat(result).isNotNull();
        assertThat(result.getBody().getContent().getFirst().getCodigo()).isEqualTo("V1");
        assertThat(result.getBody().getContent().getFirst().getStatus()).isEqualTo(Venda.Status.INICIADA);
    }

    private Venda convertToDomain(VendaDTO venda){
        return Venda.builder()
                .id("1")
                .codigo(venda.getCodigo())
                .clienteId(venda.getClienteId())
                .dataVenda(venda.getDataVenda())
                .build();
    }

    private Produto buildProduto(){
        return Produto.builder()
                .id("1")
                .codigo("P1")
                .nome("Monitor")
                .valor(BigDecimal.valueOf(400))
                .descricao("Monitor Gamer")
                .build();
    }

}
