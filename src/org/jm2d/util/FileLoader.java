package org.jm2d.util;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Class for the loadFileAsString method
 */
public final class FileLoader {

    private FileLoader() {}

    public static String loadFileAsString(String fileName) throws Exception {
        return Files.readString(Path.of(fileName));
    }

}
