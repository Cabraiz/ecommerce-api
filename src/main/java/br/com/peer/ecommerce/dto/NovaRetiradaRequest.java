package br.com.peer.ecommerce.dto;

import lombok.Data;

import java.util.List;

@Data
public class NovaRetiradaRequest {
    private Long usuarioId;
    private String observacao;
    private List<ItemRetiradaDTO> itens;
}
