import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

public class Receive {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] arg) throws Exception {
        {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println(" [*] waiting for message to exit press ctrl + c");

            DeliverCallback deliverCallback = (consumerTag , delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] received '" + message + "'");
            };

            channel.basicConsume(QUEUE_NAME , true , deliverCallback, consumerTag -> {} );
        }

    }
}
