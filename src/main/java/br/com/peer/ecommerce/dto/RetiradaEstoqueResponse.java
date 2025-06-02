package br.com.peer.ecommerce.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class RetiradaEstoqueResponse {
    private Long id;
    private LocalDateTime dataRetirada;
    private String observacao;
    private List<ItemRetiradaResponse> itens;
}
