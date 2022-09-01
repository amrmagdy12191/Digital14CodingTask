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
class RemoveDuplicatesOperationTest {
	
	RemoveDuplicatesOperation removeDuplicatesOperation;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		removeDuplicatesOperation = new RemoveDuplicatesOperation();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.digital14.operations.RemoveDuplicatesOperation#getOperationType()}.
	 */
	@Test
	void testGetOperationType() {
		String expected = OperationType.REMOVE_DUPLICATE.name();
		String actual = removeDuplicatesOperation.getOperationType().name();
		
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link com.digital14.operations.RemoveDuplicatesOperation#execute(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	void testExecute() throws Exception {
		String expected = "this is test";
		String actual;
		actual= removeDuplicatesOperation.execute("this is is test");
		assertEquals(expected, actual);
	}
	
	@Test
	void testExecuteWithManyDuplicates() throws Exception {
		String expected = "this is test";
		String actual;
		actual = removeDuplicatesOperation.execute("this is is test test");
		assertEquals(expected, actual);
	}
	
	@Test
	void testExecuteWithTripleWords() throws Exception {
		String expected = "this is test";
		String actual;
		actual = removeDuplicatesOperation.execute("this is is is test");
		assertEquals(expected, actual);
	}
	
	@Test
	void testExecutewithNoDuplicates() throws Exception {
		String expected = "this is test";
		String actual;
		actual = removeDuplicatesOperation.execute("this is test");
		assertEquals(expected, actual);
	}
	
	@Test
	void testExecuteWithNull() throws Exception {
		try {
			removeDuplicatesOperation.execute(null);
		}catch(Exception ex) {
			String expectedExceptionMessage = "Can't execute action for null value";
			assertEquals(expectedExceptionMessage, ex.getMessage());
		}
	
	}

}
