package edu.unsw.comp9321;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SmtAuthenticator extends Authenticator {
public SmtAuthenticator() {

    super();
}

@Override
public PasswordAuthentication getPasswordAuthentication() {
 String username = "booktopia2015@gmail.com";
 String password = "booktopia";
    if ((username != null) && (username.length() > 0) && (password != null) 
      && (password.length   () > 0)) {

        return new PasswordAuthentication(username, password);
    }

    return null;
}
}