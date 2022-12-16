package ar.egallo.prometheus;

import ar.egallo.lastmodifiedfilesreportmailer.AppConfiguration;
import ar.egallo.lastmodifiedfilesreportmailer.LastModifiedFilesReportMailerApplication;
import ar.egallo.lastmodifiedfilesreportmailer.prometheus.PrometheusService;
import ar.egallo.lastmodifiedfilesreportmailer.prometheus.model.Entries;
import ar.egallo.lastmodifiedfilesreportmailer.prometheus.model.Label;
import ar.egallo.lastmodifiedfilesreportmailer.prometheus.model.Streams;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.io.IOException;
import java.util.List;


@SpringBootTest(classes = LastModifiedFilesReportMailerApplication.class)
public class PrometheusServiceTest {

    @Autowired
    PrometheusService prometheusService;

    @Test
    void contextLoads() {
        Streams stream = new Streams();
        Entries entries = new Entries();
        entries.setLine("12");
        entries.setLine("111");
        Label label = new Label();
        label.setName("file-changed");
        stream.setLabels(label);
        stream.setEntries(List.of(entries));
        try {
            this.prometheusService.pushValues(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
