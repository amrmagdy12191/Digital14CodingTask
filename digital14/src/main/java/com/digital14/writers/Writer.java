package com.digital14.writers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.digital14.operations.Operation;
import com.digital14.operations.OperationType;

public abstract class Writer {
	List<Operation> operations = new ArrayList<Operation>();
	private boolean closed;
	
	
	/**
	 * apply operations then Write string
	 * @param str
	 */
	public void executeWriter(String str) throws Exception{
		if(closed)
			return;
		
		str = executeOperations(str);
		write(str);
	}
	
	protected abstract void write(String str) throws Exception;
	public abstract List<String> read() throws Exception;
	public abstract void close();

	/**
	 * add desired operation to Writer existing Operations
	 * @param operation
	 */
	public Writer append(Operation operation) {
		Optional.ofNullable(operation).ifPresent(op -> {
			operations = Optional.ofNullable(operations).orElse(new ArrayList<>());
			operations.add(operation);
		});		
		return this;
	}
	
	/**
	 * remove operation from Writer existing Operations by operationType
	 * @param operationType
	 */
	public void removeOperation(OperationType operationType) {
		Optional.ofNullable(operations).get().removeIf(o ->operationType.name().equals(o.getOperationType().name()));
	}
	
	/**
	 * remove operation from Writer existing Operations by operation order in the existing Operation List
	 * @param operationType
	 */
	public void removeOperation(int operationIndex) {
		if(operations != null && operationIndex < operations.size())
			operations.remove(operationIndex);
	}
	
	/**
	 * execute existing operation on String
	 * @param str
	 * @return
	 */
	protected String executeOperations(String str) throws Exception {
		if(str == null)
			throw new Exception("Can't execute action for null value");
		
		String result = str;
		if(operations != null) {
			for(Operation operation: operations) {
				result = operation.execute(result);
			}
		}		
		return result;
	}
	
	/**
	 * set custom selected operations during runtime
	 * @param operations
	 */
	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	protected boolean isClosed() {
		return closed;
	}

	protected void setClosed(boolean closed) {
		this.closed = closed;
	}	

}
