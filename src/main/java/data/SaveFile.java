package data;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class SaveFile implements Serializable {
    private static final long serialVersionUID = -1625303756780890652L;
    private List<Product> productList;

    private SaveFile(List<Product> productList) {
        this.productList = productList;
    }

    public static SaveFile create(List<Product> productList) {
        return new SaveFile(productList);
    }

    public List<Product> getProductList() {
        return productList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SaveFile)) return false;
        SaveFile saveFile = (SaveFile) o;
        return Objects.equals(productList, saveFile.productList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productList);
    }
}
