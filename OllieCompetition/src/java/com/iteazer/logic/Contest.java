package com.iteazer.logic;

/**
 *
 * @author Wsl_F@ITeazer
 */
public class Contest {

    static String name;
    static Results results;
    /**
     * <li>-1 - disabled </li>
     * <li> [0 .. MAX_ROUND-1] - round i+1 </li>
     */
    static int finalSubmitStatus;

    static {
        results = new Results();
        name = "Ollie contest";
        finalSubmitStatus = -1;
    }

    public static Results getResults() {
        return results;
    }

    public static int finalSubmit() {
        return finalSubmitStatus;
    }

    public static void setFinalSubmitStatus(int status) {
        finalSubmitStatus = status;
    }
}
