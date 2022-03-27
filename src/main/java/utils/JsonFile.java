package utils;

import groovy.util.logging.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Slf4j
public class JsonFile {
    public static String getFileAsString(String pathToJson) throws IOException {
        return Files.readString(Path.of(pathToJson));
    }
}
