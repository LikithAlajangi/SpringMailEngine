package com.likithalajangi.springBootSES.Config;

import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.nio.charset.StandardCharsets;

public class thymeleafTemplateConfig {
     @Bean
    public SpringTemplateEngine springTemplateEngine(){
        SpringTemplateEngine se = new SpringTemplateEngine();
        se.addTemplateResolver(emailTemplateResolver());
        return se;
    }

    @Bean
    public ClassLoaderTemplateResolver emailTemplateResolver(){
         ClassLoaderTemplateResolver ctr = new ClassLoaderTemplateResolver();
         ctr.setPrefix("templates/");
         ctr.setSuffix(".html");
         ctr.setTemplateMode(TemplateMode.HTML);
         ctr.setCharacterEncoding(StandardCharsets.UTF_8.name());
         ctr.setCacheable(false);
         return ctr;
    }

}
