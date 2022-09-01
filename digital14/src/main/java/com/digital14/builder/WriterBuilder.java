package com.digital14.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.digital14.operations.Operation;
import com.digital14.operations.OperationType;
import com.digital14.writers.Writer;

public class WriterBuilder {
	
	public static void buildWriter(Writer writer, Operation... operations) {
		Optional.ofNullable(writer).ifPresent(w -> {
			List<Operation> operationsList = new ArrayList<Operation>();
			Optional.ofNullable(operations).ifPresent(ops ->operationsList.addAll(Arrays.asList(ops)));			
			w.setOperations(operationsList);
		});
	}
	
	public static void addOperation(Writer writer, Operation operation) {
		Optional.ofNullable(writer).ifPresent(w -> w.append(operation));
	}
	
	public static void removeOperation(Writer writer, OperationType operation) {
		Optional.ofNullable(writer).ifPresent(w -> w.removeOperation(operation));
	}
	
	public static void removeOperation(Writer writer, int operationIndex) {
		Optional.ofNullable(writer).ifPresent(w -> w.removeOperation(operationIndex));
	}
	
	

}
