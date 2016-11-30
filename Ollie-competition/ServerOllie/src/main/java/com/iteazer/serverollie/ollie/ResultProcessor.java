package com.iteazer.serverollie.ollie;

import static com.iteazer.serverollie.Constants.*;

/**
 *
 * @author Wsl_F@ITeazer
 */
public class ResultProcessor {

    String processResult(Command command, String result) {
        String[] parts = result.split("\n");
        StringBuilder res = new StringBuilder(SUCCESSFUL_STATUS);

        for (String part : parts) {
            res.append(RESULT_SEPARATOR).append(extractInt(part));
        }

        return res.toString();
    }

    private int extractInt(String s) {
        int ind = s.indexOf(":");
        int res;
        try {
            res = Integer.parseInt(s.substring(ind + 2));
        } catch (Exception ex) {
            res = 0;
        }
        return res;
    }

}
