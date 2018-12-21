package com.ca.datamasker.custom;

import com.grid_tools.products.datamasker.Datamasker;
import com.grid_tools.products.datamasker.IMaskFunction;
import com.grid_tools.products.datamasker.randfunctions;

public class HashCNH implements IMaskFunction {

    @Override
    public Object mask(Object... objects) {
        String maskedValue = null;

        final String inputValue = (String) objects[0];
        if(inputValue == null || inputValue.trim().isEmpty()){
            maskedValue = inputValue;
        }

        try{
            maskedValue = hashCNH(inputValue);
        }catch (IllegalArgumentException e){
            Datamasker.processOutputs(Datamasker.formatMessage("m0345-hasherr", new String[] { "HASH" }));
            Datamasker.processErrors(Datamasker.formatMessage("m0345-hasherr", new String[] { "HASH" }));
            Datamasker.processOutputs(Datamasker.formatMessage("m0155-DBValue", new String[] { inputValue }));
            System.exit(1);
        }
        return maskedValue;
    }

    protected String hashCNH(final String cnh){
        if(!CNHValidator.isValid(cnh)){
            throw new IllegalArgumentException(cnh+ "is invalid and cannot be hashed");
        }

        final String hashedValue = randfunctions.formatHash(cnh.substring(0, cnh.length()-2));
        final CNH cnhEl = new CNH(hashedValue);
        return cnhEl.toString();

    }
}
