package com.activeMQ;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringActiveMQTest {
	
	public static void main(String[] args) {		
		ApplicationContext springContext = new ClassPathXmlApplicationContext("SpringConfig.xml");
//		FileReader fileReader = (FileReader)springContext.getBean("FileReader");
//		
//		try {
//			fileReader.filesToQueue();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		Worker worker = (Worker)springContext.getBean("Worker");
//		worker.handleFile();
		
//		JmsTemplate template = (JmsTemplate) springContext.getBean("jmsTemplate");
//		ActiveMQDestination destination = (ActiveMQDestination)springContext.getBean("destination");
//		template.setDeliveryPersistent(true);
//		template.setDeliveryMode(DeliveryMode.PERSISTENT);
//				
//		template.convertAndSend(destination,"Test ActiveMQ persistence !!! send message");
//		System.out.println("message send to des !!!");
//		
//		Object message = template.receive(destination);
//		if(message instanceof TextMessage){
//			try{
//				System.out.println(((TextMessage)message).getText());
//			}catch(JMSException je){			
//				System.out.println(je.getStackTrace());
//			}				
//		}
		
		((AbstractApplicationContext)springContext).registerShutdownHook();
		
	}	
}
