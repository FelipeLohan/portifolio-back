package com.FelipeLohan.portifolio.email.interfaces;

public interface EmailServiceI {
    void sendWelcomeEmail(String toEmail, String toName, String customerMessage);
    void sendDuplicateEmail(String toEmail, String toName);
}
