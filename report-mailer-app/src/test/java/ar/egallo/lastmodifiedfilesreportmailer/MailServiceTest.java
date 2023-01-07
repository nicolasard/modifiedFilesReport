package ar.egallo.lastmodifiedfilesreportmailer;

import ar.egallo.lastmodifiedfilesreportmailer.mailer.EmailDetails;
import ar.egallo.lastmodifiedfilesreportmailer.mailer.SendMailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MailServiceTest {

    @Autowired SendMailService sendMailService;

    @Test
    void contextLoads() {
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient("test@email.com");
        emailDetails.setSubject("Test email");
        emailDetails.setMsgBody("This is a test example");
        sendMailService.sendEmail(emailDetails);
    }
}
