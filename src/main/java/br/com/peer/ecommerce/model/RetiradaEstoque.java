package br.com.peer.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "retiradas_estoque")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RetiradaEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private LocalDateTime dataRetirada;

    private String observacao;

    @OneToMany(mappedBy = "retirada", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemRetiradaEstoque> itens;
}
