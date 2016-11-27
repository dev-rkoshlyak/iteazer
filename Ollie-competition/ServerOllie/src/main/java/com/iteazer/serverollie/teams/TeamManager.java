package com.iteazer.serverollie.teams;

import static com.iteazer.serverollie.Constants.*;

import com.iteazer.serverollie.helpers.PathHelper;
import java.util.List;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Wsl_F@ITeazer
 */
public final class TeamManager {

    private static final TeamManager instance = new TeamManager();
    private final Map<String, Team> allTeams;
    private final PathHelper pathHelper = new PathHelper();

    private TeamManager() {
        this.allTeams = new TreeMap<>();
    }

    public void loadTeams() {
        readTeams(FILE_TEAMS_INFO);
        System.out.println("loaded information about " + allTeams.size() + " teams");
    }

    public Team getTeam(String teamName) {
        return allTeams.get(teamName);
    }

    public static TeamManager getInstance() {
        return instance;
    }

    void addNewTeam(Team team) {
        String name = team.getName();
        if (!allTeams.containsKey(name)) {
            allTeams.put(team.getName(), team);
        }
    }

    private void readTeams(String inputFileName) {
        String projectFolder = pathHelper.getProjectHomeFolder();
        String filePath = projectFolder + '/' + inputFileName;
        System.out.println("Load information about teams from file:\n" + filePath);
        List<String> teamsText = pathHelper.readLines(filePath);
        for (String team : teamsText) {
            addNewTeam(new Team(team));
        }
    }

    public String getMAC(String teamName) {
        Team team = getTeam(teamName);
        return team.getDroidMAC();
    }

    public boolean checkTeam(String name, String password) {
        Team team = getTeam(name);
        return team.checkPassword(password);
    }
}
