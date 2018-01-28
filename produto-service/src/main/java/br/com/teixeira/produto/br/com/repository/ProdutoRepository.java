package br.com.teixeira.produto.br.com.repository;

import br.com.teixeira.produto.model.Categoria;
import br.com.teixeira.produto.model.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProdutoRepository extends MongoRepository<Produto, String>, ProdutoRepositoryCustom {

    public List<Produto> findAllByCategoriaList(List<Categoria> categoriaList);

}
