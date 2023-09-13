package sg.nus.edu.iss.vttp_project.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    JavaMailSender mailSender; 

    public void sendConfirmationMail(String to, String subject, String mailBody){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setTo(subject); 
        message.setTo(mailBody); 

        mailSender.send(message); 

    }

    
}
