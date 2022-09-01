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
class ReadUpperCaseOperationTest {
	
	ReadUpperCaseOperation readUpperCaseOperation;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		readUpperCaseOperation = new ReadUpperCaseOperation();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.digital14.operations.ReadUpperCaseOperation#getOperationType()}.
	 */
	@Test
	void testGetOperationType() {
		String expected = OperationType.READ_UPPER.name();
		String actual = readUpperCaseOperation.getOperationType().name();
		
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link com.digital14.operations.ReadUpperCaseOperation#execute(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	void testExecute() throws Exception {
		String expected = "THIS IS TEST";
		String actual;
		actual= readUpperCaseOperation.execute("This is Test");
		assertEquals(expected, actual);
	}
	
	@Test
	void testExecuteWithCapitalString() throws Exception {
		String expected = "THIS IS TEST";
		String actual;
		actual = readUpperCaseOperation.execute("THIS IS TEST");
		assertEquals(expected, actual);
	}
	
	@Test
	void testExecuteWithSmallString() throws Exception {
		String expected = "THIS IS TEST";
		String actual;		
		actual = readUpperCaseOperation.execute("this is test");
		assertEquals(expected, actual);
	}
	
	@Test
	void testExecuteWithNull() throws Exception {
		try {
			readUpperCaseOperation.execute(null);
		}catch(Exception ex) {
			String expectedExceptionMessage = "Can't execute action for null value";
			assertEquals(expectedExceptionMessage, ex.getMessage());
		}
	}

}
