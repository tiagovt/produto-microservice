package br.com.teixeira.produto.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.util.List;

@Data
@Document(collection = "produto")
public class Produto {

    @Id
    private String id;

    private String nome;

    private String marca;

    private String descricao;

    private Boolean ativo;

    @NumberFormat(pattern = "#,###,###,###.##")
    private BigDecimal valor;

    private List<Categoria> categoriaList;
}
