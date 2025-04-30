package br.com.peer.ecommerce.dto;

import lombok.Data;

@Data
public class ReduzirEstoqueDTO {
    private Long variacaoId;
    private String tamanho;
    private int quantidade;
}
