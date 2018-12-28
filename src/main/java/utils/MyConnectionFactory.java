package utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2018/12/28.
 */
public class MyConnectionFactory {

    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory=new ConnectionFactory();
        connectionFactory.setHost("47.106.98.232");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("user");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPassword("password");
        return connectionFactory.newConnection();
    }
}
