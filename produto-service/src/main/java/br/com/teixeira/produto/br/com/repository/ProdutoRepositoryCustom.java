package br.com.teixeira.produto.br.com.repository;

import br.com.teixeira.produto.model.Produto;

import java.util.List;

public interface ProdutoRepositoryCustom {

    public List<Produto> findByCategoriaId(String id);
}
