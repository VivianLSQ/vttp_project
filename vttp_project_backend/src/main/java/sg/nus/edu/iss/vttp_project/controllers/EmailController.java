package sg.nus.edu.iss.vttp_project.controllers;

import java.util.Properties;

import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.nus.edu.iss.vttp_project.utility.EmailUtil;

@Controller
@RequestMapping(path="/api") //to set in angular 
public class EmailController {
    //To send email 
        //For account setup confirmation
        //Support Portal - similar to Railway

    @Autowired
    private static EmailUtil email; 

    public static void main(String[] args) {
		
	    System.out.println("Test email");
		
	    String smtpHostServer = "smtp.example.com";
	    String emailId = "email_me@example.com";
	    
	    Properties props = System.getProperties();

	    props.put("mail.smtp.host", smtpHostServer);

	    Session session = Session.getInstance(props, null);
	    
	    email.sendEmail(session, emailId,"SimpleEmail Testing Subject", "SimpleEmail Testing Body");
	}
    
}
