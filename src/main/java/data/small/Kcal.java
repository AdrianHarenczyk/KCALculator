package data.small;

import java.io.Serializable;
import java.util.Objects;

public class Kcal implements Serializable {
    private double kcalValue;

    private Kcal(double kcalValue) {
        this.kcalValue = kcalValue;
    }

    public static Kcal make(double kcalValue) {
        return new Kcal(kcalValue);
    }

    public double get() {
        return kcalValue;
    }

    public void set(double kcalValue) {
        this.kcalValue = kcalValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kcal kcal = (Kcal) o;
        return Double.compare(kcal.kcalValue, kcalValue) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(kcalValue);
    }

    @Override
    public String toString() {
        return String.valueOf(kcalValue);
    }
}
