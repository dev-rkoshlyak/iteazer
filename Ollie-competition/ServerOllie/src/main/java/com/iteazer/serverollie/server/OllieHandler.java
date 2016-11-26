package com.iteazer.serverollie.server;

import static com.iteazer.serverollie.Constants.*;

import com.iteazer.serverollie.ollie.OllieManager;
import com.iteazer.serverollie.teams.Team;
import com.iteazer.serverollie.teams.TeamManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Wsl_F@ITeazer
 */
public class OllieHandler extends Thread {

    Socket client;
    private BufferedReader reader;
    private PrintWriter writer;

    Team team;
    OllieManager manager = OllieManager.getInstance();

    OllieHandler(Socket client) {
        this.client = client;
        this.team = null;

        try {
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            writer = new PrintWriter(client.getOutputStream(), true);
        } catch (Exception ex) {
            writeException(ex, "Exception caught: client disconnected.");
            this.client = null;
        }
    }

    @Override
    public void run() {
        if (client == null) {
            return;
        }

        if (authenticate()) {
            try {
                processRequests();
            } catch (Exception ex) {
                System.err.println("Exception while processing requests \n"
                        + ex.getMessage());
            }
        }

        closeClient();
    }

    void closeClient() {
        try {
            client.close();
        } catch (Exception ex) {
            writeException(ex, "Exception on closing client.");
        }
    }

    private void writeException(Exception ex, String additionalText) {
        System.err.println(additionalText + "\n" + ex.getMessage());
    }

    private boolean authenticate() {
        boolean res = false;
        try {
            String[] logPas = getNext();

            TeamManager tm = TeamManager.getInstance();
            if (logPas != null && logPas.length == 2
                    && tm.checkTeam(logPas[0], logPas[1])) {
                team = tm.getTeam(logPas[0]);
                res = true;
            }
        } catch (Exception ex) {
            System.err.println("Can't authenticate \n" + ex.getMessage());
            res = false;
        }

        if (res) {
            writer.println(SUCCESSFUL_AUTH_MSG);
            writer.flush();
        }
        
        return res;
    }

    private void processRequests() throws IOException {
        while (true) {
            String[] parts = getNext();
            String result = manager.processCommand(team, parts);
            if (result != null) {
                writer.println(result);
                writer.flush();
            }
        }

    }

    private String[] getNext() throws IOException {
        String line = reader.readLine();
        return line.split(" ");
    }

}
