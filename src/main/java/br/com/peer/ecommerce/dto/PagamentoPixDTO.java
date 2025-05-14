package br.com.peer.ecommerce.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PagamentoPixDTO {

    @Schema(description = "Chave Pix de destino", example = "email@exemplo.com")
    private String chavePix;

    @Schema(description = "Tipo da chave Pix", example = "EMAIL") // Outras opções: CPF, TELEFONE
    private String tipoChave;

    @Schema(description = "Valor da transação em reais", example = "100.00")
    private double valor;
}
