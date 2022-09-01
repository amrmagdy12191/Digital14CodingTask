package com.digital14.operations;

public class ReplaceStupidOperation implements Operation {
	
	private static final String stupidWord = "stupid";
	private static final String replacedWord = "s*****";
	

	@Override
	public OperationType getOperationType() {
		return OperationType.REPLACE_STUPID;
	}

	@Override
	public String execute(String str) throws Exception {
		if(str == null)
			throw new Exception("Can't execute action for null value");
		
		return str.replaceAll("\\b"+ stupidWord + "\\b", replacedWord);
	}

}
