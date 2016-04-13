package com.activeMQ;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.activemq.command.ActiveMQDestination;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class FileReader {

	private final String folderPath = "/home/arnonr/Downloads/ActiveMQTest/IN";
	private final String archivefolderPath = "/home/arnonr/Downloads/ActiveMQTest/Archive";
	
	
	private JmsTemplate jmsTemplate;	
	private ActiveMQDestination destination;


	@Scheduled(fixedDelay = 30000)	
	@Transactional(rollbackFor=Exception.class)
	public void filesToQueue() throws IOException {
		
		System.out.println("File reading scheduled activated !!! "+new Date());
		
		List<File> filesFromFolder = readFilesFromFolder();
		String archiveFileMessgae;
		for (File file : filesFromFolder) {		
						
			//move file to archive folder.
			archiveFileMessgae = moveHandeledMessageToarchive(file.getAbsolutePath().trim());
			
			//send file  full path to ActiveMQ.
			System.out.println("Producer send message "+archiveFileMessgae);
			jmsTemplate.convertAndSend(destination,archiveFileMessgae);
		}
		filesFromFolder.clear();
	}
	
	/**
	 * move incoming files to archive folder.
	 * @param filePath
	 * @return archived file full path. 
	 * @throws IOException
	 */
	private String moveHandeledMessageToarchive(String filePath) throws IOException{
		String[] fileName = filePath.toString().split("/");
		Path source = Paths.get(filePath.toString());
        Path targetDir = Paths.get(archivefolderPath, fileName[fileName.length-1]);
        
        //create Archive folder if not exists.
        File dir =  new File(archivefolderPath);
        if(!dir.exists())
        		dir.mkdirs();
        //move file from message to archive when finished.		
		Files.move(source,targetDir, StandardCopyOption.REPLACE_EXISTING);
		System.out.println("moving file from:"+source.toString()+" to :"+targetDir.toString());
		return targetDir.toString();
			
	}

	/**
	 * Read from "folder path" incoming files.
	 * @return
	 * @throws IOException
	 */
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
