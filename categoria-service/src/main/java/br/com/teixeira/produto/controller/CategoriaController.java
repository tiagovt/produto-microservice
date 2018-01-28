package br.com.teixeira.produto.controller;

import br.com.teixeira.produto.model.Categoria;
import br.com.teixeira.produto.service.CategoriaService;
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
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

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
    public ResponseEntity<Categoria> get(@PathVariable String id) throws URISyntaxException {
        Categoria categoria = categoriaService.findOne(id);
        return Optional.ofNullable(categoria)
                .map(result -> new ResponseEntity(result, HttpStatus.OK))
                .orElse(new ResponseEntity<Categoria>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(path = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Categoria> create(@Valid @RequestBody Categoria categoria) throws URISyntaxException {
        categoria = categoriaService.save(categoria);
        return ResponseEntity.created(new URI(categoria.getId())).body(categoria);
    }

    @RequestMapping(path = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Categoria> update(@Valid @RequestBody Categoria categoria) throws URISyntaxException {
        categoria = categoriaService.save(categoria);
        return ResponseEntity.ok().body(categoria);
    }

    @HystrixCommand(fallbackMethod = "getAllFallBack")
    @RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Categoria> getAll() throws URISyntaxException {
        List<Categoria> categoriaList = categoriaService.getAll();
        return new ResponseEntity(categoriaList, HttpStatus.OK);
    }

    private ResponseEntity<Categoria> getAllFallBack() throws URISyntaxException {
        return new ResponseEntity(new ArrayList<Categoria>(), HttpStatus.OK);
    }

}
