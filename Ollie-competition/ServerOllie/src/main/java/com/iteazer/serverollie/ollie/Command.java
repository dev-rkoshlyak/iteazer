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
    private final Map<String, Object> parameters;

    Command(String name) {
        this.name = name;
        this.waitAfter = 0;
        this.parameters = new TreeMap<>();
    }

    void addParamter(String key, Object value) {
        parameters.put(key, value);
    }

    /**
     *
     * @param key
     * @return Integer parameter, or null if parameter not exists or not Integer
     */
    Integer getIntegerParameter(String key) {
        Object o = parameters.get(key);
        if (o == null) {
            return null;
        }

        if (o.getClass().equals(Integer.class)) {
            return (Integer) o;
        }

        if (o.getClass().equals(String.class)) {
            try {
                Integer i = Integer.valueOf((String) o);
                return i;
            } catch (Exception ex) {

            }
        }

        return null;
    }

    /**
     *
     * @param key
     * @return String parameter, or null if parameter not exists or not String
     */
    String getStringParameter(String key) {
        Object o = parameters.get(key);
        if (o == null) {
            return null;
        }

        if (o.getClass().equals(String.class)) {
            return (String) o;
        }

        return null;
    }

    Object getParameter(String key) {
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
