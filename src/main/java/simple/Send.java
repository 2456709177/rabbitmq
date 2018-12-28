package simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import utils.MyConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2018/12/28.
 */
public class Send {
    private static String QUEUE_NAME="simple_queue";
    public static void main(String[] args) throws IOException, TimeoutException {
        //获取amqp连接
        Connection connection = MyConnectionFactory.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //发送消息
        String msg="你好";
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        System.out.println("发送消息："+msg);
        //关闭通道
        channel.close();
        //关闭链接
        connection.close();
    }
}
