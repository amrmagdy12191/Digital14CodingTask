package com.digital14.operations;

public class ReadUpperCaseOperation implements Operation {

	@Override
	public OperationType getOperationType() {
		return OperationType.READ_UPPER;
	}

	@Override
	public String execute(String str) throws Exception {
		if(str == null)
			throw new Exception("Can't execute action for null value");
		return str.toUpperCase();
	}

}
