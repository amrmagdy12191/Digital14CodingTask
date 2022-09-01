package com.digital14.operations;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReplaceStupidOperationTest {
	
	ReplaceStupidOperation replaceStupidOperation;

	@BeforeEach
	void setUp() throws Exception {
		replaceStupidOperation = new ReplaceStupidOperation();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetOperationType() {
		String expected = OperationType.REPLACE_STUPID.name();
		String actual = replaceStupidOperation.getOperationType().name();
		
		assertEquals(expected, actual);
	}

	@Test
	void testExecute() throws Exception {
		String expected = "This is really s*****!!!";
		String actual;
		actual= replaceStupidOperation.execute("This is really stupid!!!");
		assertEquals(expected, actual);		
	}
	
	@Test
	void testExecuteWithTwoStupid() throws Exception {
		String expected = "This is really s*****!!!";
		String actual;
		actual = replaceStupidOperation.execute("This is really stupid stupid!!!");
		expected = "This is really s***** s*****!!!";
		assertEquals(expected, actual);
	}
	
	@Test
	void testExecuteWithManyStupid() throws Exception {
		String expected = "This is really s*****!!!";
		String actual;
		actual = replaceStupidOperation.execute("This is really stupid more than stupid stupid!!!");
		expected = "This is really s***** more than s***** s*****!!!";
		assertEquals(expected, actual);
	}
	
	@Test
	void testExecuteWithNoStupid() throws Exception {
		String expected = "This is really s*****!!!";
		String actual;
		actual = replaceStupidOperation.execute("This is smart only!!");
		expected = "This is smart only!!";
		assertEquals(expected, actual);
	}
	
	@Test
	void testExecuteWithNull() throws Exception {
		try {
			replaceStupidOperation.execute(null);
		}catch(Exception ex) {
			String expectedExceptionMessage = "Can't execute action for null value";
			assertEquals(expectedExceptionMessage, ex.getMessage());
		}
	}

}
