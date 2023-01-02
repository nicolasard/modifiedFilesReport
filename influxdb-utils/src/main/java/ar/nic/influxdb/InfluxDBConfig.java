package ar.nic.influxdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class InfluxDBConfig {
    final String serviceUrl;

    final String user;

    final String password;

    @Autowired
    public InfluxDBConfig(
            @Value("${influx-url}") String serviceUrl,
            @Value("${influx-user}") String user,
            @Value("${influx-password}") String password) {
        this.serviceUrl = serviceUrl;
        this.user = user;
        this.password = password;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
