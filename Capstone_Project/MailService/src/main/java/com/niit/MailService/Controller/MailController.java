package com.niit.MailService.Controller;

import com.niit.MailService.Model.MailStructure;
import com.niit.MailService.Service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;


    @PostMapping("/send/{mail}")
    public String sendMail(@PathVariable String mail, @RequestBody MailStructure mailStructure){

        mailService.sendMail(mail,mailStructure);
        return "Successfully sent the mail !!";
    }

}
