package com.iteazer.serverollie.teams;

/**
 *
 * @author Wsl_F@ITeazer
 */
public class Team {

    private String name;
    private String droidMAC;
    private String serverAddress;

    // very bad idea to store password here
    private String password;

    private void init(String name, String password, String droidMAC, String serverAddress) {
        this.name = name;
        this.password = password;
        this.droidMAC = droidMAC;
        this.serverAddress = serverAddress;

        TeamManager tm = TeamManager.getInstance();
        tm.addNewTeam(this);
    }

    public Team(String name, String password) {
        init(name, password, "", "");
    }

    public Team(String s) {
        String str[] = s.split(" |\t");
        init(str[0], str[1], str[2], str[3]);
    }

    public String getName() {
        return name;
    }

    public String getDroidMAC() {
        return droidMAC;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public boolean checkPassword(String check) {
        return check.equals(password);
    }

}
