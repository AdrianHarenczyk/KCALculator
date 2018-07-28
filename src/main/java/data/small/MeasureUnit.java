package data.small;

import java.io.Serializable;

public enum MeasureUnit implements Serializable {
    GRAM("g"),
    MILLILITER("ml"),
    PIECE("pcs.");

    private String representation;

    MeasureUnit(String representation) {
        this.representation = representation;
    }

    public String getShort() {
        return representation;
    }
}
