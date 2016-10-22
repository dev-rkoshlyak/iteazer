package com.iteazer.logic;

import static com.iteazer.logic.Constants.PROJECT_CAPTION;
/**
 *
 * @author wslf
 */
public class PathHelper {
    public static String getProjectHomeFolder() {
        String path = Team.class.getResource("").getPath();
        int index = path.lastIndexOf(PROJECT_CAPTION);
        return path.substring(0, index+PROJECT_CAPTION.length());
    }
}
