package br.com.peer.ecommerce.rabbit;

import br.com.peer.ecommerce.dto.EventoEstoqueDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import static br.com.peer.ecommerce.config.RabbitMQConfig.QUEUE_ESTOQUE;

@Component
public class RabbitMQProducer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void enviarMensagemEstoqueReduzido(EventoEstoqueDTO evento) {
        try {
            String json = objectMapper.writeValueAsString(evento);
            System.out.println("📤 [RabbitMQProducer] (CloudAMQP) Enviando para fila '" +
                    QUEUE_ESTOQUE + "': " + json);
        } catch (Exception e) {
            System.err.println("❌ Erro ao simular envio RabbitMQ: " + e.getMessage());
        }
    }
}
