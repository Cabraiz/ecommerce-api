package br.com.peer.ecommerce.rabbit;

import br.com.peer.ecommerce.dto.EventoEstoqueDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import static br.com.peer.ecommerce.config.RabbitMQConfig.QUEUE_ESTOQUE;

@Component
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarMensagemEstoqueReduzido(EventoEstoqueDTO evento) {
        try {
            String json = objectMapper.writeValueAsString(evento);
            rabbitTemplate.convertAndSend(QUEUE_ESTOQUE, json);
            System.out.println("📤 [RabbitMQProducer] Enviado para fila '" + QUEUE_ESTOQUE + "': " + json);
        } catch (Exception e) {
            System.err.println("❌ Erro ao enviar para RabbitMQ: " + e.getMessage());
        }
    }
}
