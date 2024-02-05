package com.felipe.cursospring.domain.rest.controller;

import org.springframework.web.bind.annotation.*;

import com.felipe.cursospring.domain.entity.Pedido;
import com.felipe.cursospring.domain.rest.controller.dto.PedidoDTO;
import com.felipe.cursospring.domain.service.IPedidoService;

import static org.springframework.http.HttpStatus.*;

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
