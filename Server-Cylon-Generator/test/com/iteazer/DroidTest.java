package com.iteazer;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Wsl_F
 */
public class DroidTest {

    public DroidTest() {
    }

    /**
     * Test of parseType method, of class Droid.
     */
    @Test
    public void testParseType() {
        System.out.println("parseType");
        String s = "bb8              f16fdb2b3b4f";
        String expResult = "bb8";
        String result = Droid.parseType(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of parseType method, of class Droid.
     */
    @Test
    public void testParseType2() {
        System.out.println("parseType");
        String s = "ollie              d8e38c77d05d";
        String expResult = "ollie";
        String result = Droid.parseType(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of parseMAC method, of class Droid.
     */
    @Test
    public void testParseMAC() {
        System.out.println("parseMAC");
        String s = "bb8              f16fdb2b3b4f";
        String expResult = "f16fdb2b3b4f";
        String result = Droid.parseMAC(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of parseMAC method, of class Droid.
     */
    @Test
    public void testParseMAC2() {
        System.out.println("parseMAC");
        String s = "ollie D8:E3:8C:77:D0:5D";
        String expResult = "d8e38c77d05d";
        String result = Droid.parseMAC(s);
        assertEquals(expResult, result);
    }

}
