package com.example.demo.ActiveJiQun;
import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;
/**
 * @ Author     ：scw
 * @ Date       ：Created in 上午 11:06 2018/7/14 0014
 * @ Description：用于消息的创建类
 * @ Modified By：
 * @Version: $version$
 */
public class MessageProducer {
    //通过集群的方式进行消息服务器的管理（failover就是进行动态转移，当某个服务器宕机，
    // 那么就进行其他的服务器选择,randomize表示随机选择）
    private static final String ACTIVEMQ_URL = "failover:(tcp://127.0.0.1:61617,tcp://127.0.0.1:61618)?randomize=true";
    //定义发送消息的队列名称
    private static final String QUEUE_NAME = "MyMessage";

    public static void main(String[] args) throws JMSException {
        //创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
       //创建连接
        Connection connection = activeMQConnectionFactory.createConnection();
        //打开连接
        connection.start();
        //创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建队列目标
        Destination destination = session.createQueue(QUEUE_NAME);
        //创建一个生产者
        javax.jms.MessageProducer producer = session.createProducer(destination);
        //创建模拟100个消息
        for (int i = 1 ; i <= 100 ; i++){
            TextMessage message = session.createTextMessage("当前message是:" + i);
            //发送消息
            producer.send(message);
            //在本地打印消息
            System.out.println("我现在发的消息是：" + message.getText());
        }
        //关闭连接
        connection.close();
    }

}

