package entrypoint;

import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

public class MainController {

    @FXML
    private ListView<String> productsView;
    private ObservableList<String> products;

    @FXML
    private TextField addProductField;

    public void initialize() {
        initializeList();
        setListToSingleSelection();
        addOnClick();
    }

    @FXML
    private void addProduct() {
        String product = addProductField.getText();
        products.add(product);
        System.out.println(products);
    }

    private void initializeList() {
        products = FXCollections.observableArrayList();
        productsView.setItems(products);
    }

    private void setListToSingleSelection() {
        productsView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    private void addOnClick() {
        ObjectProperty<SelectionMode> itemProperty = getItemProperty();
        addEventListener(itemProperty);
    }

    private ObjectProperty<SelectionMode> getItemProperty() {
        productsView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        return productsView.getSelectionModel().selectionModeProperty();
    }

    private void addEventListener(ObjectProperty<SelectionMode> itemProperty) {
        itemProperty.addListener((observable, oldValue, newValue) -> {
            removeSelected();
        });
    }

    private boolean doesExist(SelectionMode newValue) {
        return null != newValue;
    }

    private void removeSelected() {
        String selectedProduct = productsView.getSelectionModel().getSelectedItem();
        products.remove(selectedProduct);
        System.out.println(products);
    }
}
