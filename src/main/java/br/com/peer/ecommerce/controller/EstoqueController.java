package br.com.peer.ecommerce.controller;

import br.com.peer.ecommerce.dto.ReduzirEstoqueDTO;
import br.com.peer.ecommerce.service.EstoqueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @PostMapping("/reduzir")
    public ResponseEntity<?> reduzirEstoque(@RequestBody List<ReduzirEstoqueDTO> pedidos) {
        for (ReduzirEstoqueDTO dto : pedidos) {
            boolean sucesso = estoqueService.reduzirEstoque(
                    dto.getVariacaoId(), dto.getTamanho(), dto.getQuantidade());

            if (!sucesso) {
                return ResponseEntity
                        .badRequest()
                        .body("Erro ao reduzir estoque da variação ID "
                                + dto.getVariacaoId() + ", tamanho " + dto.getTamanho());
            }
        }

        return ResponseEntity.ok("Todos os estoques foram reduzidos com sucesso.");
    }
}
