package entity;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;

public class ImageEntity {

    public static boolean isFileImage(File file)
    {
        String mimetype = new MimetypesFileTypeMap().getContentType(file);
        String type = mimetype.split("/")[0];
        return type.equals("image");
    }
    //File file
}
