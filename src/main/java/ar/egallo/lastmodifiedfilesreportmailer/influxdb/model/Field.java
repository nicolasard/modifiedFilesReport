package ar.egallo.lastmodifiedfilesreportmailer.influxdb.model;

public class Field {
    final String name;

    final String value;

    public Field(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
