package com.springTest.springTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.activeMQ.FileReader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "SpringConfig.xml" })
public class FileReaderTest {

	@Autowired
	private FileReader fileReader;
	
	@Test
	public void testFileReader() throws InterruptedException{
		System.out.println("testing file reader");
		Thread.sleep(3000);
		Thread.sleep(3000);
	}

	public void setFileReader(FileReader fileReader) {
		this.fileReader = fileReader;
	}
	
	
}
