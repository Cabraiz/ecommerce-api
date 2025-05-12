package br.com.peer.ecommerce.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static br.com.peer.ecommerce.config.RabbitMQConfig.QUEUE_ESTOQUE;

@Component
public class RabbitMQConsumer {

    @RabbitListener(queues = QUEUE_ESTOQUE)
    public void consumirMensagem(String mensagemJson) {
        System.out.println("📩 [RabbitMQConsumer] Mensagem recebida: " + mensagemJson);
    }
}
