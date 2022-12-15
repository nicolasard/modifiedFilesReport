package ar.egallo.lastmodifiedfilesreportmailer.prometheus;

import ar.egallo.lastmodifiedfilesreportmailer.prometheus.model.Entries;
import ar.egallo.lastmodifiedfilesreportmailer.prometheus.model.Label;
import ar.egallo.lastmodifiedfilesreportmailer.prometheus.model.Streams;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/*
 * Prometheus push request.
 * https://grafana.com/docs/loki/latest/api/
 */
@Service
public class PrometheusService {

    final String serviceUrl;

    @Autowired
    public PrometheusService(@Value("${prometheus-url}")  String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public void pushValues() throws IOException {
        Streams stream = new Streams();
        Entries entries = new Entries();
        entries.setLine("12");
        entries.setLine("111");
        Label label = new Label();
        label.setName("file-changed");
        stream.setLabels(label);
        stream.setEntries(List.of(entries));
        ObjectMapper objectMapper = new ObjectMapper();

        ///
        URL url = null;
        try {
            url = new URL(this.serviceUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Content-Type","application/x-protobuf");
        con.setRequestProperty("Content-Encoding","snappy");
        con.setRequestProperty("X-Prometheus-Remote-Write-Version","0.1.0");
        con.setRequestProperty("Authorization","Basic Mzc4NzI2OmV5SnJJam9pTkdRMk5qY3laVEUyTkRsbFpEQTJNRE0zWWpWbE1tTmlNemM1WmpBelptSmhaVFJqWmpSbE1pSXNJbTRpT2lKMFpXeGxaM0poWmpJaUxDSnBaQ0k2TmpJek9URXdmUT09");
        con.setRequestMethod("POST");
        OutputStream os = con.getOutputStream();
        os.write(objectMapper.writeValueAsString(stream).getBytes("UTF-8"));
        os.close();
    }
}
