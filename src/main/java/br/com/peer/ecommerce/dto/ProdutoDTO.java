package br.com.peer.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProdutoDTO {
    private String id;
    private String name;
    private String description;
    private double price;
    private String image; // base64 da imagem

    public ProdutoDTO() {}

    public ProdutoDTO(String id, String name, String description, double price, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }
}
