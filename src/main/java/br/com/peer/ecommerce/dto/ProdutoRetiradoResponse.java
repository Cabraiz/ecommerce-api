package br.com.peer.ecommerce.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProdutoRetiradoResponse {
    private String nomeProduto;
    private String cor;
    private String tamanho;
    private int quantidade;
    private LocalDateTime dataRetirada;
    private String observacao;
}
