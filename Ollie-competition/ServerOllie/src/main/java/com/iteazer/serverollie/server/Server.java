package com.iteazer.serverollie.server;

import static com.iteazer.serverollie.Constants.*;
import com.iteazer.serverollie.teams.TeamManager;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Wsl_F@ITeazer
 */
public class Server extends Thread {

    private final int portNumber;
    private ServerSocket socket;

    public Server() {
        this.portNumber = SERVER_PORT_ID;
    }

    public Server(int portNumber) {
        this.portNumber = portNumber;
    }

    public static void main(String[] args) {
        int port;
        try {
            port = Integer.parseInt(args[0]);
        } catch (Exception ex) {
            System.err.println("Couldn't get server port id, connecting to default port\n" + ex.getMessage());
            port = SERVER_PORT_ID;
        }

        Server server = new Server(port);
        server.start();
    }

    @Override
    public void run() {
        try {
            TeamManager.getInstance().loadTeams();
            socket = new ServerSocket(portNumber);
            System.out.println("Start server at: localhost:" + portNumber);
            while (true) {
                Socket client = socket.accept();
                OllieHandler handler = new OllieHandler(client);
                handler.start();
            }
        } catch (Exception ex) {
            System.err.println("Server exception: " + ex.getMessage());
        }

    }
}
