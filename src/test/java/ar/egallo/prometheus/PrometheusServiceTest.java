package ar.egallo.prometheus;

import ar.egallo.lastmodifiedfilesreportmailer.prometheus.PrometheusService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class PrometheusServiceTest {

    final PrometheusService prometheusService;

    @Autowired
    public PrometheusServiceTest(PrometheusService prometheusService) {
        this.prometheusService = prometheusService;
    }

    @Test
    void contextLoads() {
        try {
            this.prometheusService.pushValues();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
