package com.likithalajangi.springBootSES.Controllers;

import com.likithalajangi.springBootSES.model.notificationRequest;
import com.likithalajangi.springBootSES.model.sendHTMLEmailRequest;
import com.likithalajangi.springBootSES.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class EmailRestController {
    @Autowired
    EmailService emailService;
    @PostMapping("/sendNotification")
    public String sendNotification(@RequestBody notificationRequest notificationrequest){
        emailService.simpleSend(notificationrequest.getEmail(),notificationrequest.getSubject(),notificationrequest.getMessage());

        return "Messase Queued";
    }


    @PostMapping("/sendHTMLEmail")
    public String sendNotification(@RequestBody sendHTMLEmailRequest emailData){
        String sub = emailData.getSubject()+ " "+ emailData.getName()+ "!!!";
        emailService.htmlSend(emailData.getName(),emailData.getEmail(),emailData.getSubject(),emailData.getTemplateName(),emailData.getOfferings());
        return "HTML Queued";
    }
}
