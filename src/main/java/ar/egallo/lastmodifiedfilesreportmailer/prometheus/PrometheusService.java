package ar.egallo.lastmodifiedfilesreportmailer.prometheus;

import ar.egallo.lastmodifiedfilesreportmailer.AppConfiguration;
import ar.egallo.lastmodifiedfilesreportmailer.prometheus.model.Streams;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/*
 * Prometheus push request.
 * https://grafana.com/docs/loki/latest/api/
 */
@Service
public class PrometheusService {

    final AppConfiguration appConfiguration;

    @Autowired
    public PrometheusService(AppConfiguration appConfiguration) {
        this.appConfiguration = appConfiguration;
    }

    public void pushValues(final Streams stream) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ///
        URL url = null;
        try {
            url = new URL(appConfiguration.getServiceUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Content-Type","application/x-protobuf");
        con.setRequestProperty("Content-Encoding","snappy");
        con.setRequestProperty("X-Prometheus-Remote-Write-Version","0.1.0");
        con.setRequestProperty("Authorization","Basic AAAA");
        con.setRequestMethod("POST");
        con.setUseCaches(false);
        con.setDoInput(true);
        con.setDoOutput(true);
        try( DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            wr.write( objectMapper.writeValueAsString(stream).getBytes(StandardCharsets.UTF_8) );
        }
        con.disconnect();
    }
}
