package br.com.peer.ecommerce.service;

import br.com.peer.ecommerce.model.Produto;
import br.com.peer.ecommerce.model.ImagemProduto;
import br.com.peer.ecommerce.repository.ProdutoRepository;
import br.com.peer.ecommerce.repository.ImagemProdutoRepository;
import br.com.peer.ecommerce.dto.ProdutoDTO;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;
    private final ImagemProdutoRepository imagemRepository;

    public ProdutoService(ProdutoRepository repository, ImagemProdutoRepository imagemRepository) {
        this.repository = repository;
        this.imagemRepository = imagemRepository;
    }

    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    public List<Produto> listar() {
        return repository.findAll();
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    // ✅ Novo método para entregar os dados no formato do frontend
    public List<ProdutoDTO> listarComImagem() {
        return repository.findAll().stream().map(produto -> {
            ImagemProduto imagem = imagemRepository.findFirstByProdutoId(produto.getId());
            String imagemBase64 = null;
            if (imagem != null && imagem.getDados() != null) {
                imagemBase64 = Base64.getEncoder().encodeToString(imagem.getDados());
            }

            return new ProdutoDTO(
                    String.valueOf(produto.getId()),
                    produto.getNome(),
                    produto.getDescricao(),
                    produto.getPreco(),
                    imagemBase64
            );
        }).collect(Collectors.toList());
    }
}
