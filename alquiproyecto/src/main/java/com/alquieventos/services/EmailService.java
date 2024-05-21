package com.alquieventos.services;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.net.ssl.*;
import java.security.cert.X509Certificate;
//La clase EmailService se encarga de enviar correos electrónicos utilizando el protocolo SMTP (Simple Mail Transfer Protocol). Este servicio está configurado específicamente para usar el servidor SMTP de Gmail.
public class EmailService {
    private String username;
    private String password;
    private Properties properties;//Un objeto Properties que contiene la configuración del servidor SMTP

    //recibe el username y la password para la autenticación en el servidor SMTP. Configura las propiedades necesarias para la conexión SMTP, incluyendo la autenticación, el inicio de TLS y las especificaciones del servidor SMTP de Gmail.
    public EmailService(String username, String password) {
        this.username = username;
        this.password = password;
        this.properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        disableCertificateValidation();
    }

    //Este metodo se encarga de enviar un orreo electrónico. Recibe como parámetros el destinatario (to), el asunto (subject) y el contenido (content) del correo.
    public void sendEmail(String to, String subject, String content) throws MessagingException {
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(content);

        Transport.send(message);
    }

    private void disableCertificateValidation() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                }
            };

            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Para el correo SMTP
            properties.put("mail.smtp.ssl.socketFactory", sc.getSocketFactory());

            HostnameVerifier allHostsValid = (hostname, session) -> true;
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
