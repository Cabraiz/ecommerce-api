package br.com.peer.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRetiradaDTO {
    private Long tamanhoId;
    private Integer quantidade;
}
