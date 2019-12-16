package exception;

public class ApplicationException extends Exception {
    private static int code = 0;
    private static String text ;

    static
    {
//        text = ConfigText.registry.getNameByCode(static.code);
    }

    public static int getCode()
    {
        return code;
    }
}
