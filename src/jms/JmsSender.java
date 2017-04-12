package jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsSender {
	
	public static void main(String agrs[]) throws JMSException{
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");    
        
        Connection connection = connectionFactory.createConnection();    
        connection.start();    
          
        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);    
        Destination destination = session.createQueue("Test");    
  
        MessageProducer producer = session.createProducer(destination);    
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);  
        for(int i=0; i<100; i++) {    
            int id = i+1;  
            ObjectMessage message = session.createObjectMessage();  
            message.setObject(new User(id, "ÕÅÈý"+id, "123456"));  
            producer.send(message);    
        }    
        session.commit();  
        session.close();    
        connection.close();    
	}

}
