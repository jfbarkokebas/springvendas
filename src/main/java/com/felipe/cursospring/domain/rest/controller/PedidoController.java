package com.felipe.cursospring.domain.rest.controller;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.felipe.cursospring.domain.entity.ItemPedido;
import com.felipe.cursospring.domain.entity.Pedido;
import com.felipe.cursospring.domain.rest.dto.InformacaoItemPedidoDTO;
import com.felipe.cursospring.domain.rest.dto.InformacoesPedidoDTO;
import com.felipe.cursospring.domain.rest.dto.PedidoDTO;
import com.felipe.cursospring.domain.service.IPedidoService;

import static org.springframework.http.HttpStatus.*;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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


    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id){
        return service
            .obterPedidoCompleto(id)
            .map(
                p -> converter(p)
            )
            .orElseThrow(
                ()-> new ResponseStatusException(NOT_FOUND, "Pedido não encontrado."));
    }

    private InformacoesPedidoDTO converter(Pedido pedido){
       return InformacoesPedidoDTO.builder()
            .codigo(pedido.getId())
            .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
            .cpf(pedido.getCliente().getCpf())
            .nomeCliente(pedido.getCliente().getNome())
            .total(pedido.getTotal())
            .items(converter(pedido.getItens()))
            .build();

            
    }

    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens){

        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }

        return itens.stream().map(
            item -> InformacaoItemPedidoDTO.builder()
                .descricaoProduto(item.getProduto().getDescricao())   
                .precoUnitario(item.getProduto().getPreco())
                .quantidade(item.getQuantidade())
                .build()
        ).collect(Collectors.toList());
    }
}
