package br.com.teixeira.produto.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "categoria")
public class Categoria {

    @Id
    private String id;

    private String nome;

    private String descricao;

    private Boolean ativo;

}
