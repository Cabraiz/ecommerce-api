package br.com.peer.ecommerce.controller;

import br.com.peer.ecommerce.dto.PagamentoCartaoDTO;
import br.com.peer.ecommerce.dto.PixQRCodeDTO;
import br.com.peer.ecommerce.service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamentos")
@Tag(name = "Pagamentos", description = "Operações de pagamento com Pix ou Cartão")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    private static final String CHAVE_PIX_FIXA = "61070800317";

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @Operation(
            summary = "Pagamento com Pix",
            description = "Processa um pagamento utilizando o método Pix.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pagamento via Pix processado com sucesso")
            }
    )
    @PostMapping("/pix/payload")
    public ResponseEntity<String> gerarPayloadPix(
            @RequestBody(
                    description = "Gera apenas o código Pix (não o QR base64)",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = """
                        {
                          "valor": 75.00,
                          "descricao": "Doação para festa"
                        }
                        """)
                    )
            )
            @org.springframework.web.bind.annotation.RequestBody PixQRCodeDTO dto
    ) {
        String payload = pagamentoService.gerarPayloadPix(CHAVE_PIX_FIXA, dto.getValor(), dto.getDescricao());
        if (payload == null) {
            return ResponseEntity.internalServerError().body("Erro ao gerar código Pix");
        }
        return ResponseEntity.ok(payload);
    }

    @Operation(
            summary = "Pagamento com Cartão",
            description = "Processa um pagamento utilizando cartão de crédito.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pagamento com cartão processado com sucesso")
            }
    )
    @PostMapping("/cartao")
    public ResponseEntity<String> pagarComCartao(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados do pagamento com cartão",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = """
                    {
                      "nomeTitular": "João da Silva",
                      "numeroCartao": "4111111111111111",
                      "validade": "12/2027",
                      "cvv": "123",
                      "valor": 250.75
                    }
                """)
                    )
            )
            @org.springframework.web.bind.annotation.RequestBody PagamentoCartaoDTO dto
    ) {
        String resultado = pagamentoService.processarPagamentoCartao();
        return ResponseEntity.ok(resultado);
    }

    @Operation(
            summary = "Gerar QR Code para pagamento Pix",
            description = "Gera um QR Code (imagem base64) a partir de um valor e descrição. A chave Pix é fixa no backend.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "QR Code gerado com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro ao gerar QR Code Pix")
            }
    )
    @PostMapping("/pix/qrcode")
    public ResponseEntity<String> gerarQRCodePix(
            @RequestBody(
                    description = "Informações para gerar o QR Code (chave Pix é fixa)",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = """
                    {
                      "valor": 150.75,
                      "descricao": "Pagamento de pedido #12345"
                    }
                """)
                    )
            )
            @org.springframework.web.bind.annotation.RequestBody PixQRCodeDTO dto
    ) {
        String base64QrCode = pagamentoService.gerarQRCodePix(CHAVE_PIX_FIXA, dto.getValor(), dto.getDescricao());
        if (base64QrCode == null) {
            return ResponseEntity.internalServerError().body("Erro ao gerar QR Code Pix");
        }
        return ResponseEntity.ok(base64QrCode);
    }
}
