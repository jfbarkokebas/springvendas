package com.felipe.cursospring.domain.rest.controller;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.cursospring.domain.entity.Pedido;
import com.felipe.cursospring.domain.rest.dto.PedidoDTO;
import com.felipe.cursospring.domain.service.IPedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController { //--->ok

    private IPedidoService service;

    public PedidoController(IPedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save( @RequestBody PedidoDTO dto ){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }

}
