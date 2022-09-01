package com.digital14.writers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.ResourceUtils;

public class FileWriter extends Writer{
	String filePath;
	
	public FileWriter(String filePath) {
		this.filePath = filePath;
	}

	@Override
	protected void write(String str) throws Exception {    
	    	File fileWriter = new File(filePath);
	    	RandomAccessFile raf = new RandomAccessFile(fileWriter, "rw");
			raf.seek(fileWriter.length());
			raf.writeBytes(str);
		    raf.close();	
	}

	@Override
	public List<String> read() throws Exception{
		File file = ResourceUtils.getFile(filePath);
		if(!file.canRead())
			return new ArrayList<>();
		
		FileReader fileReader = new FileReader(file);
		List<String> result = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(fileReader);
		reader.lines().forEach(l -> result.add(l));
		fileReader.close();
		reader.close();
		return result;
	}

	@Override
	public void close() {
		setClosed(true);		
	}
	


}
