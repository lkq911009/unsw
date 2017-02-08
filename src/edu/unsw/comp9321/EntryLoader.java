package edu.unsw.comp9321;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.logging.Logger;

import javax.servlet.ServletException;

public class EntryLoader {

	public static void main(String[] args) throws Exception {
		EntryParser parser;
		InputStream is = new FileInputStream("F:\\java\\workshop\\9321ass2\\dblp.xml");
		parser = new EntryParser(is);
	}

}
