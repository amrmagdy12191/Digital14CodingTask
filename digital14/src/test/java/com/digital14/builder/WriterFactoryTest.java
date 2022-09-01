/**
 * 
 */
package com.digital14.builder;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.digital14.writers.FileWriter;
import com.digital14.writers.StringWriter;

import ch.qos.logback.core.util.FileUtil;

/**
 * @author amrmagdy
 *
 */
class WriterFactoryTest {
	
	String filePath = "file.txt";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {		
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		Files.deleteIfExists(Paths.get(filePath));
	}

	/**
	 * Test method for {@link com.digital14.builder.WriterFactory#initiateStringWriterWithRemoveStupidLower()}.
	 * @throws Exception 
	 */
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
