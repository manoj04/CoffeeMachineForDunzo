package utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class JSONUtil {
    public static String readJSONFromFilePath(String path) throws URISyntaxException, IOException {
        URL resource = JSONUtil.class.getClassLoader().getResource(path);
        if (resource != null)
            return FileUtils.readFileToString(new File(resource.toURI()), "UTF-8");
        else return null;
    }
}
