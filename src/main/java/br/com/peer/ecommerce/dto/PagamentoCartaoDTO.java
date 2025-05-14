package br.com.peer.ecommerce.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PagamentoCartaoDTO {

    @Schema(description = "Nome completo do titular do cartão", example = "João da Silva")
    private String nomeTitular;

    @Schema(description = "Número do cartão de crédito", example = "4111111111111111")
    private String numeroCartao;

    @Schema(description = "Validade do cartão no formato MM/AAAA", example = "12/2027")
    private String validade;

    @Schema(description = "Código de segurança (CVV) do cartão", example = "123")
    private String cvv;

    @Schema(description = "Valor da transação em reais", example = "250.75")
    private double valor;
}
