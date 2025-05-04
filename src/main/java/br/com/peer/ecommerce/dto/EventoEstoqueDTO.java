package br.com.peer.ecommerce.dto;

public class EventoEstoqueDTO {
    private Long variacaoId;
    private String tamanho;
    private int quantidade;

    public EventoEstoqueDTO() {}

    public EventoEstoqueDTO(Long variacaoId, String tamanho, int quantidade) {
        this.variacaoId = variacaoId;
        this.tamanho = tamanho;
        this.quantidade = quantidade;
    }

    public Long getVariacaoId() {
        return variacaoId;
    }

    public void setVariacaoId(Long variacaoId) {
        this.variacaoId = variacaoId;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
