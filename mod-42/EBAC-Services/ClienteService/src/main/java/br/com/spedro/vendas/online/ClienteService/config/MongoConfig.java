package br.com.spedro.vendas.online.ClienteService.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "br.com.spedro.vendas.online.ClienteService.repository")
public class MongoConfig {

}
