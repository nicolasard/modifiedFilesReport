package ar.egallo.lastmodifiedfilesreportmailer.influxdb;

import ar.egallo.lastmodifiedfilesreportmailer.AppConfiguration;
import ar.egallo.lastmodifiedfilesreportmailer.influxdb.model.Field;
import ar.egallo.lastmodifiedfilesreportmailer.influxdb.model.Measurement;
import ar.egallo.lastmodifiedfilesreportmailer.influxdb.model.Tag;
import ar.egallo.lastmodifiedfilesreportmailer.mailer.SendMailService;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 *  InfluxDB line protocol https://docs.influxdata.com/influxdb/cloud/reference/syntax/line-protocol/# implementation.
 */
@Service
public class InfluxDBService {

    Logger logger = LoggerFactory.getLogger(SendMailService.class);

    final AppConfiguration appConfiguration;

    @Autowired
    public InfluxDBService(@NonNull AppConfiguration appConfiguration) {
        this.appConfiguration = appConfiguration;
    }

    public void pushValues(@NonNull final Measurement measurement) {
        URL url = null;
        try {
            url = new URL(appConfiguration.getServiceUrl());
        } catch (MalformedURLException e) {
            throw new InfluxDBException(e);
        }
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            throw new InfluxDBException(e);
        }
        con.setRequestProperty(
                "Authorization",
                String.format("Basic %s", appConfiguration.getPrometheusCredentials()));
        try {
            con.setRequestMethod("POST");
        } catch (ProtocolException e) {
            throw new InfluxDBException(e);
        }
        con.setUseCaches(false);
        con.setDoInput(true);
        con.setDoOutput(true);
        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            wr.write(parseToInfluxLine(measurement).getBytes(StandardCharsets.UTF_8));
            wr.flush();
            logger.info("Influx response code: {}", String.valueOf(con.getResponseCode()));
            logger.info("Influx response message: {}", con.getResponseMessage());
            con.getResponseCode();
        } catch (IOException e) {
            throw new InfluxDBException(e);
        }
        con.disconnect();
    }

    public @NonNull String parseToInfluxLine(@NonNull final Measurement measurement) {
        final StringBuilder result = new StringBuilder();
        result.append(String.format("%s,", measurement.getName()));
        for (Tag tag : measurement.getTag()) {
            result.append(String.format("%s=%s ", tag.getName(), tag.getValue()));
        }
        for (Field field : measurement.getField()) {
            result.append(String.format("%s=%s ", field.getName(), field.getValue()));
        }
        return result.toString();
    }
}
