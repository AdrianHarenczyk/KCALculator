package appinstance;

public class AppInstanceSaver {
    private static AppInstanceSaver ourInstance = new AppInstanceSaver();

    public static AppInstanceSaver instance() {
        return ourInstance;
    }

    private AppInstanceSaver() {
    }

    
}
