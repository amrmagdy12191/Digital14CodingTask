package com.digital14.operations;

public class RemoveDuplicatesOperation implements Operation {

	@Override
	public OperationType getOperationType() {
		return OperationType.REMOVE_DUPLICATE;
	}

	@Override
	public String execute(String str) throws Exception {
		if(str == null)
			throw new Exception("Can't execute action for null value");
		
		StringBuilder stringBuilder = new StringBuilder();
		String[] words = str.split("\\s+");
		
		String previous = "";
		for(String word:words) {
			if(word.equals(previous))
				continue;
			
			stringBuilder.append(word).append(" ");
			previous = word;
		}
		stringBuilder.deleteCharAt(stringBuilder.length() -1);
		
		return stringBuilder.toString();
	}

}
