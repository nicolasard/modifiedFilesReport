package ar.egallo.lastmodifiedfilesreportmailer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    private final String pathToCheck;

    final String emailTo;

    final String emailFrom;

    final String serviceUrl;

    @Autowired
    public AppConfiguration(@Value("${path-to-check}") final String pathToCheck
            , @Value("${email-to}")  String emailTo
            , @Value("${email-from}")  String emailFrom
            , @Value("${prometheus-url}") String serviceUrl) {
        this.pathToCheck = pathToCheck;
        this.emailTo = emailTo;
        this.emailFrom = emailFrom;
        this.serviceUrl = serviceUrl;
    }

    public String getPathToCheck() {
        return pathToCheck;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }
}
