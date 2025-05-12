package br.com.peer.ecommerce.dto;

import lombok.Data;

@Data
public class PagamentoCartaoDTO {
    private String nomeTitular;
    private String numeroCartao;
    private String validade;  // Ex: 12/2027
    private String cvv;
    private double valor;
}
