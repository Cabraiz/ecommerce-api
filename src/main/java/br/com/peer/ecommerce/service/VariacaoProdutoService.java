package br.com.peer.ecommerce.service;

import br.com.peer.ecommerce.dto.TamanhoDTO;
import br.com.peer.ecommerce.dto.VariacaoComTamanhosDTO;
import br.com.peer.ecommerce.model.TamanhoVariacao;
import br.com.peer.ecommerce.model.VariacaoProduto;
import br.com.peer.ecommerce.repository.TamanhoVariacaoRepository;
import br.com.peer.ecommerce.repository.VariacaoProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VariacaoProdutoService {

    private final VariacaoProdutoRepository variacaoRepository;
    private final TamanhoVariacaoRepository tamanhoRepository;

    public VariacaoProdutoService(VariacaoProdutoRepository variacaoRepository, TamanhoVariacaoRepository tamanhoRepository) {
        this.variacaoRepository = variacaoRepository;
        this.tamanhoRepository = tamanhoRepository;
    }

    // ✅ Método necessário para funcionar o controller
    public List<VariacaoProduto> listar() {
        return variacaoRepository.findAll();
    }

    public List<VariacaoComTamanhosDTO> listarComTamanhos() {
        return variacaoRepository.findAll().stream().map(variacao -> {
            List<TamanhoVariacao> tamanhos = tamanhoRepository.findByVariacaoId(variacao.getId());

            List<TamanhoDTO> tamanhosDTO = tamanhos.stream()
                    .map(t -> new TamanhoDTO(t.getTamanho(), t.getEstoque()))
                    .toList();

            return new VariacaoComTamanhosDTO(
                    variacao.getId(),
                    variacao.getCor(),
                    variacao.getProdutoId(),
                    tamanhosDTO
            );
        }).collect(Collectors.toList());
    }

    public VariacaoProduto salvar(VariacaoProduto variacao) {
        return variacaoRepository.save(variacao);
    }

    public void deletar(Long id) {
        variacaoRepository.deleteById(id);
    }
}
