package br.com.peer.ecommerce.repository;

import br.com.peer.ecommerce.model.TamanhoVariacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TamanhoVariacaoRepository extends JpaRepository<TamanhoVariacao, Long> {
    List<TamanhoVariacao> findByVariacaoId(Long variacaoId);
}
