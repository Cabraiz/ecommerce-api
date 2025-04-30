package br.com.peer.ecommerce.controller;

import br.com.peer.ecommerce.dto.ReduzirEstoqueDTO;
import br.com.peer.ecommerce.service.EstoqueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @PostMapping("/reduzir")
    public ResponseEntity<?> reduzirEstoque(@RequestBody ReduzirEstoqueDTO dto) {
        boolean sucesso = estoqueService.reduzirEstoque(dto.getVariacaoId(), dto.getTamanho(), dto.getQuantidade());

        if (sucesso) {
            return ResponseEntity.ok("Estoque reduzido com sucesso.");
        } else {
            return ResponseEntity.badRequest().body("Estoque insuficiente ou variação não encontrada.");
        }
    }
}
