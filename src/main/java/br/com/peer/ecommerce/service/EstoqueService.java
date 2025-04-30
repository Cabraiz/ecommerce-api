package br.com.peer.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {

    private final JdbcTemplate jdbcTemplate;

    public EstoqueService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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

            return updated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
