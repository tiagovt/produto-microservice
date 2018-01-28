package br.com.teixeira.produto.br.com.repository;

import br.com.teixeira.produto.model.Categoria;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoriaRepository extends MongoRepository<Categoria, String>{

}
