package com.felipe.cursospring.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipe.cursospring.domain.entity.Produto;

public interface Produtos extends JpaRepository<Produto,Integer> {
}

