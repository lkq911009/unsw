package edu.unsw.comp9321;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory; 

import edu.unsw.comp9321.jdbc.DBConnectionFactory;



public class EntryParser implements ContentHandler {
	Logger logger = Logger.getLogger(this.getClass().getName());
	private Connection connection;	
	
	private enum TagType {article, inproceedings, proceedings, book, incollection,
		author, editor, title, booktitle, pages, year, journal,
		volume, number, month, publisher, isbn, dblp, unidentified};
	
	private TagType openingTag = TagType.unidentified, closingTag = TagType.unidentified;	
	private StringBuilder builder; /*This is a string buffer collecting characters*/
	private TagType currTag;
	PreparedStatement stmnt;
	String title = "UNDEFINE", author = "UNDEFINE", editor = "UNDEFINE", pages = "UNDEFINE", bookTitle = "UNDEFINE"
			, publisher = "UNDEFINE", journal = "UNDEFINE", volume = "UNDEFINE", number = "UNDEFINE", month = "UNDEFINE", isbn = "UNDEFINE";
	int year = 0;
	private int randomNumber = 300;
	private int randomCountDown = new Random().nextInt(randomNumber);
//	private int randomCountDown = 0;
	private boolean startRecord = false;
	private int count = 0;
	
	
	
	public EntryParser(InputStream xmlFile) throws Exception{
		connection = DBConnectionFactory.getConnection();
		logger.info("Got connection");

		try {
			InputSource xmlSource = new InputSource(xmlFile);
	        
			SAXParserFactory spf = SAXParserFactory.newInstance();
			spf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, false);
			spf.setNamespaceAware(true);
		    SAXParser saxParser = spf.newSAXParser();
		    XMLReader xmlReader = saxParser.getXMLReader();
		    xmlReader.setContentHandler(this);
		    xmlReader.parse(xmlSource);

			
			
			
			
			
			
//			XMLReader parser = XMLReaderFactory.createXMLReader();
//	        
//	        parser.setContentHandler(this);
//	        parser.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, false);
//	        parser.parse(xmlSource);
	   }catch(Exception e){
		   logger.log(Level.SEVERE,"Failed to process XML file: {0} ",e);
		   throw new Exception(e);
	   }  
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		//logger.info("Adding characters to the buffer");
		if(startRecord){
			builder.append(new String(ch,start,length));
		}

	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("Parsing Ended");
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException{
		//logger.info("End Element :"+qName);

		try{
			closingTag = TagType.valueOf(TagType.class,qName);
		}catch(Exception e){
//			logger.warning("Could not find tagType for tag "+qName);
			closingTag = TagType.unidentified;
		}
		
			String content = null;
			if(startRecord){	
				content = builder.toString();
			}
		
		try{
			if(closingTag==currTag && startRecord){
//				logger.info("Added entry to list");
				stmnt.setString(1, title);
				stmnt.setString(2, author);
				stmnt.setString(3, editor);
				stmnt.setString(5, bookTitle);
				stmnt.setString(6, pages );
				stmnt.setInt(7, year);
				stmnt.setString(8, publisher);
				stmnt.setString(9, journal);
				stmnt.setString(10, volume);
				stmnt.setString(11, number);
				stmnt.setString(12, month);
				stmnt.setString(13, isbn);
				stmnt.setFloat(14, new Random().nextFloat() * 1000);
				stmnt.execute();
				stmnt.close();
				title = "UNDEFINE" ;author = "UNDEFINE"; editor="UNDEFINE";
				bookTitle="UNDEFINE";pages="UNDEFINE";year = 0;
				publisher="UNDEFINE";journal="UNDEFINE";volume="UNDEFINE";
				number = "UNDEFINE"; month="UNDEFINE";isbn="UNDEFINE";
				randomCountDown = new Random().nextInt(randomNumber) + 1;
				startRecord = false;
				count++;
				System.out.println("Output " + count + " entry.");
			}else if(startRecord){	
				
					switch(closingTag){
				case author:
					if(author == "UNDEFINE"){
						author = content;
					}else{
						author = author + ", " + content;
					}
					//logger.info("Author : " + content);
					break;
				case title:
					//logger.info("Title : " + content);
					title = content;
					break;
				case publisher:
					//logger.info("Publisher : " + content);
					publisher = content;
					break;
				case editor:
	//				logger.info("Editor : "+content);
					editor = content;
					break;
				case pages:
	//				logger.info("pages : "+content);
					pages = content;
					break;	
				case journal:
	//				logger.info("Journal : "+content);
					journal = content;
					break;
				case year:
	//				logger.info("Year : "+content);
					year = Integer.parseInt(content);
					break;
				case isbn:
	//				logger.info("ISBN : "+content);
					isbn = content;
					break;
				case booktitle:
	//				logger.info("booktitle : "+content);
					bookTitle = content;
					break;		
				case volume:
	//				logger.info("Volume : "+content);
					volume = content;
					break;
				case number:
	//				logger.info("number : "+content);
					number = content;
					break;	
				case month:
	//				logger.info("Month : "+content);
					month = content;
					break;
				case dblp:
	//				logger.info("Finished parsing all books");
					break;
				case unidentified:
	//				logger.warning("Closing tag not idenitifed");
					break;
				default:
					break;
				}
			
			}else if (closingTag==currTag){
				randomCountDown--;
			}
		} 
		catch (SQLException e){
			e.printStackTrace();
		}
//		logger.info("Done parsing "+qName);
		
	}

	@Override
	public void endPrefixMapping(String arg0) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void ignorableWhitespace(char[] arg0, int arg1, int arg2)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void processingInstruction(String arg0, String arg1)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDocumentLocator(Locator arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void skippedEntity(String arg0) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes att) throws SAXException {
//		logger.info("Started Element : "+qName);
		
		try{
			openingTag = TagType.valueOf(TagType.class,qName);
		}catch(Exception e){
//			logger.warning("Could not find tagType for tag "+qName);
			openingTag = TagType.unidentified;
		}
		
		String tagName = openingTag.toString();

		if(tagName.equals("article")||tagName.equals("book")||tagName.equals("inproceedings")||tagName.equals("proceedings")||
				tagName.equals("incollection")){
//			logger.info("Created new object");
			currTag = openingTag;
			
			if (randomCountDown == 0){
			
				try {
					stmnt = connection.prepareStatement("INSERT INTO ENTRIES (SELLER, TITLE, AUTHORS, EDITORS,TYPES, BOOKTITLE, PAGES, YEAR,"
									+ "PUBLISHER, JOURNAL, VOLUME, NUMBER, MONTH, ISBN, PRICE) VALUES ('seller1',?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
					stmnt.setString(4, tagName);
					startRecord = true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
//		logger.info("Created new buffer");
		if (startRecord){
			builder = new StringBuilder();
		}

	}

	@Override
	public void startPrefixMapping(String arg0, String arg1)
			throws SAXException {
		// TODO Auto-generated method stub

	}
	
	

}
