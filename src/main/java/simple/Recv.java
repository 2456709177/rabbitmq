package simple;

import com.rabbitmq.client.*;
import utils.MyConnectionFactory;

import java.io.IOException;

/**
 * Created by Administrator on 2018/12/28.
 */
public class Recv {
    private static String QUEUE_NAME="simple_queue";
    public static void main(String[] args) throws Exception {
        //获取amqp连接
        Connection connection = MyConnectionFactory.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //接受消息consumer
        Consumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("接收到消息："+new String(body));
            }
        };
        //绑定关系
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
