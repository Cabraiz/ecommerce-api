package br.com.peer.ecommerce.service;

import br.com.peer.ecommerce.dto.ItemRetiradaDTO;
import br.com.peer.ecommerce.dto.ItemRetiradaResponse;
import br.com.peer.ecommerce.dto.NovaRetiradaRequest;
import br.com.peer.ecommerce.dto.RetiradaEstoqueResponse;
import br.com.peer.ecommerce.model.*;
import br.com.peer.ecommerce.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RetiradaEstoqueService {

    private final UsuarioRepository usuarioRepository;
    private final TamanhoVariacaoRepository tamanhoVariacaoRepository;
    private final RetiradaEstoqueRepository retiradaRepository;

    @Transactional
    public void registrarRetiradaViaDTO(NovaRetiradaRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        RetiradaEstoque retirada = RetiradaEstoque.builder()
                .usuario(usuario)
                .observacao(request.getObservacao())
                .dataRetirada(LocalDateTime.now())
                .itens(new ArrayList<>())
                .build();

        for (ItemRetiradaDTO itemDTO : request.getItens()) {
            TamanhoVariacao tamanho = tamanhoVariacaoRepository.findById(itemDTO.getTamanhoId())
                    .orElseThrow(() -> new IllegalArgumentException("Tamanho não encontrado: " + itemDTO.getTamanhoId()));

            if (tamanho.getEstoque() < itemDTO.getQuantidade()) {
                throw new IllegalStateException("Estoque insuficiente para tamanho ID: " + itemDTO.getTamanhoId());
            }

            tamanho.setEstoque(tamanho.getEstoque() - itemDTO.getQuantidade());
            tamanhoVariacaoRepository.save(tamanho);

            ItemRetiradaEstoque item = ItemRetiradaEstoque.builder()
                    .retirada(retirada)
                    .tamanho(tamanho)
                    .quantidade(itemDTO.getQuantidade())
                    .build();

            retirada.getItens().add(item);
        }

        retiradaRepository.save(retirada);
    }

    public List<RetiradaEstoqueResponse> listarPorUsuario(Long usuarioId) {
        List<RetiradaEstoque> retiradas = retiradaRepository.findByUsuarioId(usuarioId);

        return retiradas.stream().map(retirada -> {
            RetiradaEstoqueResponse resp = new RetiradaEstoqueResponse();
            resp.setId(retirada.getId());
            resp.setDataRetirada(retirada.getDataRetirada());
            resp.setObservacao(retirada.getObservacao());

            List<ItemRetiradaResponse> itens = retirada.getItens().stream().map(item -> {
                ItemRetiradaResponse dto = new ItemRetiradaResponse();
                dto.setTamanhoId(item.getTamanho().getId());
                dto.setTamanho(item.getTamanho().getTamanho());
                dto.setCorProduto(item.getTamanho().getVariacao().getCor());
                dto.setQuantidade(item.getQuantidade());
                return dto;
            }).collect(Collectors.toList());

            resp.setItens(itens);
            return resp;
        }).collect(Collectors.toList());
    }
}
