package com.digital14.operations;

public interface Operation {
	 OperationType getOperationType();
	 String execute(String str) throws Exception;

}
