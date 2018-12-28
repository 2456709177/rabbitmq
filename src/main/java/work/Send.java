package work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import utils.MyConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2018/12/28.
 * 非公平模式消息确认机制为自动提交 boolean autoAck=true
 * 发送着 [1,2,3,4,7,6,8]
 * 消费者1  [1，3，5，7]
 * 消费者2  [2，4，6，8]
 */

public class Send {
    private static String QUEUE_NAME="work_queue";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = MyConnectionFactory.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        for (int i = 0; i < 50; i++) {
            channel.basicPublish("",QUEUE_NAME,null,(i+"work_模式").getBytes());
            System.out.println("send"+i+"work_模式");
        }

        channel.close();
        connection.close();
    }
}
