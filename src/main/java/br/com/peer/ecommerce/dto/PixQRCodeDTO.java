package br.com.peer.ecommerce.dto;

import lombok.Data;

@Data
public class PixQRCodeDTO {
    private String chavePix;
    private double valor;
    private String descricao;
}
