package com.digital14.writers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.digital14.operations.Operation;
import com.digital14.operations.OperationType;
import com.digital14.operations.ReadLowerCaseOperation;
import com.digital14.operations.ReadUpperCaseOperation;
import com.digital14.operations.RemoveDuplicatesOperation;
import com.digital14.operations.ReplaceStupidOperation;

class FileWriterTest {
	FileWriter fileWriter;
	String filePath = "file.txt";

	@BeforeEach
	void setUp() throws Exception {
		initiateFile();	
	}

	@AfterEach
	void tearDown() throws Exception {
		Files.deleteIfExists(Paths.get(filePath));
	}

	@Test
	void testWrite() throws Exception {
		String expected, actual;
		expected = "First input";
		fileWriter.write("First input");
		actual = fileWriter.read().get(0);
		assertEquals(expected, actual);
	}
	
	@Test
	@DisplayName("test write many times in same line")
	void testWriteWithManyTimesSameLine() throws Exception {
		String expected, actual;
		expected = "First input, second input";
		fileWriter.write("First input");
		fileWriter.write(", second input");
		actual = fileWriter.read().get(0);
		assertEquals(expected, actual);
	}
	
	@Test
	@DisplayName("test write many lines")
	void testWriteWithManyLines() {
		try {
			String expectedFirstLine = "First input";
			String expectedSecondLine = "second input";
			String actualFirstLine, actualSecondLine;
			
			fileWriter.write("First input");
			fileWriter.write("\n");
			fileWriter.write("second input");
			
			actualFirstLine = fileWriter.read().get(0);
			actualSecondLine = fileWriter.read().get(1);
			
			assertEquals(expectedFirstLine, actualFirstLine);
			assertEquals(expectedSecondLine, actualSecondLine);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("test write null")
	void testWriteWithNull() {
		String expected, actual;
		try {
			expected = "First input, second input";
			fileWriter.write("First input");
			fileWriter.write(", second input");
			actual = fileWriter.read().get(0);
			assertEquals(expected, actual);
		} catch (Exception e) {
			String expectedExceptionMessage = "Can't execute action for null value";
			assertEquals(expectedExceptionMessage, e.getMessage());
		}
	}

	@Test
	void testRead() throws Exception {
		String expected, actual;
		expected = "First input";
		fileWriter.write("First input");
		actual = fileWriter.read().get(0);
		assertEquals(expected, actual);
	}
	
	@Test
	void testReadFromEmptyFile() throws Exception {
		boolean expected;
		List<String> result;
		expected = true;
		result = fileWriter.read();
		assertEquals(expected, result.isEmpty());
	}
	
	@Test
	void testReadFromNotExistFile() throws Exception {
		boolean expected;
		List<String> result;
		expected = true;
		result = fileWriter.read();
		assertEquals(expected, result.isEmpty());
	}

	@Test
	void testClose() throws Exception {
		boolean expected, actual;
		expected = true;
		fileWriter.write("First input");
		fileWriter.close();
		actual = fileWriter.isClosed();
		assertEquals(expected, actual);
	}
	
	@Test
	void testCloseNotExistingFile() throws Exception {
		int expected, actual;
		expected = 0;
		fileWriter.close();
		actual = fileWriter.read().size();
		assertEquals(expected, actual);
	}
	
	void initiateFile() throws IOException {
		Files.deleteIfExists(Paths.get(filePath));
		fileWriter = new FileWriter(filePath);	
	}
	
	/**
	 * Test method for {@link com.digital14.writers.Writer#executeWriter(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	void testExecuteWriter() throws Exception {
		String expected, actual;
		expected = "first input";
		fileWriter.append(new ReadLowerCaseOperation());
		fileWriter.executeWriter("First input");
		actual = fileWriter.read().get(0);
		assertEquals(expected, actual);
	}
	
	@Test
	void testExecuteWriterWithNoOperations() throws Exception {
		String expected, actual;
		expected = "First input";
		fileWriter.executeWriter("First input");
		actual = fileWriter.read().get(0);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link com.digital14.writers.Writer#append(com.digital14.operations.Operation)}.
	 * @throws Exception 
	 */
	@Test
	void testAppend() throws Exception {
		String expected, actual;
		expected = "first input";
		fileWriter.append(new ReadLowerCaseOperation());
		fileWriter.executeWriter("First input");
		actual = fileWriter.read().get(0);
		assertEquals(expected, actual);
	}
	
	@Test
	void testAppendWithManyOperations() throws Exception {
		String expected, actual;
		expected = "first input";
		fileWriter.append(new ReadLowerCaseOperation());
		fileWriter.append(new RemoveDuplicatesOperation());
		fileWriter.executeWriter("First input input");
		actual = fileWriter.read().get(0);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link com.digital14.writers.Writer#removeOperation(com.digital14.operations.OperationType)}.
	 * @throws Exception 
	 */
	@Test
	void testRemoveOperationOperationType() throws Exception {
		String expected, actual;
		expected = "First input";
		fileWriter.append(new ReadLowerCaseOperation());
		fileWriter.removeOperation(OperationType.READ_LOWER);
		fileWriter.executeWriter("First input");
		actual = fileWriter.read().get(0);
		assertEquals(expected, actual);
	}
	
	@Test
	void testRemoveOperationOperationTypeWithManyOperations() throws Exception {
		String expected, actual;
		expected = "First s***** input";
		fileWriter.append(new ReplaceStupidOperation());
		fileWriter.append(new ReadLowerCaseOperation());
		fileWriter.removeOperation(OperationType.READ_LOWER);
		fileWriter.executeWriter("First stupid input");
		actual = fileWriter.read().get(0);
		assertEquals(expected, actual);
	}
	
	@Test
	void testRemoveOperationOperationTypeWithNoOperations() throws Exception {
		String expected, actual;
		expected = "First input";	
		fileWriter.removeOperation(OperationType.READ_LOWER);
		fileWriter.executeWriter("First input");
		actual = fileWriter.read().get(0);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link com.digital14.writers.Writer#removeOperation(int)}.
	 * @throws Exception 
	 */
	@Test
	void testRemoveOperationInt() throws Exception {
		String expected, actual;
		expected = "First input";
		fileWriter.append(new ReadLowerCaseOperation());
		fileWriter.removeOperation(0);
		fileWriter.executeWriter("First input");
		actual = fileWriter.read().get(0);
		assertEquals(expected, actual);
	}
	
	@Test
	void testRemoveOperationIntWithManyOperations() throws Exception {
		String expected, actual;
		expected = "First s***** input";
		fileWriter.append(new ReplaceStupidOperation());
		fileWriter.append(new ReadLowerCaseOperation());
		fileWriter.removeOperation(1);
		fileWriter.executeWriter("First stupid input");
		actual = fileWriter.read().get(0);
		assertEquals(expected, actual);
	}
	
	@Test
	void testRemoveOperationInteWithNoOperations() throws Exception {
		String expected, actual;
		expected = "First input";	
		fileWriter.removeOperation(1);
		fileWriter.executeWriter("First input");
		actual = fileWriter.read().get(0);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link com.digital14.writers.Writer#executeOperations(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	void testExecuteOperations() throws Exception {
		String expected, actual;
		expected = "First s***** input";
		fileWriter.append(new ReplaceStupidOperation());
		fileWriter.append(new ReadLowerCaseOperation());
		fileWriter.removeOperation(1);
		actual = fileWriter.executeOperations("First stupid input");
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link com.digital14.writers.Writer#setOperations(java.util.List)}.
	 * @throws Exception 
	 */
	@Test
	void testSetOperations() throws Exception {
		String expected, actual;
		expected = "first s***** input";
		List<Operation> operations = new ArrayList<Operation>();
		operations.add(new ReplaceStupidOperation());
		operations.add(new ReadLowerCaseOperation());
		fileWriter.setOperations(operations);
		fileWriter.executeWriter("First stupid input");
		actual = fileWriter.read().get(0);
		assertEquals(expected, actual);
		
	}
	
	@Test
	void testSetOperationsWithEmptyOperations() throws Exception {
		String expected, actual;
		expected = "First stupid input";
		List<Operation> operations = new ArrayList<Operation>();
		fileWriter.setOperations(operations);
		fileWriter.executeWriter("First stupid input");
		actual = fileWriter.read().get(0);
		assertEquals(expected, actual);
		
	}
	
	@Test
	void testSetOperationsWithNull() throws Exception {
		String expected, actual;
		expected = "First stupid input";
		fileWriter.setOperations(null);
		fileWriter.executeWriter("First stupid input");
		actual = fileWriter.read().get(0);
		assertEquals(expected, actual);
		
	}

}
