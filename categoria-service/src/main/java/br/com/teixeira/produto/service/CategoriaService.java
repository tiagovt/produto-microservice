package br.com.teixeira.produto.service;

import br.com.teixeira.produto.br.com.repository.CategoriaRepository;
import br.com.teixeira.produto.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria save(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public Categoria findOne(String id){
        return categoriaRepository.findOne(id);
    }

    public List<Categoria> getAll(){
        return categoriaRepository.findAll();
    }

}
