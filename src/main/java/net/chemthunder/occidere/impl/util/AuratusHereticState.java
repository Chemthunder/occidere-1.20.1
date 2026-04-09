package net.chemthunder.occidere.impl.util;

public enum AuratusHereticState {
    GILDED("Gilded", "May the martyred be not afraid.", 0, 0xFF6f5318),
    TAINTED("Tainted", "Fear is the only salvation.", 1, 0xFF541524);

    public final String string;
    public final String description;
    public final int placement;
    public final int descColor;

    AuratusHereticState(String string, String description, int placement, int descColor) {
        this.string = string;
        this.description = description;
        this.placement = placement;
        this.descColor = descColor;
    }
}
