package com.activeMQ;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQDestination;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Worker {	
	
	private JmsTemplate jmsTemplate;
	private ActiveMQDestination destination;
	
	
	@Scheduled(fixedDelay=3000)
	public void handleFile(){
		System.out.println(" worker "+Thread.currentThread().getName()+" activated");
		
		Object message = jmsTemplate.receiveAndConvert(destination);		
		System.out.println(" worker "+Thread.currentThread().getName()+" pooled message "+message.toString());
		
		if(message instanceof TextMessage){
			try{
				System.out.println("Worker handle file : "+((TextMessage)message).getText());
			}catch(JMSException je){			
				System.out.println(je.getStackTrace());
			}				
		}		
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void setDestination(ActiveMQDestination destination) {
		this.destination = destination;
	}

	
}
