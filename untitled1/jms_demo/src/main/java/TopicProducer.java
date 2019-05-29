import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息生产者
 */
public class TopicProducer {

    public static void main(String[] args) throws JMSException {
        //1.创建连接工厂ConnectionFactory =new ActiveMQConnectionFactory("tcp://192.168.25.135:61616")
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.218.128:61616");
        //2.获取连接factory.createConnection
        Connection connection = factory.createConnection();
        //3.启动连接connection.start()
        connection.start();
        //4.获取session，connection.createSession(参数1：是否启动事务,参数2：消息确认模式[Session.])
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.创建队列对象session.createQueue
        //5.创建队列对象session.createQueue
        Topic topic = session.createTopic("test-topic");

        //6.创建消息生产者session.createProducer
        MessageProducer producer = session.createProducer(topic);
        //7.创建消息session.createTextMessage(消息内容)
        TextMessage message = session.createTextMessage("胡士大夫77");
        //8.发送消息producer.send
        producer.send(message);
        //9.关闭资源producer,session,connection
        producer.close();
        session.close();
        connection.close();

    }
}
