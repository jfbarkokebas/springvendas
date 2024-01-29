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

import com.felipe.cursospring.domain.entity.Cliente;
import com.felipe.cursospring.domain.repository.Clientes;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    /*
     * @RequestMapping( //parametros possíveis de usar:
     * value = "/hello/{nome}", // --> pode ser um array de paths tbm
     * method = RequestMethod.POST, //--> metodo http
     * consumes= {"application/json", "application/xml"}, // o que pode receber no
     * corpo da requisicao
     * produces = {"application/json", "application/xml"} // o que pode enviar no
     * corpo da resposta
     * )
     * 
     * @ResponseBody
     * public String helloCliente(@PathVariable("nome") String nomeCliente){
     * return String.format("Hello %s " , nomeCliente);
     * }
     */

    @Autowired
    Clientes repository;

    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable Integer id) {
        return repository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // --> em uma api rest a resposta correta é 201(created)
    public Cliente save(@RequestBody Cliente cliente) {
        return repository.save(cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        repository.findById(id)
               .map(cl ->{
                repository.delete(cl);

                return Void.class;
                })
               .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Cliente cliente) {

        repository
                .findById(id) // ---> acha o cliente
                .map(clienteExistente -> { // --> clienteAchado = clienteExistente
                    cliente.setId(clienteExistente.getId()); // --> pega o cliente da request e passa o mesmo id do  clienteExistente
                    repository.save(cliente); // --> salva no BD o cliente passado na requisição com o id do cliente que
                                            // já existia
                    return clienteExistente;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

    }

    //BUSCA USANDO FILTROS:

    @GetMapping
    public List<Cliente> find(Cliente filtro){

        ExampleMatcher matcher = ExampleMatcher.matching()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        return repository.findAll(example);

    }


}