package ar.egallo.lastmodifiedfilesreportmailer;

import java.io.File;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import ar.egallo.lastmodifiedfilesreportmailer.mailer.EmailDetails;
import ar.egallo.lastmodifiedfilesreportmailer.mailer.SendMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ar.egallo.lastmodifiedfilesreportmailer.fileschecker.CheckFilesService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@SpringBootApplication
public class LastModifiedFilesReportMailerApplication implements CommandLineRunner{

    Logger logger = LoggerFactory.getLogger(LastModifiedFilesReportMailerApplication.class);

    @Autowired
    private TemplateEngine textTemplateEngine;

    @Autowired
    private SendMailService sendMailService;

    @Autowired
    private AppConfiguration appConfiguration;

    public static void main(String[] args) {
		SpringApplication.run(LastModifiedFilesReportMailerApplication.class, args);
	}
	
    @Override
    public void run(String... args) {
        //Get Modified Files
       CheckFilesService checkFilesService = new CheckFilesService();
       List<File> findings = checkFilesService.checkFiles(appConfiguration.getPathToCheck()
               ,Instant.now().minus(24,ChronoUnit.HOURS)
               ,Instant.now());
        final Context ctx = new Context();
        ctx.setVariable("name", "recipientName");
        ctx.setVariable("subscriptionDate", new Date());
        ctx.setVariable("changedFiles", findings);
        final String htmlContent = this.textTemplateEngine.process("last-edited-files-email-template.html", ctx);
        final EmailDetails emailDetails = new EmailDetails();
        emailDetails.setSender(appConfiguration.getEmailFrom());
        emailDetails.setRecipient(appConfiguration.getEmailTo());
        emailDetails.setSubject("Ultimos archivos modificados.");
        emailDetails.setMsgBody(htmlContent);
        sendMailService.sendEmailHtml(emailDetails);
        //Create report
        //Send email
        logger.info("Modified fiels found: {}",findings.size());
    }

}
