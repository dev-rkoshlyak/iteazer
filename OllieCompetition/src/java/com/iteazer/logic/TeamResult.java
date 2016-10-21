package com.iteazer.logic;

/**
 *
 * @author Wsl_F2ITeazer
 */
public class TeamResult {

    Team team;
    /**
     * time in milliseconds
     */
    int time;

    /**
     * <li>-1 - unknown</li>
     * <li> 0 - unsuccessful</li>
     * <li> 1 - successful </li>
     */
    int successful;

    public TeamResult(Team team, int time) {
        this.team = team;
        this.time = time;
        successful = -1;
    }

}
