package com.likithalajangi.springBootSES.service;

import com.likithalajangi.springBootSES.model.Offer;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EmailService {
   @Autowired
   private JavaMailSender emailSender;//wraps the email data(sub,body,fromadress,toaddress) into a msg and talks to SMTP host in authorised way from username and password
   @Value("${fromEmail}")
   private String fromMail;
   @Value("${fromName}")
   private String fromName;
   @Autowired
   private SpringTemplateEngine templateEngine;

   @Async
   public void simpleSend(String toemail,String subject,String msg){
log.info("simpleSend toemail: {} message: {}",toemail,msg);

     try{
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);

        helper.setFrom(fromMail,fromName);
        helper.setTo(toemail);
        helper.setSubject(subject);
        helper.setText(msg,true);
        emailSender.send(message);
        log.info("simpleSend: Email queued");
     } catch (Exception e) {
         log.error("Got Exception: "+ e.getMessage());
     }


   }

   @Async
   public void htmlSend(String name, String email, String subject, String templateName, List<Offer> offerings) {
      log.info("htmlSend: receiverMail: {}, name: {}, subject: {}, template name: {}",email,name,subject,templateName);

      try{
         MimeMessage msg= emailSender.createMimeMessage();
         MimeMessageHelper helper = new MimeMessageHelper(msg);
         helper.setTo(email);
         helper.setFrom(fromMail,fromName);
         helper.setSubject(subject);
         String html="";
         //Thymeleaf Context
         Context context = new Context();
         //properties to show up in template after stored in context
         Map<String,Object> properties = new HashMap<String,Object>();
         properties.put("name",name);
         properties.put("offerings",offerings);
         context.setVariables(properties);

         html = templateEngine.process("emails/"+templateName,context);
         // log.info(html);
         helper.setText(html,true);
         emailSender.send(msg);
         log.info("htmlSend: Email queued");

      } catch (MessagingException | UnsupportedEncodingException e) {
          log.error("Got Exception : {}", e.getMessage());
      }
   }
}
