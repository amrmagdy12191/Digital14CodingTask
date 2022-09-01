package com.digital14;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.digital14.builder.WriterFactory;
import com.digital14.writers.FileWriter;
import com.digital14.writers.StringWriter;

@SpringBootTest
class DemoApplicationTests {
	
	String filePath = "file.txt";

	@Test
	void contextLoads() {
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		Files.deleteIfExists(Paths.get(filePath));
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		Files.deleteIfExists(Paths.get(filePath));
	}
	
	@Test
	void testInitiateStringWriterWithRemoveStupidLower() throws Exception {
		String expected = "THIS IS IT";
		FileWriter fileWriter = WriterFactory.initFileWriterWithRemoveDuplicatesUpper();
		fileWriter.executeWriter("This is is it");		
		String actual = fileWriter.read().get(0);
		
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link com.digital14.builder.WriterFactory#initFileWriterWithRemoveDuplicatesUpper()}.
	 * @throws Exception 
	 */
	@Test
	void testInitFileWriterWithRemoveDuplicatesUpper() throws Exception {
		String expected = "this is really really s*****!!!";
		StringWriter stringWriter = WriterFactory.initiateStringWriterWithRemoveStupidLower();
		stringWriter.executeWriter("This is really really stupid!!!");		
		String actual = stringWriter.read().get(0);
		
		assertEquals(expected, actual);
	}

}
