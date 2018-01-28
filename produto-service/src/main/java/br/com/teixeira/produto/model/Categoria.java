package br.com.teixeira.produto.model;

import lombok.Data;

@Data
public class Categoria {

    private String id;

    private String nome;

    private String descricao;

    private Boolean ativo;

}
