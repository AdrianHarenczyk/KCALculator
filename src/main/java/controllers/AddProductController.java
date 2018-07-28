package controllers;

import data.Product;
import data.small.Kcal;
import data.small.MeasureUnit;
import data.small.Name;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddProductController {

    @FXML
    private TextField productName;
    @FXML
    private TextField kcalForHundred;
    @FXML
    private ComboBox<MeasureUnit> measureUnitChooser;

    private ObservableList<MeasureUnit> measureUnits;

    public void initialize() {
        measureUnits = FXCollections.observableArrayList(MeasureUnit.values());
        setDataForChooser();
    }

    public Product processResults() {
        return Product.make(getProductName(),getUnit(),getKcal());
    }

    private Name getProductName() {
        String textName = productName.getText();
        return Name.make(textName);
    }

    private Kcal getKcal() {
        String kcal = kcalForHundred.getText();
        return Kcal.make(Double.parseDouble(kcal));
    }

    private void setDataForChooser() {
        measureUnitChooser.setItems(measureUnits);
    }

    private MeasureUnit getUnit() {
        return measureUnitChooser.getSelectionModel().getSelectedItem();
    }
}
