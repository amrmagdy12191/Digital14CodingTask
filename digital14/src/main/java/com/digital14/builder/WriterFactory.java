package com.digital14.builder;

import com.digital14.operations.Operation;
import com.digital14.operations.ReadLowerCaseOperation;
import com.digital14.operations.ReadUpperCaseOperation;
import com.digital14.operations.RemoveDuplicatesOperation;
import com.digital14.operations.ReplaceStupidOperation;
import com.digital14.writers.FileWriter;
import com.digital14.writers.StringWriter;

public class WriterFactory {
	
	public static StringWriter initiateStringWriterWithRemoveStupidLower(){
		StringWriter stringWriter = new StringWriter();
		Operation[] operations = {new ReplaceStupidOperation(), new ReadLowerCaseOperation()};
		
		WriterBuilder.buildWriter(stringWriter, operations);
		
		return stringWriter;
	}
	
	public static FileWriter initFileWriterWithRemoveDuplicatesUpper(){
		FileWriter fileWriter = new FileWriter("file.txt");
		
		WriterBuilder.buildWriter(fileWriter);
		fileWriter.append(new RemoveDuplicatesOperation())
				  .append(new ReadUpperCaseOperation());
		
		return fileWriter;
	}

}
