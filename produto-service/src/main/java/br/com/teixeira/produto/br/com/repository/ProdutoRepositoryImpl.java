package br.com.teixeira.produto.br.com.repository;

import br.com.teixeira.produto.br.com.repository.ProdutoRepositoryCustom;
import br.com.teixeira.produto.model.Produto;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class ProdutoRepositoryImpl implements ProdutoRepositoryCustom{

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public List<Produto> findByCategoriaId(String id ) {
        Criteria criteria = Criteria.where("categoriaList.id").is(new ObjectId(id));
        Query query = new Query();
        query.addCriteria(criteria);
        List<Produto> produtoList = mongoTemplate.find(query, Produto.class);
        return produtoList;
    }
}
