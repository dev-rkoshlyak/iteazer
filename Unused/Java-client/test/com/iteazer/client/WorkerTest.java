package com.iteazer.client;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Wsl_F@Iteazer
 */
public class WorkerTest {

    public WorkerTest() {
    }

    /**
     * Test of getDirection method, of class Worker.
     */
    @Test
    public void testGetDirectionChar() {
        String command = "N_1000_23";
        char expResult = 'N';
        char result = Worker.getDirection(command);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDirection method, of class Worker.
     */
    @Test
    public void testGetDirectionChar2() {
        String command = "n_12000_23";
        char expResult = 'N';
        char result = Worker.getDirection(command);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDirection method, of class Worker.
     */
    @Test
    public void testGetDirectionChar3() {
        String command = "E_1_230";
        char expResult = 'E';
        char result = Worker.getDirection(command);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDirection method, of class Worker.
     */
    @Test
    public void testGetDirectionChar4() {
        String command = "e_4321_0";
        char expResult = 'E';
        char result = Worker.getDirection(command);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDirection method, of class Worker.
     */
    @Test
    public void testGetDirectionChar5() {
        String command = "S_150_223";
        char expResult = 'S';
        char result = Worker.getDirection(command);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDirection method, of class Worker.
     */
    @Test
    public void testGetDirectionChar6() {
        String command = "w_18_197";
        char expResult = 'W';
        char result = Worker.getDirection(command);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDirection method, of class Worker.
     */
    @Test
    public void testGetDirectionChar7() {
        String command = "R_1000_23";
        Set<Character> expResult = new HashSet<>(Arrays.asList('N', 'S', 'E', 'W'));
        char result = Worker.getDirection(command);
        assertTrue(expResult.contains(result));
    }

    /**
     * Test of getDirection method, of class Worker.
     */
    @Test
    public void testGetDirectionChar8() {
        String command = "q_1000_23";
        char expResult = 0;
        char result = Worker.getDirection(command);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTime method, of class Worker.
     */
    @Test
    public void testGetTime() {
        String command = "N_1000_23";
        int expResult = 1000;
        int result = Worker.getTime(command);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTime method, of class Worker.
     */
    @Test
    public void testGetTime2() {
        String command = "N_100_123";
        int expResult = 100;
        int result = Worker.getTime(command);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTime method, of class Worker.
     */
    @Test
    public void testGetTime3() {
        String command = "S_04321_3";
        int expResult = 4321;
        int result = Worker.getTime(command);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTime method, of class Worker.
     */
    @Test
    public void testGetTime4() {
        String command = "S_-4321_3";
        int expResult = -1;
        int result = Worker.getTime(command);
        assertEquals(expResult, result);
    }

    /**
     * Test of getSpeed method, of class Worker.
     */
    @Test
    public void testGetSpeed() {
        String command = "S_-4321_3";
        int expResult = 3;
        int result = Worker.getSpeed(command);
        assertEquals(expResult, result);
    }

    /**
     * Test of getSpeed method, of class Worker.
     */
    @Test
    public void testGetSpeed2() {
        String command = "W_123_250";
        int expResult = 250;
        int result = Worker.getSpeed(command);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDSpeed method, of class Worker.
     */
    @Test
    public void testGetSpeed3() {
        String command = "E_11_256";
        int expResult = -1;
        int result = Worker.getSpeed(command);
        assertEquals(expResult, result);
    }

}
