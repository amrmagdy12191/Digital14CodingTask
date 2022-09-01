package com.digital14.operations;

public class ReadLowerCaseOperation implements Operation {
	
	@Override
	public OperationType getOperationType() {
		return OperationType.READ_LOWER;
	}

	@Override
	public String execute(String str) throws Exception {
		if(str == null)
			throw new Exception("Can't execute action for null value");
		return str.toLowerCase();
	}

}
