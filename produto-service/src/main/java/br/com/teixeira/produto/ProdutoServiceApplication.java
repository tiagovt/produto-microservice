package br.com.teixeira.produto;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@SpringBootApplication
@RestController
public class ProdutoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdutoServiceApplication.class, args);
	}
	
	@RequestMapping("teste")
	public String teste(HttpServletRequest request){
		return "Funcionando, IP solicitante" + request.getRemoteHost() + ":" 
				+ request.getRemotePort() + " IP server : " + request.getLocalAddr();
	}
}
