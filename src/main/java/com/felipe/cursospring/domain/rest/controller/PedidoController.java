package com.felipe.cursospring.domain.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.cursospring.domain.entity.Pedido;
import com.felipe.cursospring.domain.rest.controller.dto.PedidoDTO;
import com.felipe.cursospring.domain.service.IPedidoService;

@RestController
@RequestMapping("/api/pedidos")

public class PedidoController {
    
    private IPedidoService service;

    //injetando via construtor (sem @Autowired):
    public PedidoController(IPedidoService service){
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO dto){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }


}
