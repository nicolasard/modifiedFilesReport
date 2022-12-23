package ar.egallo.lastmodifiedfilesreportmailer.influxdb.model;

public class Tag {

    final String name;

    final String value;

    public Tag(String name, String value) {
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
