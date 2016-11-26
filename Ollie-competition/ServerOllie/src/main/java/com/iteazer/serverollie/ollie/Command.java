package com.iteazer.serverollie.ollie;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Wsl_F@ITeazer
 */
public class Command {

    final String name;
    /**
     * time in ms for waiting after command execution
     */
    private int waitAfter;
    private final Map<String, Integer> parameters;

    Command(String name) {
        this.name = name;
        this.waitAfter = 0;
        this.parameters = new TreeMap<>();
    }

    void addParamter(String key, Integer value) {
        parameters.put(key, value);
    }

    Integer getParameter(String key) {
        return parameters.get(key);
    }

    int getParametersCount() {
        return parameters.size();
    }

    String toExecutionString() {
        StringBuilder executionString = new StringBuilder(name);
        executionString.append("?");

        parameters.forEach((k, v)
                -> executionString.append(k).append("=").append(v).append("&"));

        return executionString.toString();
    }

    int getWaitAfter() {
        return waitAfter;
    }

    void setWaitAfter(int waitTime) {
        this.waitAfter = waitTime;
    }
}
