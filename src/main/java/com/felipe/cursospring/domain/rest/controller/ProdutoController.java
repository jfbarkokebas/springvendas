package com.felipe.cursospring.domain.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.felipe.cursospring.domain.entity.Produto;
import com.felipe.cursospring.domain.repository.Produtos;

import static org.springframework.http.HttpStatus. *;


@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    Produtos repository;

    @PostMapping
    @ResponseStatus(CREATED)
    public Produto save(@RequestBody Produto produto){
        return repository.save(produto);
    }

    @GetMapping("/{id}")
    public Produto getProdutoById(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow(
            ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado")
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        repository.findById(id).map(
                produto -> {
                    repository.delete(produto);
                    return Void.class;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }
    

    @PutMapping("/{id}")
    @ResponseStatus(NOT_FOUND)
    public void update(@PathVariable Integer id, @RequestBody Produto produto){
        repository.findById(id)
                  .map(produtoExistente -> {
                    produto.setId(produtoExistente.getId());
                    repository.save(produtoExistente);

                    return Void.class;
                  })
                  .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    @GetMapping
    public List<Produto> find(Produto filtro){

        ExampleMatcher matcher = ExampleMatcher.matching()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        return repository.findAll(example);

    }
    
}
