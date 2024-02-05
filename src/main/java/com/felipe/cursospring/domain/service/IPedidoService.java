package com.felipe.cursospring.domain.service;

import java.util.Optional;

import com.felipe.cursospring.domain.entity.Pedido;
import com.felipe.cursospring.domain.rest.dto.PedidoDTO;

public interface IPedidoService { 
    
    Pedido salvar (PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);
}
