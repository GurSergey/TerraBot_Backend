package exception;

import java.util.HashMap;

public interface Text {
    static HashMap<Integer, String> map = new HashMap<>();
    static String getNameByCode(int code) {
        return map.get(code);
    }
}
