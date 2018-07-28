package data.small;

public enum Messages {
    DIALOG_TITLE("Add new product"),
    DIALOG_HEADER("Use this dialog to add new product."),
    DIALOG_VIEW_PATH("/add_product_view.fxml"),
    DIALOG_ERROR_MSG("Couldn't load the dialog");

    private String data;

    Messages(String data) {
        this.data = data;
    }

    public String text() {
        return data;
    }
}
