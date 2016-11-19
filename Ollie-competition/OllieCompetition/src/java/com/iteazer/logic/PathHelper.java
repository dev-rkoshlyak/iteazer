package com.iteazer.logic;

import static com.iteazer.logic.Constants.MAX_NUMBER_OF_ATTEMPTS;
import static com.iteazer.logic.Constants.PROJECT_CAPTION;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wsl_F@ITeazer
 */
public class PathHelper {

    public static String getProjectHomeFolder() {
        String path = Team.class.getResource("").getPath();
        int index = path.lastIndexOf(PROJECT_CAPTION);
        return path.substring(0, index + PROJECT_CAPTION.length());
    }

    /**
     *
     * @param directory path
     * @return true if directory exists
     */
    public static boolean createFoldersIfNeed(String directory) {
        if (!Files.exists(Paths.get(directory))) {
            try {
                Files.createDirectories(Paths.get(directory));
            } catch (IOException ex) {
                System.err.println("Couldn't create directory: " + directory);
                return false;
            }
        }

        return true;
    }

    public static boolean writeToFile(String fullPath, String text) {
        try {
            Path path = Paths.get(fullPath);
            Files.write(path, text.getBytes("utf-8"), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ex) {
            System.err.println("Couldn't write submmision to file: " + fullPath);
            System.err.println(ex.getMessage());
            return false;
        }
        return true;
    }

    public static boolean writeToFile(String directory, String localPath, String text) {
        if (!createFoldersIfNeed(directory)) {
            return false;
        }
        return writeToFile(directory + localPath, text);
    }

    public static String getSubmissionName(Team team, int roundN) {
        Results res = Contest.getResults();
        int attempt = MAX_NUMBER_OF_ATTEMPTS - res.AttemptLeft(team, roundN);

        return team.getName() + "_R" + roundN + "_A" + attempt + ".sbm";
    }

    public static String getSubmissionDirectory(int roundN) {
        return PathHelper.getProjectHomeFolder() + "/submissions/" + roundN + "/";
    }

    public static String readFromFile(Path path) {
        String text;
        try {
            text = new String(Files.readAllBytes(path));
        } catch (Exception ex) {
            text = null;
        }

        return text;
    }
}
