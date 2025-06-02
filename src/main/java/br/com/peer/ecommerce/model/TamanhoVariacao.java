package br.com.peer.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tamanhos_variacao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TamanhoVariacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tamanho;

    private Integer estoque;

    @Column(name = "variacao_id")
    private Long variacaoId;

    @ManyToOne
    @JoinColumn(name = "variacao_id", insertable = false, updatable = false)
    private VariacaoProduto variacao;
}
