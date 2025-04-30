package br.com.peer.ecommerce.controller;

import br.com.peer.ecommerce.dto.ProdutoDTO;
import br.com.peer.ecommerce.model.Produto;
import br.com.peer.ecommerce.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Produtos", description = "CRUD do catálogo de produtos")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    // ✅ Agora retorna DTOs com imagem base64
    @Operation(summary = "Lista todos os produtos com imagem em base64 (frontend)")
    @GetMapping
    public List<ProdutoDTO> listar() {
        return service.listarComImagem();
    }

    @Operation(summary = "Cadastra um novo produto")
    @PostMapping
    public Produto salvar(@RequestBody Produto produto) {
        return service.salvar(produto);
    }

    @Operation(summary = "Deleta um produto pelo ID")
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
