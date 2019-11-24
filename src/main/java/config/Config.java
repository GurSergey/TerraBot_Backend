package config;

import di.GetterGuiceServiceLocator;
import di.ServiceLocator;

public class Config {
   // private static final  clazz
    public static int PORT = 9001;
    public static String VERSION = "0.5";
    public static ServiceLocator serviceLocator = GetterGuiceServiceLocator.getLocator();
    public static String SYMBOLS_FOR_TOKEN = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static int SIZE_OF_TOKEN  = 50;
}
