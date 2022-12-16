package ar.egallo.lastmodifiedfilesreportmailer.mailer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class SendMailService {

    Logger logger = LoggerFactory.getLogger(SendMailService.class);

    private final JavaMailSender javaMailSender;

    @Autowired
    public SendMailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public String sendEmail(EmailDetails emailDetails){
        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(emailDetails.getSender());
            mailMessage.setTo(emailDetails.getRecipient().split(";"));
            mailMessage.setText(emailDetails.getMsgBody());
            mailMessage.setSubject(emailDetails.getSubject());

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while Sending Mail";
        }
    }

    public void sendEmailHtml(EmailDetails emailDetails){
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(emailDetails.getMsgBody(), true); // Use this or above line.
            helper.setTo(emailDetails.getRecipient().split(";"));
            helper.setSubject(emailDetails.getSubject());
            helper.setFrom(emailDetails.getSender());
            javaMailSender.send(mimeMessage);
        }
        // Catch block to handle the exceptions
        catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }
}
