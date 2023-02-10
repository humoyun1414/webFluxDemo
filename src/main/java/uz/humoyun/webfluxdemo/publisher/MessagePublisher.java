package uz.humoyun.webfluxdemo.publisher;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import uz.humoyun.webfluxdemo.context.RabbitMqConfig;
import uz.humoyun.webfluxdemo.model.MessageDto;

@RestController
@RequestMapping("/api/rabbit/v1")
public class MessagePublisher {

    private final AmqpTemplate template;

    public MessagePublisher(final RabbitTemplate template) {
        this.template = template;
    }

    @PostMapping("/send-message")
    public Mono<String> sendMessage(@RequestBody MessageDto dto) {
        return Mono.fromCallable(() -> {
            template.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.ROUTING_KEY, dto);
            return "Message send";
        });

    }

}
