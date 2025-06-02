package br.com.peer.ecommerce.controller;

import br.com.peer.ecommerce.dto.ReduzirEstoqueDTO;
import br.com.peer.ecommerce.dto.ItemRetiradaDTO;
import br.com.peer.ecommerce.dto.NovaRetiradaRequest;
import br.com.peer.ecommerce.service.EstoqueService;
import br.com.peer.ecommerce.service.RetiradaEstoqueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    private final EstoqueService estoqueService;
    private final RetiradaEstoqueService retiradaEstoqueService;

    public EstoqueController(EstoqueService estoqueService, RetiradaEstoqueService retiradaEstoqueService) {
        this.estoqueService = estoqueService;
        this.retiradaEstoqueService = retiradaEstoqueService;
    }

    @PostMapping("/reduzir")
    public ResponseEntity<?> reduzirEstoque(@RequestBody List<ReduzirEstoqueDTO> pedidos) {
        Long usuarioId = pedidos.stream()
                .map(ReduzirEstoqueDTO::getUsuarioId)
                .filter(id -> id != null)
                .findFirst()
                .orElse(1L); // Admin padrão

        List<ItemRetiradaDTO> itens = new ArrayList<>();

        for (ReduzirEstoqueDTO dto : pedidos) {
            Long tamanhoId = estoqueService.obterTamanhoId(dto.getVariacaoId(), dto.getTamanho());
            itens.add(new ItemRetiradaDTO(tamanhoId, dto.getQuantidade()));
        }

        NovaRetiradaRequest retirada = new NovaRetiradaRequest();
        retirada.setUsuarioId(usuarioId);
        retirada.setItens(itens);
        retirada.setObservacao("Retirada via /estoque/reduzir");

        retiradaEstoqueService.registrarRetiradaViaDTO(retirada);

        return ResponseEntity.ok("Todos os estoques foram reduzidos e vinculados ao usuário.");
    }
}
