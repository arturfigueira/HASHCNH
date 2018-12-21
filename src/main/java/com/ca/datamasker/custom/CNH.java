package com.ca.datamasker.custom;

/**
 * This object represents a Brazilian Driver License Number, also called CNH
 */
public class CNH {
    private final String number;
    private final String checkDigit;

    private static final int INITIAL_WEIGHT = 2;

    /**
     * Creates a new CNH number with it checkDigit
     * @param number Main number
     * @param checkDigit checkDigit
     */
    public CNH(final String number, final String checkDigit) {
        this.number = number;
        this.checkDigit = checkDigit;
    }

    /**
     * Creates a new CNH number and automatically calculate its CheckDigit
     * @param number main Number, wihtout checkDigit
     */
    public CNH(final String number){
        this.number = number;
        final String firstDigit = calculateCheckDigit(number, DC_WEIGHT.FIRST_DC);
        final String secondDigit = calculateCheckDigit(number+firstDigit, DC_WEIGHT.SECOND_DC);
        this.checkDigit =firstDigit+secondDigit;
    }

    public String getNumber() {
        return number;
    }

    public String getCheckDigit() {
        return checkDigit;
    }

    private String calculateCheckDigit(final String number, DC_WEIGHT weightType){
        int columnsSum = 0;
        for(int i=0; i< number.length(); i++){
            final int numericValue = Character.getNumericValue(number.charAt(i));
            columnsSum += weightType.calculate(i, numericValue);
        }
        final int modOfTotalSum = mod11(columnsSum);
        return Integer.toString(modOfTotalSum);
    }

    private static int mod11(final int value){
        int mod11 = value % 11;
        return (mod11 > 9) ? 0 : mod11;
    }

    @Override
    public String toString() {
        return number+checkDigit;
    }
}

enum DC_WEIGHT{
    FIRST_DC, SECOND_DC;

    public int calculate(final int index, final int value){
        int valueWithWeight = 0;
        switch (this){
            case FIRST_DC: valueWithWeight = value * (9-index); break;
            case SECOND_DC: valueWithWeight = (index==9)? 0 : value * (1+index); break;
        }
        return valueWithWeight;
    }
}
