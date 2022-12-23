package ar.egallo.prometheus;

import ar.egallo.lastmodifiedfilesreportmailer.LastModifiedFilesReportMailerApplication;
import ar.egallo.lastmodifiedfilesreportmailer.influxdb.InfluxDBService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = LastModifiedFilesReportMailerApplication.class)
public class PrometheusServiceTest {

    @Autowired
    InfluxDBService influxDBService;

    @Test
    void contextLoads() {

        //this.prometheusService.pushValues(streamsObject);
    }
}
