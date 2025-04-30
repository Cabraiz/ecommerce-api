package br.com.peer.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VariacaoComTamanhosDTO {
    private Long id;
    private String cor;
    private Long produtoId;
    private List<TamanhoDTO> tamanhos;
}
