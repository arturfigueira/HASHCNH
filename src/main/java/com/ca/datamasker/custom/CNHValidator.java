package com.ca.datamasker.custom;

/**
 * This class has methods to validate Dirvers Licences Numbers from Brazil
 */
public class CNHValidator {

    private static final int CNH_LENGTH = 11;

    private CNHValidator(){}

    /**
     * Check whether a given {@param cnh} is valid or not.
     *
     * @param cnh number with its duo of checkdigts.
     * @return True if its valid according to Brazil rules, False otherwise
     */
    public static boolean isValid(final String cnh){
        boolean isAValidCNH = false;
        if(cnh != null && !cnh.isEmpty() && cnh.length() == CNH_LENGTH && !hasSequences(cnh)){
            final CNH cnhEl = new CNH(cnh.substring(0, cnh.length() - 2));
            final String checkDigit = cnhEl.getCheckDigit();
            isAValidCNH = cnh.endsWith(checkDigit);
        }
        return isAValidCNH;
    }

    /**
     * Check if the given CNH,not counting its check digit, has a sequence of repeated numbers,
     * ex: 11111111111 should be considered invalid
     * @param cnh CNH number with checkdigit
     * @return True if it has sequences or false otherwise
     */
    static boolean hasSequences(final String cnh){
        final String numberWithoutCD = cnh.substring(0, cnh.length()-2);
        return numberWithoutCD.matches("^(\\d)\\1{8}$");
    }
}
