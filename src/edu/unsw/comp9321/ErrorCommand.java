package edu.unsw.comp9321;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Forwards to an error message
 * @author  yunki
 */
public class ErrorCommand implements Command {
	
	/** Creates a new instance of ErrorCommand */
	public ErrorCommand() {
	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {

     //TODO: set the response content type to "text/html" here
     //TODO: You'll have to use getWriter() to output some HTML texts 
     //TODO: close the output stream

		response.setContentType("text/html");// from response, set content type
		PrintWriter out = response.getWriter();// from response, get output writer
		out.println("<b>	There is no such an operator. Please try again!</b>");
	    return null;

	}
	
}
