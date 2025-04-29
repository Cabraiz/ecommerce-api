package br.com.peer.ecommerce.controller;

import br.com.peer.ecommerce.model.ImagemProduto;
import br.com.peer.ecommerce.service.ImagemProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

import java.util.List;

@Tag(name = "Imagens de Produto", description = "CRUD de imagens vinculadas aos produtos")
@RestController
@RequestMapping("/imagens")
public class ImagemProdutoController {

    private final ImagemProdutoService service;

    public ImagemProdutoController(ImagemProdutoService service) {
        this.service = service;
    }

    @Operation(summary = "Lista todas as imagens de produtos")
    @GetMapping
    public List<ImagemProduto> listar() {
        return service.listar();
    }

    @Operation(summary = "Cadastra uma nova imagem de produto")
    @PostMapping
    public ImagemProduto salvar(@RequestBody ImagemProduto imagem) {
        return service.salvar(imagem);
    }

    @Operation(summary = "Remove uma imagem de produto pelo ID")
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    // ✅ NOVO: Atualiza imagem com upload de arquivo
    @Operation(summary = "Atualiza a imagem de um produto via upload de arquivo")
    @PutMapping(value = "/{id}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> atualizarImagem(
            @PathVariable Long id,
            @Parameter(description = "Arquivo de imagem", required = true)
            @RequestPart("imagem") MultipartFile imagem) {

        boolean sucesso = service.atualizarImagem(id, imagem);
        if (sucesso) {
            return ResponseEntity.ok("Imagem atualizada com sucesso!");
        } else {
            return ResponseEntity.badRequest().body("Falha ao atualizar imagem.");
        }
    }

    @GetMapping("/{id}/imagem")
    public ResponseEntity<byte[]> obterImagem(@PathVariable Long id) {
        Optional<ImagemProduto> imagemOpt = service.buscarPorId(id);
        if (imagemOpt.isPresent() && imagemOpt.get().getDados() != null) {
            ImagemProduto img = imagemOpt.get();
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.parseMediaType(img.getTipo()))
                    .body(img.getDados());
        }
        return ResponseEntity.notFound().build();
    }
}
