package br.com.peer.ecommerce.controller;

import br.com.peer.ecommerce.dto.LoginDTO;
import br.com.peer.ecommerce.dto.UsuarioResponseDTO;
import br.com.peer.ecommerce.model.Usuario;
import br.com.peer.ecommerce.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuários", description = "CRUD dos usuários do sistema")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @Operation(summary = "Lista todos os usuários")
    @GetMapping
    public List<Usuario> listar() {
        return service.listar();
    }

    @Operation(summary = "Cadastra um novo usuário")
    @PostMapping
    public Usuario salvar(@RequestBody Usuario usuario) {
        return service.salvar(usuario);
    }

    @Operation(summary = "Remove um usuário pelo ID")
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @Operation(summary = "Login do usuário (email + senha)")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO login) {
        Usuario usuario = service.buscarPorEmailESenha(login.getEmail(), login.getSenha());

        if (usuario != null) {
            UsuarioResponseDTO dto = new UsuarioResponseDTO();
            dto.setId(usuario.getId());
            dto.setNome(usuario.getNome());
            dto.setEmail(usuario.getEmail());
            dto.setPapel(usuario.getPapel());

            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou senha inválidos");
        }
    }
}
