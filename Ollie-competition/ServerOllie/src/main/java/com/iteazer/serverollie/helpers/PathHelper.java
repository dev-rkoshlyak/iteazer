package com.iteazer.serverollie.helpers;

import static com.iteazer.serverollie.Constants.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Wsl_F@ITeazer
 */
public class PathHelper {

    public String getProjectHomeFolder() {
        String path = PathHelper.class.getResource("").getPath();
        // if we executing jar file on ubuntu, path contains "file:" prefix, that doesn't need
        // TODO check on Windows & MAC
        if (path.startsWith("file:")) {
            path = path.substring(5);
        }
        System.out.println("PathHelper resource path: " + path);
        int index = path.indexOf(PROJECT_CAPTION);
        return path.substring(0, index + PROJECT_CAPTION.length());
    }

    /**
     *
     * @param directory path
     * @return true if directory exists
     */
    public boolean createFoldersIfNeed(String directory) {
        if (!Files.exists(Paths.get(directory))) {
            try {
                Files.createDirectories(Paths.get(directory));
            } catch (Exception ex) {
                System.err.println("Couldn't create directory: " + directory
                        + "\n" + ex.getMessage());
                return false;
            }
        }

        return true;
    }

    public boolean writeToFile(String fullPath, String text) {
        try {
            Path path = Paths.get(fullPath);
            Files.write(path, text.getBytes(ENCODING), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (Exception ex) {
            System.err.println("Couldn't write submmision to file: " + fullPath
                    + "\n" + ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean writeToFile(String directory, String localPath, String text) {
        if (!createFoldersIfNeed(directory)) {
            return false;
        }
        return writeToFile(directory + localPath, text);
    }

    public String getSubmissionDirectory(int roundN) {
        return getProjectHomeFolder() + "/submissions/" + roundN + "/";
    }

    public String readAllFile(String path) {
        return readAllFile(Paths.get(path));
    }

    public String readAllFile(Path path) {
        String text;
        try {
            text = new String(Files.readAllBytes(path));
        } catch (Exception ex) {
            System.err.println("Can't read file: " + path.toAbsolutePath().toString()
                    + "\n" + ex.getMessage());
            text = null;
        }

        return text;
    }

    public List<String> readLines(String path) {
        return readLines(Paths.get(path));
    }

    public List<String> readLines(Path path) {
        List<String> lines = new LinkedList<>();
        try {
            Files.lines(path).forEach(s -> lines.add(s));
        } catch (Exception ex) {
            System.err.println("Can't read file: " + path.toAbsolutePath().toString()
                    + "\n" + ex.getMessage());
            return null;
        }

        return lines;
    }
}
