package com.insigma.mvc.serviceimp.jms;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.StreamMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.insigma.mvc.service.jms.JmsProducerService;

/**
 * jms消息服务
 * 
 * @author wengsh
 *
 */
//@Service
public class JmsProducerServiceImpl implements JmsProducerService {

	private Log log = LogFactory.getLog(JmsProducerServiceImpl.class);

	//@Resource(name = "jmsQueueTemplate")
	//@Resource(name="jmsTopicTemplate")
    private JmsTemplate jmsTemplate;

	/**
	 * 向默认队列发送消息
	 */
	public void sendMessage(final String msg) {
		String destination = jmsTemplate.getDefaultDestination().toString();
		log.info("向队列" + destination + "发送了消息------------" + msg);
		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(msg);
			}
		});
	}

	/**
	 * 向指定Destination发送map消息
	 * 
	 * @param destination
	 * @param message
	 */
	public void sendMapMessage(final String message) {
		String destination = jmsTemplate.getDefaultDestination().toString();
		jmsTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				MapMessage mapMessage = session.createMapMessage();
				mapMessage.setString("msgId", message);
				return mapMessage;
			}
		});
		System.out.println("springJMS send map message...");
	}

	/**
	 * 向指定Destination发送序列化的对象
	 * 
	 * @param destination
	 * @param object object 必须序列化
	 */
	public void sendObjectMessage(final Serializable object) {
		String destination = jmsTemplate.getDefaultDestination().toString();
		jmsTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage(object);
			}
		});
		System.out.println("springJMS send object message...");
	}

	/**
	 * 向指定Destination发送字节消息
	 * 
	 * @param destination
	 * @param bytes
	 */
	public void sendBytesMessage(final byte[] bytes) {
		String destination = jmsTemplate.getDefaultDestination().toString();
		jmsTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				BytesMessage bytesMessage = session.createBytesMessage();
				bytesMessage.writeBytes(bytes);
				return bytesMessage;

			}
		});
		System.out.println("springJMS send bytes message...");
	}

	/**
	 * 向默认队列发送Stream消息
	 */
	public void sendStreamMessage() {
		String destination = jmsTemplate.getDefaultDestination().toString();
		jmsTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				StreamMessage message = session.createStreamMessage();
				message.writeString("stream string");
				message.writeInt(11111);
				return message;
			}
		});
		System.out.println("springJMS send Strem message...");
	}
}