package com.inf5153.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Provides utility methods for file operations.
 * This class contains methods to read the contents of a file into a string.
 */
public class FileUtils {

    /**
     * Reads the contents of a file specified by the file path into a string.
     *
     * @param filePath the path of the file to read
     * @return the contents of the file as a string
     * @throws IOException              if an I/O error occurs reading from the file
     * @throws IllegalArgumentException if the filePath is null or empty
     */
    public static String readFile(String filePath) throws IOException {
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("filePath is null or empty");
        }
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    /**
     * Extracts the base name of a file without its extension from a given file
     * path.
     *
     * @param filePath the path of the file
     * @return the base name of the file without its extension
     * @throws IllegalArgumentException if the filePath is null or empty
     */
    public static String extractFileName(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("filePath is null or empty");
        }
        String fileName = Paths.get(filePath).getFileName().toString();
        return fileName.substring(0, fileName.lastIndexOf('.'));
    }

    /**
     * Generates a class name with the prefix "Gen" followed by the file name
     * without extension.
     *
     * @param filePath the path of the file
     * @return the generated class name
     */
    public static String generateClassName(String filePath) {
        String baseName = extractFileName(filePath);
        return "Gen" + capitalizeFirstLetter(baseName);
    }

    /**
     * Capitalizes the first letter of a given string.
     *
     * @param str the string to capitalize
     * @return the string with the first letter capitalized
     */
    private static String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * Writes the provided content to a file at the specified path.
     *
     * @param filePath the path of the file to write to
     * @param content  the content to write to the file
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public static void writeToFile(String filePath, String content) throws IOException {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                throw new IOException("Failed to create directories: " + parentDir.getAbsolutePath());
            }
        }
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(content);
        }
    }
}
