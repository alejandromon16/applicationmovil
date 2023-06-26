package com.example.travell;

import android.os.AsyncTask;
import android.util.Log;
import java.util.Properties;
import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
    private static final String TAG = "EmailSender";
    private static final String SMTP_SERVER = "smtp.gmail.com";
    private static final int SMTP_PORT = 587;
    private static final String USERNAME = "admin@travell.app";
    private static final String PASSWORD = "Chespirit@1";

    public static void sendEmail(final String recipientEmail, final String subject, final String messageText) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Properties props = new Properties();
                    props.put("mail.smtp.host", SMTP_SERVER);
                    props.put("mail.smtp.port", SMTP_PORT);
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.starttls.enable", "true");

                    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(USERNAME, PASSWORD);
                        }
                    });

                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(USERNAME));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
                    message.setSubject(subject);
                    message.setText(messageText);

                    Transport.send(message);
                    Log.d(TAG, "Email sent successfully!");
                } catch (AuthenticationFailedException e) {
                    Log.e(TAG, "Authentication failed. Make sure you've enabled less secure apps access.");
                } catch (MessagingException e) {
                    Log.e(TAG, "Error sending email: " + e.getMessage());
                }

                return null;
            }
        }.execute();
    }
}
