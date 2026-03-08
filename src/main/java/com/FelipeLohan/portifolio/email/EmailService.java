package com.FelipeLohan.portifolio.email;

import com.FelipeLohan.portifolio.email.interfaces.EmailServiceI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class EmailService implements EmailServiceI {

    private static final String BREVO_API_URL = "https://api.brevo.com/v3/smtp/email";

    private final RestTemplate restTemplate;

    @Value("${brevo.api.key}")
    private String apiKey;

    @Value("${brevo.sender.email}")
    private String senderEmail;

    @Value("${brevo.sender.name}")
    private String senderName;

    public EmailService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public void sendWelcomeEmail(String toEmail, String toName, String customerMessage) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", apiKey);

        String htmlContent = """
                <html>
                  <body style="font-family: Arial, sans-serif; color: #333;">
                    <h2>Olá, %s!</h2>
                    <p>Obrigado por entrar em contato. Recebi sua mensagem e retornarei em breve.</p>
                    <blockquote style="border-left: 4px solid #ccc; padding-left: 12px; color: #555;">
                      <em>"%s"</em>
                    </blockquote>
                    <p>Atenciosamente,<br/><strong>%s</strong></p>
                  </body>
                </html>
                """.formatted(toName, customerMessage, senderName);

        Map<String, Object> body = Map.of(
                "sender", Map.of("name", senderName, "email", senderEmail),
                "to", List.of(Map.of("email", toEmail, "name", toName)),
                "subject", "Obrigado pelo contato, " + toName + "!",
                "htmlContent", htmlContent
        );

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
        restTemplate.exchange(BREVO_API_URL, HttpMethod.POST, request, String.class);
    }

    @Override
    public void sendDuplicateEmail(String toEmail, String toName) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", apiKey);

        String htmlContent = """
                <html>
                  <body style="font-family: Arial, sans-serif; color: #333;">
                    <h2>Olá, %s!</h2>
                    <p>Parece que você já entrou em contato anteriormente com este e-mail.</p>
                    <p>Não se preocupe — sua mensagem já foi recebida e entrarei em contato assim que possível.</p>
                    <p>Se precisar de algo urgente, pode me chamar diretamente em <strong>%s</strong>.</p>
                    <p>Atenciosamente,<br/><strong>%s</strong></p>
                  </body>
                </html>
                """.formatted(toName, senderEmail, senderName);

        Map<String, Object> body = Map.of(
                "sender", Map.of("name", senderName, "email", senderEmail),
                "to", List.of(Map.of("email", toEmail, "name", toName)),
                "subject", "Já recebemos seu contato, " + toName + "!",
                "htmlContent", htmlContent
        );

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
        restTemplate.exchange(BREVO_API_URL, HttpMethod.POST, request, String.class);
    }
}
