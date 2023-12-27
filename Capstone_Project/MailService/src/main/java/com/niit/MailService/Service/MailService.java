package com.niit.MailService.Service;

import com.niit.MailService.Model.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}") private String sender;

    public String sendEmail(EmailDetails emailDetails) {
        try {
            // creating a simple mail message
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            //Setting up necessary details
            simpleMailMessage.setFrom(sender);
            simpleMailMessage.setTo(emailDetails.getRecipient());
            simpleMailMessage.setText(emailDetails.getMsgBody());
            simpleMailMessage.setSubject(emailDetails.getSubject());

            // Sending the mail
            javaMailSender.send(simpleMailMessage);
            return "Mail sent succesfully...";
        }
        // Catch block to handle the exceptions
        catch (Exception e) {
            System.out.println(e);
            return "Error while Sending Mail";

        }
    }

}
