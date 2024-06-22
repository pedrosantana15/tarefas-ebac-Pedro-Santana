package br.com.spedro.PrimeiroExemploSpringBoot;

import br.com.spedro.domain.Cliente;
import br.com.spedro.domain.Produto;
import br.com.spedro.domain.ProdutoQuantidade;
import br.com.spedro.domain.Venda;
import br.com.spedro.repository.IClienteRepository;
import br.com.spedro.repository.IProdutoRepository;
import br.com.spedro.repository.IVendaRepository;
import lombok.extern.apachecommons.CommonsLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@SpringBootApplication
@Configuration
@EnableJpaRepositories(basePackages = "br.com.spedro")
@EntityScan("br.com.spedro.*")
@ComponentScan(basePackages = "br.com.spedro")
public class PrimeiroExemploSpringBootApplication implements CommandLineRunner {

	@Autowired
	private IClienteRepository clienteRepository;

	@Autowired
	private IProdutoRepository produtoRepository;

	@Autowired
	private IVendaRepository vendaRepository;

	private static final Logger log = LoggerFactory.getLogger(PrimeiroExemploSpringBootApplication.class);

	@Override
	public void run(String... args) throws Exception {
		log.info("Iniciando aplicação...");

		Cliente cliente = createCliente();

		Produto produto = createProduto();

		Venda venda = createVenda();


		log.info("Cadastrando produto...");
		produtoRepository.save(produto);

		log.info("Cadastrando cliente...");
		clienteRepository.save(cliente);

		venda.setCliente(cliente);
		venda.adicionarProduto(produto, 2);

		log.info("Cadastrando venda...");
		vendaRepository.save(venda);
	}

	public static void main(String[] args) {
		SpringApplication.run(PrimeiroExemploSpringBootApplication.class, args);
	}

	private Cliente createCliente() {
		return Cliente.builder()
				.nome("Pedro")
				.email("pedro@gmail.com")
				.cpf(4445521558L)
				.cidade("São Paulo")
				.estado("SP")
				.tel(444455L)
				.end("Rua das flores")
				.numero(3)
				.build();
	}

	private Produto createProduto() {
		return Produto.builder()
				.codigo("P1")
				.nome("Monitor")
				.descricao("Monitor Gamer 144hz")
				.preco(BigDecimal.valueOf(800))
				.build();
	}

	private Venda createVenda() {
		Venda venda = new Venda();
		venda.setCodigo("V1");
		venda.setStatus(Venda.Status.INICIADA);
		venda.setDataVenda(Instant.now());
		venda.setValorTotal(venda.getValorTotal());
		return venda;
	}

}
