package ar.egallo.lastmodifiedfilesreportmailer.influxdb.model;

import java.util.List;

public class Measurement {

    String name;

    List<Field> field;

    List<Tag> tag;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Field> getField() {
        return field;
    }

    public void setField(List<Field> field) {
        this.field = field;
    }

    public List<Tag> getTag() {
        return tag;
    }

    public void setTag(List<Tag> tag) {
        this.tag = tag;
    }
}
