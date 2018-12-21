package com.ca.datamasker.custom;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class HashCNHTest {

    private static final String validCNH = "24240233011";
    private static final String invalidCNH = "11220233099";

    HashCNH hasher;

    @Before
    public void setUp() throws  Exception{
        hasher = new HashCNH();
    }

    @Test
    public void hashValidCNH() {
        assertTrue("Masked CNH should be also a valid CNH", CNHValidator.isValid(hasher.hashCNH(validCNH)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void hashWithInValidCheckDigit() {
        hasher.hashCNH(invalidCNH);
    }

    @Test(expected = IllegalArgumentException.class)
    public void hashSmallerLength() {
        final Random rand = new Random();
        int n = rand.nextInt(999) + 1000;
        hasher.hashCNH(Integer.toString(n));
    }

    @Test(expected = IllegalArgumentException.class)
    public void hashLargerLength() {
        final Random rand = new Random();
        int n = rand.nextInt(999999) + 100000;
        hasher.hashCNH("123658"+n);
    }

    @Test
    public void checkHashIntegrity() {
        final String firtHash = hasher.hashCNH(validCNH);
        final String secondHash = hasher.hashCNH(validCNH);
        assertEquals("Same input, same output hash", firtHash, secondHash);
    }

}