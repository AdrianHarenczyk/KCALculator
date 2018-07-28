package data;

import data.small.Kcal;
import data.small.MeasureUnit;
import data.small.Name;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {
    private Name name;
    private MeasureUnit measureUnit;
    private Kcal kcal;

    private Product(Name name, MeasureUnit measureUnit, Kcal kcal) {
        this.name = name;
        this.measureUnit = measureUnit;
        this.kcal = kcal;
    }

    public static Product make(Name name, MeasureUnit measureUnit, Kcal kcal) {
        return new Product(name,measureUnit,kcal);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name=" + name +
                ", measureUnit=" + measureUnit +
                ", kcal=" + kcal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) &&
                measureUnit == product.measureUnit &&
                Objects.equals(kcal, product.kcal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, measureUnit, kcal);
    }
}
