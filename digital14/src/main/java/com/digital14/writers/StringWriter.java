package com.digital14.writers;

import java.util.ArrayList;
import java.util.List;

public class StringWriter extends Writer {
	public List<String> cachedStrings = new ArrayList<String>();

	@Override
	protected void write(String str) throws Exception{
		if(str == null)
			return;
		cachedStrings.add(str);
		System.out.println(str);
	}

	@Override
	public List<String> read() throws Exception{
		return cachedStrings;
	}

	@Override
	public void close() {
		setClosed(true);
		
	}

}
