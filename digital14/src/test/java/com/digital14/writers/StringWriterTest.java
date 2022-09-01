/**
 * 
 */
package com.digital14.writers;

import static org.junit.jupiter.api.Assertions.*;

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

/**
 * @author amrmagdy
 *
 */
class StringWriterTest {
	
	StringWriter stringWriter;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		stringWriter = new StringWriter();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}
	
	/**
	 * Test method for {@link com.digital14.writers.StringWriter#write(java.lang.String)}.
	 */
	@Test
	void testWrite() throws Exception {
		String expected, actual;
		expected = "First input";
		stringWriter.write("First input");
		actual = stringWriter.read().get(0);
		assertEquals(expected, actual);
	}
	
	@Test
	@DisplayName("test write many entries")
	void testWriteWithManyentriess() throws Exception {
		String expectedFirstLine = "First input";
		String expectedSecondLine = "second input";
		String actualFirstLine, actualSecondLine;
		
		stringWriter.write("First input");
		stringWriter.write("second input");
		
		actualFirstLine = stringWriter.read().get(0);
		actualSecondLine = stringWriter.read().get(1);
		
		assertEquals(expectedFirstLine, actualFirstLine);
		assertEquals(expectedSecondLine, actualSecondLine);
	}
	
	@Test
	void testWriteWithEmpty() throws Exception {
		String expected, actual;
		expected = "";
		stringWriter.write("");
		actual = stringWriter.read().get(0);
		assertEquals(expected, actual);
	}
	
	@Test
	@DisplayName("test write null")
	void testWriteWithNull() {
		int expected, actual;
		try {
			expected = 0;
			stringWriter.write(null);
			actual = stringWriter.read().size();
			assertEquals(expected, actual);
		} catch (Exception e) {
			String expectedExceptionMessage = "Can't execute action for null value";
			assertEquals(expectedExceptionMessage, e.getMessage());
		}
	}

	/**
	 * Test method for {@link com.digital14.writers.StringWriter#read()}.
	 */
	@Test
	void testRead() throws Exception {
		String expected, actual;
		expected = "First input";
		stringWriter.write("First input");
		actual = stringWriter.read().get(0);
		assertEquals(expected, actual);
	}
	
	@Test
	void testReadFromEmpty() throws Exception {
		boolean expected;
		List<String> result;
		expected = true;
		result = stringWriter.read();
		assertEquals(expected, result.isEmpty());
	}

	/**
	 * Test method for {@link com.digital14.writers.StringWriter#close()}.
	 */
	@Test
	void testClose() throws Exception {
		boolean expected, actual;
		expected = true;
		stringWriter.write("First input");
		stringWriter.close();
		actual = stringWriter.isClosed();
		assertEquals(expected, actual);
	}
	
	@Test
	void testCloseWithoutWrite() throws Exception {
		int expected, actual;
		expected = 0;
		stringWriter.close();
		actual = stringWriter.read().size();
		assertEquals(expected, actual);
	}
	
	/**
	 * Test method for {@link com.digital14.writers.Writer#executeWriter(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	void testExecuteWriter() throws Exception {
		String expected, actual;
		expected = "first input";
		stringWriter.append(new ReadLowerCaseOperation());
		stringWriter.executeWriter("First input");
		actual = stringWriter.read().get(0);
		assertEquals(expected, actual);
	}
	
	@Test
	void testExecuteWriterWithNoOperations() throws Exception {
		String expected, actual;
		expected = "First input";
		stringWriter.executeWriter("First input");
		actual = stringWriter.read().get(0);
		assertEquals(expected, actual);
	}
	
	@Test
	void testExecuteWriterWithClosedWriter() throws Exception {
		String expected, actual;
		expected = "first input";
		
		stringWriter.append(new ReadLowerCaseOperation());
		stringWriter.executeWriter("First input");
		stringWriter.close();
		stringWriter.executeWriter("First input input");
		actual = stringWriter.read().get(0);
		assertEquals(expected, actual);
		
		int expectedSize = 1;
		assertEquals(expectedSize, stringWriter.read().size());
	}

	/**
	 * Test method for {@link com.digital14.writers.Writer#append(com.digital14.operations.Operation)}.
	 * @throws Exception 
	 */
	@Test
	void testAppend() throws Exception {
		String expected, actual;
		expected = "first input";
		stringWriter.append(new ReadLowerCaseOperation());
		stringWriter.executeWriter("First input");
		actual = stringWriter.read().get(0);
		assertEquals(expected, actual);
	}
	
	@Test
	void testAppendWithManyOperations() throws Exception {
		String expected, actual;
		expected = "first input";
		stringWriter.append(new ReadLowerCaseOperation());
		stringWriter.append(new RemoveDuplicatesOperation());
		stringWriter.executeWriter("First input input");
		actual = stringWriter.read().get(0);
		assertEquals(expected, actual);
	}
	
	@Test
	void testAppendWithNull() throws Exception {
		String expected, actual;
		expected = "First input";
		stringWriter.append(null);
		stringWriter.executeWriter("First input");
		actual = stringWriter.read().get(0);
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
		stringWriter.append(new ReadLowerCaseOperation());
		stringWriter.removeOperation(OperationType.READ_LOWER);
		stringWriter.executeWriter("First input");
		actual = stringWriter.read().get(0);
		assertEquals(expected, actual);
	}
	
	@Test
	void testRemoveOperationOperationTypeWithManyOperations() throws Exception {
		String expected, actual;
		expected = "First s***** input";
		stringWriter.append(new ReplaceStupidOperation());
		stringWriter.append(new ReadLowerCaseOperation());
		stringWriter.removeOperation(OperationType.READ_LOWER);
		stringWriter.executeWriter("First stupid input");
		actual = stringWriter.read().get(0);
		assertEquals(expected, actual);
	}
	
	@Test
	void testRemoveOperationOperationTypeWithNoOperations() throws Exception {
		String expected, actual;
		expected = "First input";	
		stringWriter.removeOperation(OperationType.READ_LOWER);
		stringWriter.executeWriter("First input");
		actual = stringWriter.read().get(0);
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
		stringWriter.append(new ReadLowerCaseOperation());
		stringWriter.removeOperation(0);
		stringWriter.executeWriter("First input");
		actual = stringWriter.read().get(0);
		assertEquals(expected, actual);
	}
	
	@Test
	void testRemoveOperationIntWithManyOperations() throws Exception {
		String expected, actual;
		expected = "First s***** input";
		stringWriter.append(new ReplaceStupidOperation());
		stringWriter.append(new ReadLowerCaseOperation());
		stringWriter.removeOperation(1);
		stringWriter.executeWriter("First stupid input");
		actual = stringWriter.read().get(0);
		assertEquals(expected, actual);
	}
	
	@Test
	void testRemoveOperationInteWithNoOperations() throws Exception {
		String expected, actual;
		expected = "First input";	
		stringWriter.removeOperation(1);
		stringWriter.executeWriter("First input");
		actual = stringWriter.read().get(0);
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
		stringWriter.append(new ReplaceStupidOperation());
		stringWriter.append(new ReadLowerCaseOperation());
		stringWriter.removeOperation(1);
		actual = stringWriter.executeOperations("First stupid input");
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
		stringWriter.setOperations(operations);
		stringWriter.executeWriter("First stupid input");
		actual = stringWriter.read().get(0);
		assertEquals(expected, actual);
		
	}
	
	@Test
	void testSetOperationsWithEmptyOperations() throws Exception {
		String expected, actual;
		expected = "First stupid input";
		List<Operation> operations = new ArrayList<Operation>();
		stringWriter.setOperations(operations);
		stringWriter.executeWriter("First stupid input");
		actual = stringWriter.read().get(0);
		assertEquals(expected, actual);
		
	}
	
	@Test
	void testSetOperationsWithNull() throws Exception {
		String expected, actual;
		expected = "First stupid input";
		stringWriter.setOperations(null);
		stringWriter.executeWriter("First stupid input");
		actual = stringWriter.read().get(0);
		assertEquals(expected, actual);
		
	}

}
