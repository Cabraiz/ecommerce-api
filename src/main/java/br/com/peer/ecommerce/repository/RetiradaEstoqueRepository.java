package br.com.peer.ecommerce.repository;

import br.com.peer.ecommerce.model.RetiradaEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RetiradaEstoqueRepository extends JpaRepository<RetiradaEstoque, Long> {

    // Busca todas as retiradas feitas por um usuário específico
    List<RetiradaEstoque> findByUsuarioId(Long usuarioId);
}
