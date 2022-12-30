package ar.egallo.lastmodifiedfilesreportmailer.influxdb;

public class InfluxDBException extends RuntimeException {
    public InfluxDBException(Exception e) {
        super(e);
    }
}
