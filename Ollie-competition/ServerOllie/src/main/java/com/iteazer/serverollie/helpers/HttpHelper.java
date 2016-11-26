package com.iteazer.serverollie.helpers;

import static com.iteazer.serverollie.Constants.*;

import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author Wsl_F@ITeazer
 */
public class HttpHelper {

    public boolean sendHttpRequestWithoutResponse(String urlString) {
        boolean success = true;

        URL url = sendRequest(urlString);
        try (InputStream is = url.openStream()) {
            is.available();
        } catch (Exception ex) {
            printException(ex, urlString);
            success = false;
        }

        return success;
    }

    public String sendHttpRequest(String urlString) {
        String out;
        URL url = sendRequest(urlString);

        try {
            Scanner scanner = new Scanner(url.openStream(), ENCODING).useDelimiter("\\A");
            out = scanner.hasNext() ? scanner.next() : "";
        } catch (Exception ex) {
            printException(ex, urlString);
            out = null;
        }

        return out;
    }

    private URL sendRequest(String urlString) {
        URL url;

        try {
            url = new URL(urlString);
        } catch (Exception ex) {
            printException(ex, urlString);
            url = null;
        }
        return url;
    }

    private void printException(Exception ex, String urlString) {
        System.err.println("Can't acces url: \"" + urlString + '\"'
                + "\n" + ex.getMessage());
    }

}
