package br.com.peer.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "itens_retirada_estoque")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemRetiradaEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "retirada_id")
    private RetiradaEstoque retirada;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tamanho_id")
    private TamanhoVariacao tamanho;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(name = "status")
    private String status;
}
