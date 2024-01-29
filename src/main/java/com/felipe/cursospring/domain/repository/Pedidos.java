package com.felipe.cursospring.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipe.cursospring.domain.entity.Cliente;
import com.felipe.cursospring.domain.entity.Pedido;

public interface Pedidos extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);
}

