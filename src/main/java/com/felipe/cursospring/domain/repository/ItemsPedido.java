package com.felipe.cursospring.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipe.cursospring.domain.entity.ItemPedido;

public interface ItemsPedido extends JpaRepository<ItemPedido, Integer> {
}

