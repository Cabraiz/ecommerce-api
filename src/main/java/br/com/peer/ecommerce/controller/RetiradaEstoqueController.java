package br.com.peer.ecommerce.controller;

import br.com.peer.ecommerce.dto.NovaRetiradaRequest;
import br.com.peer.ecommerce.dto.ProdutoRetiradoResponse;
import br.com.peer.ecommerce.dto.RetiradaEstoqueResponse;
import br.com.peer.ecommerce.service.RetiradaEstoqueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/retiradas")
@RequiredArgsConstructor
@Tag(name = "Retiradas de Estoque", description = "Gerencia as retiradas de estoque feitas por usuários")
public class RetiradaEstoqueController {

    private final RetiradaEstoqueService retiradaEstoqueService;

    @PostMapping
    @Operation(summary = "Registrar uma nova retirada de estoque")
    public ResponseEntity<?> registrarRetirada(@RequestBody NovaRetiradaRequest request) {
        retiradaEstoqueService.registrarRetiradaViaDTO(request);
        return ResponseEntity.ok("Retirada registrada com sucesso.");
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Listar retiradas completas feitas por um usuário")
    public ResponseEntity<List<RetiradaEstoqueResponse>> listarRetiradasPorUsuario(
            @PathVariable Long usuarioId) {
        return ResponseEntity.ok(retiradaEstoqueService.listarPorUsuario(usuarioId));
    }

    @GetMapping("/usuario/{usuarioId}/produtos")
    @Operation(summary = "Listar todos os produtos retirados por um usuário")
    public ResponseEntity<List<ProdutoRetiradoResponse>> listarProdutosPorUsuario(
            @PathVariable Long usuarioId) {
        return ResponseEntity.ok(retiradaEstoqueService.listarProdutosRetiradosPorUsuario(usuarioId));
    }
}
