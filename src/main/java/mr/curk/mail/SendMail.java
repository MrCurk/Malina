package mr.curk.mail;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;


public class SendMail implements Runnable {
    private final MailConfigFile mailConfig;
    private final String subject;
    private final String message;

    public SendMail(MailConfigFile mailConfig, String subject, String message) {
        this.mailConfig = mailConfig;
        this.subject = subject;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", mailConfig.getMailSmtpHost()); // for gmail use smtp.gmail.com or "smtp.mail.yahoo.com"
            props.put("mail.smtp.auth", "true");
            props.put("mail.debug", mailConfig.getMailDebug());
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");

            Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mailConfig.getUsername(), mailConfig.getPassword());
                }
            });

            mailSession.setDebug(mailConfig.getMailDebugBoolean()); // Enable the debug mode

            Message msg = new MimeMessage(mailSession);

            //--[ Set the FROM, TO, DATE and SUBJECT fields
            msg.setFrom(new InternetAddress(mailConfig.getMailFrom()));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailConfig.getMailTo()));
            msg.setSentDate(new Date());
            msg.setSubject(subject);

            //--[ Create the body of the mail
            msg.setText(new SimpleDateFormat("HH:mm:ss dd-MM-yyyy").format(Calendar.getInstance().getTime())
                    + "    " + message);

            //--[ Ask the Transport class to send our mail message
            Transport.send(msg);
            System.out.println("Mail sent!");
        } catch (Exception E) {
            System.out.println("SendMail Error!");
            System.out.println(E.toString());
        }

    }
}