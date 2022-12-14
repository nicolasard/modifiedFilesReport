package ar.egallo.lastmodifiedfilesreportmailer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    private final String pathToCheck;

    final String emailTo;

    @Autowired
    public AppConfiguration(@Value("${path-to-check}") final String pathToCheck, @Value("${email-to}")  String emailTo) {
        this.pathToCheck = pathToCheck;
        this.emailTo = emailTo;
    }

    public String getPathToCheck() {
        return pathToCheck;
    }

    public String getEmailTo() {
        return emailTo;
    }
}
