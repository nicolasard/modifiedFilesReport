package ar.nic.influxdb;

import ar.nic.influxdb.model.Field;
import ar.nic.influxdb.model.Measurement;
import ar.nic.influxdb.model.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = {InfluxDBService.class, InfluxDBConfig.class})
public class InfluxDBServiceTest {
    @Autowired
    InfluxDBService influxDBService;

    /*
    * Write a value to the InfluxDB database.
    */
    @Test
    void testWriteValue() {
        final Measurement measurement = new Measurement();
        measurement.setName("NameTest");
        measurement.setField(List.of(new Field("fieldName","fieldValue")));
        measurement.setTag(List.of(new Tag("tagName","tagValue")));
        influxDBService.pushValues(measurement);
    }
}
