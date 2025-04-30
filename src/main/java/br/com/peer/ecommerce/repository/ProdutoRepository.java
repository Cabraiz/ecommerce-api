package br.com.peer.ecommerce.repository;

import br.com.peer.ecommerce.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Conta produtos com ativo = true
    long countByAtivoTrue();

    // Retorna todos os produtos ativos
    List<Produto> findAllByAtivoTrue();
}
