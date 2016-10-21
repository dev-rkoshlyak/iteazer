package com.iteazer.logic;

import java.util.ArrayList;
import static com.iteazer.logic.Constants.*;
import static java.lang.Integer.min;

/**
 *
 * @author Wsl_F@ITeazer
 */
public class RoundResult {

    final ArrayList<ArrayList<TeamResult>> attempts;

    public RoundResult() {
        attempts = new ArrayList<>();
        for (int i = 0; i < MAX_NUMBER_OF_ATTEMPTS; i++) {
            attempts.add(new ArrayList<>());
        }
    }

    boolean isAttemptContainsTeam(int attemptNumber, Team team) {
        for (TeamResult tr : attempts.get(attemptNumber)) {
            if (tr.team.equals(team)) {
                return true;
            }
        }

        return false;
    }

    /**
     *
     * @param teamResult
     * @param attemptNumber [0..MAX-1]
     * @return
     */
    public boolean addAttempt(TeamResult teamResult, int attemptNumber) {
        if (attemptNumber < 0 || attemptNumber >= MAX_NUMBER_OF_ATTEMPTS) {
            return false;
        }

        if (isAttemptContainsTeam(attemptNumber, teamResult.team)) {
            return false;
        }

        attempts.get(attemptNumber).add(teamResult);
        return true;
    }

    int getAttemtCount(Team team) {
        for (int i = MAX_NUMBER_OF_ATTEMPTS - 1; i >= 0; i--) {
            if (isAttemptContainsTeam(i, team)) {
                return i + 1;
            }
        }

        return 0;
    }

    public boolean addAttempt(TeamResult teamResult) {
        int attemptNumber = getAttemtCount(teamResult.team);
        return addAttempt(teamResult, attemptNumber);
    }

    public int getTime(Team team) {
        int time = MAX_TIME_VALUE;
        for (int i = 0; i < MAX_NUMBER_OF_ATTEMPTS; i++) {
            int curTime = getTime(team, i);
            if (curTime == -1) {
                curTime = MAX_TIME_VALUE;
            }
            time = min(time, curTime);
        }

        return time == MAX_TIME_VALUE ? -1 : time;
    }

    public int getTime(Team team, int attemptN) {
        for (TeamResult tr : attempts.get(attemptN)) {
            if (tr.team.equals(team) && tr.successful == 1) {
                return tr.time;
            }
        }

        return -1;
    }

    public int getBestTime() {
        int bTime = MAX_TIME_VALUE;

        for (int i = 0; i < MAX_NUMBER_OF_ATTEMPTS; i++) {
            for (TeamResult tr : attempts.get(i)) {
                if (tr.successful == 1) {
                    bTime = min(bTime, tr.time);
                }
            }
        }

        return bTime;
    }

    public ArrayList<Team> getTeams(int attemptN) {
        ArrayList<Team> teams = new ArrayList<>();
        if (attemptN < 0 || attemptN >= MAX_NUMBER_OF_ATTEMPTS) {
            return teams;
        }

        for (TeamResult tr : attempts.get(attemptN)) {
            teams.add(tr.team);
        }

        return teams;
    }

    public int getStatus(Team team, int attemptN) {
        if (attemptN < 0 || attemptN >= MAX_NUMBER_OF_ATTEMPTS) {
            return -1;
        }

        for (TeamResult tr : attempts.get(attemptN)) {
            if (tr.team.equals(team)) {
                return tr.successful;
            }
        }

        return -1;
    }

    public void setStatus(Team team, int attemptN, int status) {
        if (attemptN < 0 || attemptN >= MAX_NUMBER_OF_ATTEMPTS) {
            return;
        }

        for (TeamResult tr : attempts.get(attemptN)) {
            if (tr.team.equals(team)) {
                tr.successful = status;
                return;
            }
        }

    }

}
