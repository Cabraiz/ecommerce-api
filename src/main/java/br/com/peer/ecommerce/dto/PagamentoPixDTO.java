package br.com.peer.ecommerce.dto;

import lombok.Data;

@Data
public class PagamentoPixDTO {
    private String chavePix;
    private String tipoChave; // EMAIL, CPF, TELEFONE, etc.
    private double valor;
}
