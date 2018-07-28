package entrypoint;

import data.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Optional;

public class MainController {

    @FXML
    private BorderPane mainPane;

    @FXML
    private ListView<Product> productsView;
    private ObservableList<Product> products;

    @FXML
    private TextField addProductField;

    public void initialize() {
        initializeList();
        initSelectionModel();
        addOnClick();
    }

    @FXML
    private void addProduct() {
        String product = addProductField.getText();
    }

    private void initializeList() {
        products = FXCollections.observableArrayList();
        productsView.setItems(products);
    }

    private void addOnClick() {
    }
    private void initSelectionModel() {
        productsView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    public void showNewItemDialog() {
        Dialog<ButtonType> newItemDialog = createDialog(mainPane.getScene().getWindow(), "Add new product");
        newItemDialog.setHeaderText("Use this dialog to add new product.");
        FXMLLoader fxmlLoader = prepareLoader()
        try {
            newItemDialog.getDialogPane().setContent(fxmlLoader.load());

        } catch(IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        newItemDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        newItemDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = newItemDialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            DialogController controller = fxmlLoader.getController();
            TodoItem newItem = controller.processResults();
            todoListView.getSelectionModel().select(newItem);
        }


    }

    private Dialog<ButtonType> createDialog(Window owner, String title) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(owner);
        dialog.setTitle(title);
        return dialog;
    }

    private FXMLLoader prepareLoader(String location) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(location));
        return fxmlLoader;
    }
}
