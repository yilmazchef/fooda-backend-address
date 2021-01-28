package be.fooda.backend.address.service.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PostcodeValidator implements ConstraintValidator<Postcode,String> {
    @Override
    public void initialize(Postcode postcode) {

    }

    @Override
    public boolean isValid(String postcode, ConstraintValidatorContext constraintValidatorContext) {

        String regex="^[1-9][0-9]{3}$" ;
        //Pattern pattern = Pattern.compile(regex);
        return !postcode.isEmpty()
                && postcode.length()>=4
                && postcode.matches(regex);
    }
}

/*
#Japanese postal codes
zipJP=^\d{3}-\d{4}$

#US postal codes
zipUS=^\d{5}\p{Punct}?\s?(?:\d{4})?$

#Dutch postal code
zipNL=^[0-9]{4}\s*[a-zA-Z]{2}$

#Argentinean postal code
zipAR=^\d{3}-\d{4}$

#Swedish postal code
zipSE=^(s-|S-){0,1}[0-9]{3}\s?[0-9]{2}$

#Canadian postal code
zipCA=^([A-Z]\d[A-Z]\s\d[A-Z]\d)$

#UK postal code
zipUK=^[a-zA-Z]{1,2}[0-9][0-9A-Za-z]{0,1} {0,1}[0-9][A-Za-z]{2}$


*/
