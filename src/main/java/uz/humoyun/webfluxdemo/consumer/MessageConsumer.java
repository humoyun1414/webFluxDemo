package uz.humoyun.webfluxdemo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import uz.humoyun.webfluxdemo.context.RabbitMqConfig;
import uz.humoyun.webfluxdemo.model.MessageDto;

@Component
public class MessageConsumer {

    @RabbitListener(queues = RabbitMqConfig.QUEUE)
    public void consumerMessage(MessageDto message) {
        System.out.println("You have new message : " + message);
    }

}
