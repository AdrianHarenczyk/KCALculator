package data.small;

import java.io.Serializable;

public enum MeasureUnit implements Serializable {
    GRAM("g"),
    MILLILITER("ml");

    private String representation;

    MeasureUnit(String representation) {
        this.representation = representation;
    }
}
