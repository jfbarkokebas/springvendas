package com.felipe.cursospring.domain.service;

import com.felipe.cursospring.domain.entity.Pedido;
import com.felipe.cursospring.domain.rest.controller.dto.PedidoDTO;

public interface IPedidoService {
    Pedido salvar (PedidoDTO dto);
}