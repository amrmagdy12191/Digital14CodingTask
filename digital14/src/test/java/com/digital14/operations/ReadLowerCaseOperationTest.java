/**
 * 
 */
package com.digital14.operations;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author amrmagdy
 *
 */
class ReadLowerCaseOperationTest {
	
	ReadLowerCaseOperation readLowerCaseOperation;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		readLowerCaseOperation = new ReadLowerCaseOperation();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.digital14.operations.ReadLowerCaseOperation#getOperationType()}.
	 */
	@Test
	void testGetOperationType() {
		String expected = OperationType.READ_LOWER.name();
		String actual = readLowerCaseOperation.getOperationType().name();
		
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link com.digital14.operations.ReadLowerCaseOperation#execute(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	void testExecute() throws Exception {
		String expected = "this is test";
		String actual;
		actual= readLowerCaseOperation.execute("This is Test");
		assertEquals(expected, actual);
	}
	
	
	@Test
	void testExecuteWithCapitalString() throws Exception {
		String expected = "this is test";
		String actual;
		actual = readLowerCaseOperation.execute("THIS IS TEST");
		assertEquals(expected, actual);
	}
	
	@Test
	void testExecuteWithSmallString() throws Exception {
		String expected = "this is test";
		String actual;		
		actual = readLowerCaseOperation.execute("this is test");
		assertEquals(expected, actual);
	}
	
	@Test
	void testExecuteWithNull() throws Exception {
		try {
			readLowerCaseOperation.execute(null);
		}catch(Exception ex) {
			String expectedExceptionMessage = "Can't execute action for null value";
			assertEquals(expectedExceptionMessage, ex.getMessage());
		}
	}
}