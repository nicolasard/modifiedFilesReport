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

    final String prometheusCredentials;

    final Boolean influxEnabled;

    final String loggingMetric;

    @Autowired
    public AppConfiguration(
            @Value("${path-to-check}") final String pathToCheck,
            @Value("${email-to}") String emailTo,
            @Value("${email-from}") String emailFrom,
            @Value("${influx-enabled}") Boolean influxEnabled,
            @Value("${influx-url}") String serviceUrl,
            @Value("${influx-credentials}") String prometheusCredentials,
            @Value("${logging-metric}") String loggingMetric) {
        this.pathToCheck = pathToCheck;
        this.emailTo = emailTo;
        this.emailFrom = emailFrom;
        this.serviceUrl = serviceUrl;
        this.prometheusCredentials = prometheusCredentials;
        this.loggingMetric = loggingMetric;
        this.influxEnabled = influxEnabled;
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

    public String getPrometheusCredentials() {
        return prometheusCredentials;
    }

    public Boolean getInfluxEnabled() {
        return influxEnabled;
    }

    public String getLoggingMetric() {
        return loggingMetric;
    }
}
