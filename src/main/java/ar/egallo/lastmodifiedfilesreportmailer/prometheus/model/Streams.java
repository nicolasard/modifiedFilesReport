package ar.egallo.lastmodifiedfilesreportmailer.prometheus.model;

import java.util.List;

public class Streams {
    private List<Entries> entries;

    private Label labels;

    public Label getLabels() {
        return labels;
    }

    public void setLabels(Label labels) {
        this.labels = labels;
    }

    public List<Entries> getEntries() {
        return entries;
    }

    public void setEntries(List<Entries> entries) {
        this.entries = entries;
    }
}
