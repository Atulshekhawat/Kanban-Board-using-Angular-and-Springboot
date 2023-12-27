package com.niit.MailService.Controller;


import com.niit.MailService.Model.EmailDetails;
import com.niit.MailService.Service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    MailService emailService;
    @PostMapping("/sendEmail")
    public String sendMail(@RequestBody EmailDetails emailDetails) {
        return emailService.sendEmail(emailDetails);
    }

}
