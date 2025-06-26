package com.likithalajangi.springBootSES.model;

import lombok.Getter;

import java.util.List;

public class sendHTMLEmailRequest {
    @Getter
    private String email;
    @Getter
    private String name;
    @Getter
    private String subject;

    @Getter
    private List<Offer> offerings;
    private String templateName;
    public String getTemplateName() {
        return templateName;
    }

}
