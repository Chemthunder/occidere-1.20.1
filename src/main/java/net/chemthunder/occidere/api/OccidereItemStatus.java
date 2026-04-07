package net.chemthunder.occidere.api;

public enum OccidereItemStatus {
    EXPERIMENTAL("Experimental"),
    UNFINISHED("Unfinished");

    public final String string;

    OccidereItemStatus(String string) {
        this.string = string;
    }
}
