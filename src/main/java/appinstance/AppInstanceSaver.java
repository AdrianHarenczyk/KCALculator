package appinstance;

import data.Product;
import data.SaveFile;
import exceptions.ExceptionHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility.ConsoleColor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AppInstanceSaver implements Serializable {
    private static final long serialVersionUID = 5110087274583345773L;
    private static AppInstanceSaver ourInstance = new AppInstanceSaver();
    private ObservableList<Product> productList = FXCollections.observableArrayList();
    private static final String SAVE_FILE_NAME = "save.dat";


    private AppInstanceSaver() {
    }

    public static AppInstanceSaver instance() {
        return ourInstance;
    }

    public ObservableList<Product> getProductList() {
        return productList;
    }

    public void saveInstance() {
        try {
            saveObject();
        } catch (IOException e) {
            ExceptionHandler.handleException(e, "Unable to save instance.");
        }
    }

    private List<Product> observableToSimpleList() {
        return new ArrayList<>(this.productList);
    }

    private void saveObject() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(SAVE_FILE_NAME);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        SaveFile fileToSave = SaveFile.create(observableToSimpleList());
        objectOutputStream.writeObject(fileToSave);
    }

    public void loadInstance() {
        try {
            unpackSaveFile();
        } catch (IOException e) {
            ExceptionHandler.handleException(e, "There is no such file.");
        } catch (ClassNotFoundException e) {
            ExceptionHandler.handleException(e, "In this file there are no classes.");
        }
    }

    private Optional<SaveFile> loadObject() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(SAVE_FILE_NAME);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Optional<SaveFile> loadedFile = Optional.empty();
        Object file;
        if ((file = objectInputStream.readObject()) instanceof SaveFile) {
            loadedFile = Optional.of((SaveFile) file);
        }
        return loadedFile;
    }

    private void unpackSaveFile() throws IOException, ClassNotFoundException {
        Optional<SaveFile> optionalSaveFile = loadObject();
        if (optionalSaveFile.isPresent()) {
            SaveFile saveFile = optionalSaveFile.get();
            List<Product> productSimpleList = saveFile.getProductList();
            productList.addAll(productSimpleList);
        }
    }
}
