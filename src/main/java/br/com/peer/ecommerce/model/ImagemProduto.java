package br.com.peer.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "imagens_produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "produto_id")
    private Long produtoId;

    @Column(name = "dados")
    private byte[] dados;

    @Column(name = "tipo")
    private String tipo;
}
