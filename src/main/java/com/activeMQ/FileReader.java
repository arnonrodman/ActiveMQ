package com.activeMQ;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.activemq.command.ActiveMQDestination;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class FileReader {

	private final String folderPath = "/home/arnonr/Downloads/ActiveMQTest";
	private final String rchivefolderPath = "/home/arnonr/Downloads/ActiveMQTest/Archive";
	private JmsTemplate jmsTemplate;
	private ActiveMQDestination destination;


	@Scheduled(fixedDelay = 30000)
	public void filesToQueue() throws IOException {
		
		System.out.println("File reading scheduled activated !!! "+Thread.currentThread().getName());
		
		List<File> filesFromFolder = readFilesFromFolder();

		for (File file : filesFromFolder) {
			System.out.println("Producer send message "+file.getAbsolutePath());
			
			jmsTemplate.convertAndSend(destination,file.getAbsolutePath().trim());
		}
	}

	private List<File> readFilesFromFolder() throws IOException {
		return Files.walk(Paths.get(folderPath)).filter(Files::isRegularFile).map(Path::toFile)
				.collect(Collectors.toList());
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void setDestination(ActiveMQDestination destination) {
		this.destination = destination;
	}
	
	

}
