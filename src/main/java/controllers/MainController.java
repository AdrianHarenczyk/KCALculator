package controllers;

import appinstance.AppInstanceSaver;
import data.Product;
import data.small.Messages;
import exceptions.ExceptionHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Optional;

import static data.small.Messages.*;
import static javafx.scene.control.ButtonType.*;

public class MainController {

    @FXML
    private BorderPane mainPane;

    @FXML
    private ListView<Product> productsView;
    private ObservableList<Product> products;

    @FXML
    private TextField addProductField;

    private AppInstanceSaver appInstance;

    public void initialize() {
        appInstance = AppInstanceSaver.instance();
        initializeList();
        initSelectionModel();
    }

    private void initializeList() {
        products = appInstance.getProductList();
        productsView.setItems(products);
    }

    @FXML
    private void addNewProduct() {
        showNewItemDialog();
    }

    private void initSelectionModel() {
        productsView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    private void showNewItemDialog() {
        Dialog<ButtonType> newItemDialog = createDialog(mainPane.getScene().getWindow(), DIALOG_TITLE);
        newItemDialog.setHeaderText(DIALOG_HEADER.text());
        FXMLLoader fxmlLoader = initLoader(newItemDialog);
        addButtonToDialog(newItemDialog, CANCEL, OK);
        addProductWhenConfirm(newItemDialog, fxmlLoader);
    }

    private Dialog<ButtonType> createDialog(Window owner, Messages title) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(owner);
        dialog.setTitle(title.text());
        return dialog;
    }

    private FXMLLoader initLoader(Dialog<ButtonType> dialog) {
        FXMLLoader fxmlLoader = prepareLoader(DIALOG_VIEW_PATH.text());
        tryToBindViewToDialog(dialog, fxmlLoader);
        return fxmlLoader;
    }

    private FXMLLoader prepareLoader(String location) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(location));
        return fxmlLoader;
    }

    private void tryToBindViewToDialog(Dialog<ButtonType> dialog, FXMLLoader loader) {
        try {
            bindViewToDialog(dialog, loader);
        } catch (IOException e) {
            ExceptionHandler.handleException(e, DIALOG_ERROR_MSG.text());
        }
    }

    private void bindViewToDialog(Dialog<ButtonType> dialog, FXMLLoader loader) throws IOException {
        dialog.getDialogPane().setContent(loader.load());
    }

    private void addButtonToDialog(Dialog<ButtonType> dialog, ButtonType... buttonTypes) {
        for (ButtonType type : buttonTypes) {
            dialog.getDialogPane().getButtonTypes().add(type);
        }
    }

    private void addProductWhenConfirm(Dialog<ButtonType> dialog, FXMLLoader loader) {
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == OK) {
            AddProductController controller = loader.getController();
            Product newItem = controller.processResults();
            products.add(newItem);
            productsView.getSelectionModel().select(newItem);
        }
    }


}
