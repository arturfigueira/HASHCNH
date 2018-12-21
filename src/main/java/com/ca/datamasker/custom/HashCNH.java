package com.ca.datamasker.custom;

import com.grid_tools.products.datamasker.IMaskFunction;
import com.grid_tools.products.datamasker.randfunctions;

public class HashCNH implements IMaskFunction {

    @Override
    public Object mask(Object... objects) {
        return null;
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
