package com.iteazer.logic;

import java.util.ArrayList;

import static com.iteazer.logic.Constants.*;
import java.io.Serializable;

/**
 *
 * @author Wsl_F@ITeazer
 */
public class Results implements Serializable {

    final ArrayList<RoundResult> rounds;

    public Results() {
        rounds = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_ROUNDS; i++) {
            rounds.add(new RoundResult());
        }
    }

    public boolean addAttempt(Team team, int time, int roundN) {
        if (roundN < 0 || roundN >= NUMBER_OF_ROUNDS) {
            return false;
        }

        TeamResult teamResult = new TeamResult(team, time);
        return rounds.get(roundN).addAttempt(teamResult);
    }

    public int getTime(Team team, int roundN) {
        if (roundN < 0 || roundN >= NUMBER_OF_ROUNDS) {
            return -1;
        }

        return rounds.get(roundN).getTime(team);
    }

    public int getTime(Team team, int roundN, int attemptN) {
        if (roundN < 0 || roundN >= NUMBER_OF_ROUNDS) {
            return -1;
        }

        return rounds.get(roundN).getTime(team, attemptN);
    }

    public double getCurrentResult(Team team, int roundN) {
        int time = getTime(team, roundN);
        if (time == -1) {
            return 0;
        }

        int bTime = rounds.get(roundN).getBestTime();
        double score = bTime * 100;
        score /= time;
        return score;
    }

    public int AttemptLeft(Team team, int roundN) {
        return MAX_NUMBER_OF_ATTEMPTS - rounds.get(roundN).getAttemtCount(team);
    }

    public ArrayList<Team> getTeams(int roundN, int attemptN) {
        if (roundN < 0 || roundN >= NUMBER_OF_ROUNDS) {
            return new ArrayList<>();
        }
        return rounds.get(roundN).getTeams(attemptN);
    }

    public int getTeamStatus(Team team, int roundN, int attemptN) {
        if (roundN < 0 || roundN >= NUMBER_OF_ROUNDS) {
            return -1;
        }

        return rounds.get(roundN).getStatus(team, attemptN);
    }

    public void setTeamStatus(Team team, int roundN, int attemptN, int status) {
        if (roundN < 0 || roundN >= NUMBER_OF_ROUNDS) {
            return;
        }

        rounds.get(roundN).setStatus(team, attemptN, status);
    }
}
