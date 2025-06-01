package br.com.peer.ecommerce.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PagamentoPixDTO {

    @Schema(description = "Valor da transação em reais", example = "100.00")
    private double valor;

    @Schema(description = "Descrição da transação", example = "Presente de casamento")
    private String descricao;
}
