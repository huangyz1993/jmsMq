package jms;

import java.util.concurrent.TimeUnit;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsReceiver {

	
	public static void main(String agrs[]) throws JMSException, InterruptedException{
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");    
        
        Connection connection = connectionFactory.createConnection();    
        connection.start();  
        
        final Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);    
        Queue queue = session.createQueue("Test");  
          
        MessageConsumer consumer = session.createConsumer(queue);  
        
        //一下两行功能与上两行一致,Queue实现了Destination接口
//        Destination destination = session.createQueue("Test"); 
//        MessageConsumer consumer = session.createConsumer(destination); 
        //listener 方式   
        consumer.setMessageListener(new MessageListener() {   
       
            public void onMessage(Message msg) {   
                ObjectMessage message = (ObjectMessage) msg;   
                //TODO something....   
                try {  
                    User user = (User) message.getObject();  
                    System.out.println("收到消息："+user.getUsername());  
                } catch (JMSException e1) {  
                    // TODO Auto-generated catch block  
                    e1.printStackTrace();  
                }   
                try {  
                    session.commit();  
                } catch (JMSException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }   
            }   
       
        });   
        TimeUnit.MINUTES.sleep(1);   
        
        session.close();    
        connection.close();  
	}
}
