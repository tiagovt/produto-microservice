package br.com.teixeira.produto.controller;

import br.com.teixeira.produto.model.Produto;
import br.com.teixeira.produto.service.ProdutoService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RefreshScope
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Value("${teste}")
    private String teste;

    @RequestMapping("teste")
    public String teste(HttpServletRequest request){
        return "Funcionando, IP solicitante" + request.getRemoteHost() + ":"
                + request.getRemotePort() + " IP server : " + request.getLocalAddr();
    }

    @RequestMapping("testeProfile")
    public String testeProfile(HttpServletRequest request){
        return "Metodo teste profile Funcionando, IP solicitante" + request.getRemoteHost() + ":"
                + request.getRemotePort() + " IP server : " + request.getLocalAddr()
                + "\n valor variavel teste : " + teste;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Produto> get(@PathVariable String id) throws URISyntaxException {
        Produto produto = produtoService.findOne(id);
        return Optional.ofNullable(produto)
                .map(result -> new ResponseEntity(result, HttpStatus.OK))
                .orElse(new ResponseEntity<Produto>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(path = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Produto> create(@Valid @RequestBody Produto produto) throws URISyntaxException {
        produto = produtoService.save(produto);
        return ResponseEntity.created(new URI(produto.getId())).body(produto);
    }

    @RequestMapping(path = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Produto> update(@Valid @RequestBody Produto produto) throws URISyntaxException {
        produto = produtoService.save(produto);
        return ResponseEntity.ok().body(produto);
    }

    @RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Produto> getAll() throws URISyntaxException {
        List<Produto> produtoList = produtoService.getAll();
        return new ResponseEntity(produtoList, HttpStatus.OK);
    }

    @HystrixCommand(fallbackMethod = "getByCategoriaFallBack")
    @RequestMapping(path = "/categoria/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Produto> getByCategoria(@PathVariable String id) throws URISyntaxException {
        List<Produto> produtoList = produtoService.getByCategoria(id);
        return new ResponseEntity(produtoList, HttpStatus.OK);
    }


    private ResponseEntity<Produto> getByCategoriaFallBack(@PathVariable String id) throws URISyntaxException {
        return new ResponseEntity(new ArrayList<Produto>(), HttpStatus.OK);
    }

}
