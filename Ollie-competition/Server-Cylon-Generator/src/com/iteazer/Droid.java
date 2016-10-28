package com.iteazer;

/**
 *
 * @author Wsl_F
 */
public class Droid {

    String type;
    String mac;
    String name;

    public Droid() {
        this("", "");
    }

    public Droid(String type, String mac) {
        this.type = type.toLowerCase();
        this.mac = mac.toLowerCase();
        this.name = this.type + "_" + this.mac;
    }

    public Droid(String s) {
        this(parseType(s), parseMAC(s));
    }

    public static String parseType(String s) {
        return s.substring(0, s.indexOf(' '));
    }

    public static String parseMAC(String s) {
        String r = s.substring(s.lastIndexOf(' ') + 1);
        if (r.indexOf(':') != -1) {
            r = String.join("", r.split(":"));
        }

        return r.toLowerCase();
    }
}
