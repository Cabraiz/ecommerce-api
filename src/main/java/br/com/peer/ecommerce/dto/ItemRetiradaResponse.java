package br.com.peer.ecommerce.dto;

import lombok.Data;

@Data
public class ItemRetiradaResponse {
    private Long produtoId;
    private String tamanho;
    private String corProduto;
    private int quantidade;
    private String status;
}
