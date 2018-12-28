package work_avg;

import com.rabbitmq.client.*;
import utils.MyConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2018/12/28.
 */
public class Recv1 {
    private static String QUEUE_NAME="work_avg_queue";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = MyConnectionFactory.getConnection();
        final Channel channel = connection.createChannel();
        channel.basicQos(1);//保证一次只分发一个
        Consumer defaultConsumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("recv[1]:"+new String(body));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }

            }
        };
        channel.basicConsume(QUEUE_NAME,false,defaultConsumer);
    }
}
