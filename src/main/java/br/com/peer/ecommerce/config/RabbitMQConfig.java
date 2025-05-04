package br.com.peer.ecommerce.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_ESTOQUE = "estoque.reduzido";

    @Bean
    public Queue estoqueQueue() {
        System.out.println("🎬 [RabbitMQConfig] (CloudAMQP) Fila simulada criada: '" + QUEUE_ESTOQUE + "'");
        return new Queue(QUEUE_ESTOQUE, false);
    }
}
