package br.com.peer.ecommerce.repository;

import br.com.peer.ecommerce.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    // Adicionado para login simples (sem hash seguro)
    Optional<Usuario> findByEmailAndSenhaHash(String email, String senhaHash);
}
