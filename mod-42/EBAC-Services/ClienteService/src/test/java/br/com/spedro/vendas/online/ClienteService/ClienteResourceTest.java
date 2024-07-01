package br.com.spedro.vendas.online.ClienteService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.spedro.vendas.online.ClienteService.resources.ClienteResource;
import br.com.spedro.vendas.online.ClienteService.usecase.BuscaCliente;
import br.com.spedro.vendas.online.ClienteService.usecase.CadastroCliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import br.com.spedro.vendas.online.ClienteService.domain.Cliente;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class ClienteResourceTest {

    @InjectMocks
    private ClienteResource clienteResource;

    @MockBean
    private BuscaCliente buscaCliente;

    @MockBean
    private CadastroCliente cadastroCliente;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCadastrar(){
        Cliente cliente = Cliente.builder().id("1").nome("Pedro 1").build();

        when(cadastroCliente.cadastrar(cliente)).thenReturn(cliente);
        ResponseEntity<Cliente> result = clienteResource.cadastrar(cliente);
        verify(cadastroCliente).cadastrar(cliente);

        Cliente clienteResult = result.getBody();
        assertThat(clienteResult).isEqualTo(cliente);
        assertThat(clienteResult.getId()).isEqualTo(cliente.getId());
        assertThat(clienteResult.getNome()).isEqualTo(cliente.getNome());
    }

    @Test
    public void testAtualizar(){
        Cliente cliente = Cliente.builder().id("1").nome("Pedro 1").build();

        when(cadastroCliente.cadastrar(cliente)).thenReturn(cliente);
        cliente.setNome("Pedro 2");
        when(cadastroCliente.atualizar(cliente)).thenReturn(cliente);

        ResponseEntity<Cliente> result = clienteResource.atualizar(cliente);
        assertThat(result.getStatusCode().is2xxSuccessful()).isTrue();
        Cliente clienteResult = result.getBody();
        assertThat(clienteResult).isEqualTo(cliente);
        assertThat(clienteResult.getNome()).isEqualTo("Pedro 2");
    }

    @Test
    public void testExcluir(){
        Cliente cliente = Cliente.builder().id("1").nome("Pedro 1").build();

        ResponseEntity<String> result = clienteResource.excluir(cliente.getId());
        verify(cadastroCliente).remover(cliente.getId());

        assertThat(result.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(result.getBody()).isEqualTo("Cliente excluído com sucesso!");
    }

    @Test
    public void testBuscarPorId() {
        Cliente cliente = Cliente.builder().id("1").nome("Pedro 1").build();

        when(buscaCliente.buscarPorId("1")).thenReturn(cliente);

        ResponseEntity<Cliente> result = clienteResource.buscarPorId("1");

        Cliente clienteResult = result.getBody();
        assertThat(clienteResult).isEqualTo(cliente);
        assertThat(clienteResult.getId()).isEqualTo(cliente.getId());
        assertThat(clienteResult.getNome()).isEqualTo(cliente.getNome());
    }

    @Test
    public void testBuscarPorCpf() {
        Cliente cliente = Cliente.builder().id("1").nome("Pedro 1").cpf(11122255565L).build();

        when(buscaCliente.buscarPorCpf(11122255565L)).thenReturn(cliente);
        ResponseEntity<Cliente> result = clienteResource.buscarPorCpf(11122255565L);
        verify(buscaCliente).buscarPorCpf(cliente.getCpf());

        Cliente clienteResult = result.getBody();
        assertThat(clienteResult).isEqualTo(cliente);
        assertThat(clienteResult.getCpf()).isEqualTo(cliente.getCpf());
        assertThat(clienteResult.getNome()).isEqualTo(cliente.getNome());
    }

}
