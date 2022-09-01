/**
 * 
 */
package com.digital14.builder;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.digital14.operations.Operation;
import com.digital14.operations.OperationType;
import com.digital14.operations.ReadLowerCaseOperation;
import com.digital14.operations.RemoveDuplicatesOperation;
import com.digital14.operations.ReplaceStupidOperation;
import com.digital14.writers.FileWriter;
import com.digital14.writers.StringWriter;
import com.digital14.writers.Writer;

/**
 * @author amrmagdy
 *
 */
class WriterBuilderTest {
	
	String filePath = "file.txt";

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

	/**
	 * Test method for {@link com.digital14.builder.WriterBuilder#buildWriter(com.digital14.writers.Writer, com.digital14.operations.Operation[])}.
	 * @throws Exception 
	 */
	@Test
	void testBuildWriter() throws Exception {
		String expected, actual;
		expected = "this is really really s*****!!!";
		StringWriter writer = new StringWriter();
		Operation[] operations = {new ReplaceStupidOperation(), new ReadLowerCaseOperation()};
		WriterBuilder.buildWriter(writer, operations);
		writer.executeWriter("This is really really stupid!!!");
		actual= writer.read().get(0);
		assertEquals(expected, actual);			
	}
	
	@Test
	void testBuildWriterWithEmptyOpeartions() throws Exception {
		String expected, actual;
		expected = "This is really really stupid!!!";
		StringWriter writer = new StringWriter();
		Operation[] operations = {};
		WriterBuilder.buildWriter(writer, operations);
		writer.executeWriter("This is really really stupid!!!");
		actual= writer.read().get(0);
		assertEquals(expected, actual);			
	}
	
	@Test
	void testBuildWriterWithNoOperation() throws Exception {
		String expected, actual;
		expected = "This is really really stupid!!!";
		StringWriter writer = new StringWriter();
		WriterBuilder.buildWriter(writer);
		writer.executeWriter("This is really really stupid!!!");
		actual= writer.read().get(0);
		assertEquals(expected, actual);			
	}
	
	@Test
	void testBuildWriterWithNullOperation() throws Exception {
		String expected, actual;
		expected = "This is really really stupid!!!";
		StringWriter writer = new StringWriter();
		WriterBuilder.buildWriter(writer, null);
		writer.executeWriter("This is really really stupid!!!");
		actual= writer.read().get(0);
		assertEquals(expected, actual);			
	}
	
	@Test
	void testBuildWriterWithNullWriter() throws Exception {
		Operation[] operations = {new ReplaceStupidOperation(), new ReadLowerCaseOperation()};
		WriterBuilder.buildWriter(null, operations);		
	}

	/**
	 * Test method for {@link com.digital14.builder.WriterBuilder#addOperation(com.digital14.writers.Writer, com.digital14.operations.Operation)}.
	 * @throws Exception 
	 */
	@Test
	void testAddOperation() throws Exception {
		String expected, actual;
		expected = "first input";
		StringWriter writer = new StringWriter();
		WriterBuilder.buildWriter(writer, null);
		WriterBuilder.addOperation(writer, new ReadLowerCaseOperation());
		writer.executeWriter("First input");
		actual = writer.read().get(0);
		assertEquals(expected, actual);
	}
	
	@Test
	void testAddOperationWithManyOperations() throws Exception {
		String expected, actual;
		expected = "first input";
		StringWriter writer = new StringWriter();
		WriterBuilder.buildWriter(writer);
		WriterBuilder.addOperation(writer, new ReadLowerCaseOperation());
		WriterBuilder.addOperation(writer, new RemoveDuplicatesOperation());
		writer.executeWriter("First input input");
		actual = writer.read().get(0);
		assertEquals(expected, actual);
	}
	
	@Test
	void testAddOperationWithNull() throws Exception {
		String expected, actual;
		expected = "First input";
		StringWriter writer = new StringWriter();
		WriterBuilder.buildWriter(writer);
		WriterBuilder.addOperation(writer, null);
		writer.executeWriter("First input");
		actual = writer.read().get(0);
		assertEquals(expected, actual);
	}
	
	@Test
	void testAddOperationWithNullWriter() throws Exception {
		String expected, actual;
		expected = "First input";
		StringWriter writer = new StringWriter();
		WriterBuilder.buildWriter(writer);
		WriterBuilder.addOperation(null, null);
		writer.executeWriter("First input");
		actual = writer.read().get(0);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link com.digital14.builder.WriterBuilder#removeOperation(com.digital14.writers.Writer, com.digital14.operations.Operation)}.
	 * @throws Exception 
	 */
	@Test
	void testRemoveOperationWriterOperationType() throws Exception {
		String expected, actual;
		expected = "First input";
		Writer writer = new StringWriter();
		WriterBuilder.addOperation(writer, new ReadLowerCaseOperation());
		WriterBuilder.removeOperation(writer, OperationType.READ_LOWER);
		writer.executeWriter("First input");
		actual = writer.read().get(0);
		assertEquals(expected, actual);
	}
	
	
	@Test
	void testRemoveOperationWriterOperationWithManyOperations() throws Exception {
		String expected, actual;
		expected = "First s***** input";
		Writer writer = new StringWriter();
		WriterBuilder.addOperation(writer,new ReplaceStupidOperation());
		WriterBuilder.addOperation(writer,new ReadLowerCaseOperation());
		WriterBuilder.removeOperation(writer, OperationType.READ_LOWER);
		writer.executeWriter("First stupid input");
		actual = writer.read().get(0);
		assertEquals(expected, actual);
	}
	
	@Test
	void testRemoveOperationWriterOperationWithNoOperations() throws Exception {
		String expected, actual;
		expected = "First input";	
		FileWriter writer = new FileWriter(filePath);
		WriterBuilder.removeOperation(writer,OperationType.READ_LOWER);
		writer.executeWriter("First input");
		actual = writer.read().get(0);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link com.digital14.builder.WriterBuilder#removeOperation(com.digital14.writers.Writer, int)}.
	 */
	@Test
	void testRemoveOperationWriterInt() throws Exception {
		String expected, actual;
		expected = "First input";
		FileWriter writer = new FileWriter(filePath);
		WriterBuilder.addOperation(writer,new ReadLowerCaseOperation());
		WriterBuilder.removeOperation(writer, 0);
		writer.executeWriter("First input");
		actual = writer.read().get(0);
		assertEquals(expected, actual);
	}
	
	@Test
	void testRemoveOperationWriterIntWithManyOperations() throws Exception {
		String expected, actual;
		expected = "First s***** input";
		FileWriter writer = new FileWriter(filePath);
		WriterBuilder.addOperation(writer, new ReplaceStupidOperation());
		WriterBuilder.addOperation(writer, new ReadLowerCaseOperation());
		WriterBuilder.removeOperation(writer, 1);
		writer.executeWriter("First stupid input");
		actual = writer.read().get(0);
		assertEquals(expected, actual);
	}
	
	@Test
	void testRemoveOperationWriterIntWithNoOperations() throws Exception {
		String expected, actual;
		expected = "First input";	
		FileWriter writer = new FileWriter(filePath);
		WriterBuilder.removeOperation(writer,1);
		writer.executeWriter("First input");
		actual = writer.read().get(0);
		assertEquals(expected, actual);
	}

}
