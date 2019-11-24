package di;

public class GetterGuiceServiceLocator {
    private static class SingletonHolder {
        static final GuiceServiceLocator HOLDER_INSTANCE = new GuiceServiceLocator();
    }

    public static GuiceServiceLocator getLocator() {
        return SingletonHolder.HOLDER_INSTANCE;
    }
}
