package com.ca.datamasker.custom;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CNHValidatorTest {


    @Test
    public void checkValidCNH() {
        assertTrue("This CNH is considered valid", CNHValidator.isValid("84608999256"));
    }

    @Test
    public void checkInvalidCheckDigitCNH() {
        assertFalse("This CNH is considered invalid, because of its checkDigit", CNHValidator.isValid("11856039789"));
    }

    @Test
    public void checkInvalidSequenceCNH() {
        assertFalse("Sequences of equal numbers is considered invalid", CNHValidator.isValid("22222222222"));
    }

    @Test
    public void checkSmallLengthCNH() {
        assertFalse("Smaller than allowed Length", CNHValidator.isValid("123791"));
    }

    @Test
    public void checkLargerLengthCNH() {
        assertFalse("Larger than allowed length", CNHValidator.isValid("358180931014"));
    }

    @Test
    public void checkNullCNH() {
        assertFalse("Null is considered invalid", CNHValidator.isValid(null));
    }

    @Test
    public void checkEmptyCNH() {
        assertFalse("Empty is considered invalid", CNHValidator.isValid(""));
    }

    @Test
    public void checkOnlySpacesCNH() {
        assertFalse("A String with only spaces is considered invalid", CNHValidator.isValid("      "));
    }
}