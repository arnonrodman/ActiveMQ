package com.activeMQ;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import javax.jms.JMSException;

import org.apache.activemq.command.ActiveMQDestination;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Worker {

	private JmsTemplate jmsTemplate;
	private ActiveMQDestination destination;
	private final String archivefolderPathDone = "/home/arnonr/Downloads/ActiveMQTest/ArDone";
	volatile private static int counter = 0;

	@Scheduled(fixedRate = 30000)
	@Async
	@Transactional(rollbackFor = Exception.class)
	public void handleFile() throws JMSException, InterruptedException, IOException {
		counter++;		
		System.out.println("********************************************************");
		System.out.println(" worker " + Thread.currentThread().getName() + " activated "+new Date());
		System.out.println("********************************************************");

		// read file from queue. 
		Object message = jmsTemplate.receiveAndConvert(destination);

		if(message!=null){
			handleFile(message);

			moveHandeledMessage(message);
		}
		
	}

	private void handleFile(Object message) throws InterruptedException, IOException, JMSException {
		System.out.println("Worker handle file : " + message.toString());

		if(message.toString().contains("104000514813450EVENTS000003201603241400271001")){		
			System.out.println("shooooooooooooo  sleeping on" + message.toString()
					+ " !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! $$$$$$$$$$$$$$$$$$$$$$$$");
			Thread.sleep(40000);
			System.out.println("FINISH  sleeping on" + message.toString()
					+ " !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! $$$$$$$$$$$$$$$$$$$$$$$$");
		}else if(message.toString().contains("104000514813450EVENTS000003201603241030161001")
				|| message.toString().contains("104000514813450EVENTS000003201603241430401001")){
			throw new JMSException("fuck file failed"+message.toString());
		}
	}

	/**
	 * move file into archived done folder.
	 * 
	 * @param message
	 * @throws IOException
	 */
	private void moveHandeledMessage(Object message) throws IOException {
		String[] fileName = message.toString().split("/");
		Path source = Paths.get(message.toString());
		Path targetDir = Paths.get(archivefolderPathDone, fileName[fileName.length - 1]);

		// create Archive folder if not exists.
		File dir = new File(archivefolderPathDone);
		if (!dir.exists())
			dir.mkdirs();
		// move file from message to archive when finished.
		Files.move(source, targetDir, StandardCopyOption.REPLACE_EXISTING);
		System.out.println("moving file from:" + source.toString() + " to :" + targetDir.toString());

	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void setDestination(ActiveMQDestination destination) {
		this.destination = destination;
	}

}
