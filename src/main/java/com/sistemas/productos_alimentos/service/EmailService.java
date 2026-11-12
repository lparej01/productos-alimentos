package com.sistemas.productos_alimentos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarEnlaceContrasena(String emailDestino, String token) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            // URL temporal que recibirá tu frontend de Angular
            String urlAngular = "http://localhost:4200/restaurar?token=" + token;

            String htmlBody = "<html><body>"
                    + "<h3>Restablecer Contraseña</h3>"
                    + "<p>Haz clic en el siguiente enlace para cambiar tu clave:</p>"
                    + "<a href='" + urlAngular + "'>Cambiar Contraseña</a>"
                    + "</body></html>";

            helper.setFrom("soporte@tuapp.com");
            helper.setTo(emailDestino);
            helper.setSubject("Recuperación de contraseña");
            helper.setText(htmlBody, true); // 'true' renderiza el HTML

            mailSender.send(message);
            System.out.println("¡Correo enviado con éxito a Mailtrap!");

        } catch (Exception e) {
            System.err.println("Error al enviar: " + e.getMessage());
        }
    }
}