package br.com.teixeira.produto.service;

import br.com.teixeira.produto.br.com.repository.ProdutoRepository;
import br.com.teixeira.produto.model.Produto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto save(Produto produto){
        return produtoRepository.save(produto);
    }

    public Produto findOne(String id){
        return produtoRepository.findOne(id);
    }

    public List<Produto> getAll(){
        return produtoRepository.findAll();
    }

    public List<Produto> getByCategoria(String id){
        return produtoRepository.findByCategoriaId(id);
    }


}
