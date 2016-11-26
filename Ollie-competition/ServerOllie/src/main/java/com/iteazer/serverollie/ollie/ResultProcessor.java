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
            res.append(RESULT_SEPARATOR).append(part);
        }
        
        return res.toString();
    }
}
