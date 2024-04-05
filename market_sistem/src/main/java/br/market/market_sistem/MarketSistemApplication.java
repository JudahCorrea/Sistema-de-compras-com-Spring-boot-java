package br.market.market_sistem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class MarketSistemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketSistemApplication.class, args);
	}

}

/**
 * Antes de rodar o sistema é necessário que :
 * 1 - abra o terminal
 * 2 - vá até o diretório onde está este projeto
 * 3 - rode o comando : mvn clean package -DskipTests
 * Este comando cria o : market_sistem-0.0.1-SNAPSHOT.jar
 * que será referenciado pelos arquivos Dockerfile e Docker-compose.yml .
 * Após isto o sistema estará pronto para rodar sem problemas.
 */
