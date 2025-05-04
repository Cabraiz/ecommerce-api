package br.com.peer.ecommerce.service;

import br.com.peer.ecommerce.dto.EventoEstoqueDTO;
import br.com.peer.ecommerce.rabbit.RabbitMQProducer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {

    private final JdbcTemplate jdbcTemplate;
    private final RabbitMQProducer rabbitMQProducer;

    public EstoqueService(JdbcTemplate jdbcTemplate, RabbitMQProducer rabbitMQProducer) {
        this.jdbcTemplate = jdbcTemplate;
        this.rabbitMQProducer = rabbitMQProducer;
    }

    public boolean reduzirEstoque(Long variacaoId, String tamanho, int quantidade) {
        try {
            Integer estoqueAtual = jdbcTemplate.queryForObject("""
                SELECT estoque FROM tamanhos_variacao
                WHERE variacao_id = ? AND tamanho = ?
            """, Integer.class, variacaoId, tamanho);

            if (estoqueAtual == null || estoqueAtual < quantidade) {
                return false;
            }

            int updated = jdbcTemplate.update("""
                UPDATE tamanhos_variacao
                SET estoque = estoque - ?
                WHERE variacao_id = ? AND tamanho = ?
            """, quantidade, variacaoId, tamanho);

            if (updated > 0) {
                EventoEstoqueDTO evento = new EventoEstoqueDTO(variacaoId, tamanho, quantidade);
                rabbitMQProducer.enviarMensagemEstoqueReduzido(evento);
            }

            return updated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
