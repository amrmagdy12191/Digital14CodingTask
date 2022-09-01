package com.digital14;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.digital14.builder.WriterFactory;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		try {
			WriterFactory.initFileWriterWithRemoveDuplicatesUpper().executeWriter("This is is it");
			WriterFactory.initiateStringWriterWithRemoveStupidLower().executeWriter("This is really really stupid!!!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
