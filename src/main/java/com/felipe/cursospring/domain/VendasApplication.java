package com.felipe.cursospring.domain;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.felipe.cursospring.domain.entity.Cliente;
import com.felipe.cursospring.domain.entity.ItemPedido;
import com.felipe.cursospring.domain.entity.Pedido;
import com.felipe.cursospring.domain.entity.Produto;
import com.felipe.cursospring.domain.repository.Clientes;
import com.felipe.cursospring.domain.repository.ItemsPedido;
import com.felipe.cursospring.domain.repository.Pedidos;
import com.felipe.cursospring.domain.repository.Produtos;

@SpringBootApplication
public class VendasApplication {

    
    @Bean
    public CommandLineRunner onInit(@Autowired Pedidos persistPedido,
    @Autowired ItemsPedido persisItemsPedido, @Autowired Produtos persiProdutos, @Autowired Clientes persisClientes){
        return args->{


            Cliente cliente = new Cliente();
            cliente.setCpf("03003003011");
            cliente.setNome("VendaApplication Class");
            persisClientes.save(cliente);

            Produto produto = new Produto();
            produto.setDescricao("caderno");
            BigDecimal preco = new BigDecimal(20);
            produto.setPreco(preco);
            persiProdutos.save(produto);

            ItemPedido item = new ItemPedido();
            item.setProduto(produto);
            item.setQuantidade(2);
            persisItemsPedido.save(item);

            ArrayList<ItemPedido> lista = new ArrayList();
            lista.add(item);

            BigDecimal total = new BigDecimal(100);

            Pedido pedido = new Pedido();
            pedido.setCliente(cliente);
            pedido.setTotal(total);
            pedido.setItens(lista);

            persistPedido.save(pedido);
            System.out.println("Pedido salvo");

        };
    }
 
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
        System.out.println("*********");
        System.out.println(" BUILDED");
        System.out.println("*********");
    }
}
