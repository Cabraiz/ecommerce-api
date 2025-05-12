package br.com.peer.ecommerce.controller;

import br.com.peer.ecommerce.dto.PixQRCodeDTO;
import br.com.peer.ecommerce.service.PagamentoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamentos")
@Tag(name = "Pagamentos", description = "Operações de pagamento com Pix ou Cartão")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping("/pix")
    public ResponseEntity<String> pagarComPix() {
        String resultado = pagamentoService.processarPagamentoPix();
        return ResponseEntity.ok(resultado);
    }

    @PostMapping("/cartao")
    public ResponseEntity<String> pagarComCartao() {
        String resultado = pagamentoService.processarPagamentoCartao();
        return ResponseEntity.ok(resultado);
    }

    //https://codebeautify.org/base64-to-image-converter#google_vignette
    @PostMapping("/pix/qrcode")
    public ResponseEntity<String> gerarQRCodePix(@RequestBody PixQRCodeDTO dto) {
        String base64QrCode = pagamentoService.gerarQRCodePix(dto.getChavePix(), dto.getValor(), dto.getDescricao());
        if (base64QrCode == null) {
            return ResponseEntity.internalServerError().body("Erro ao gerar QR Code Pix");
        }
        return ResponseEntity.ok(base64QrCode);
    }
}
