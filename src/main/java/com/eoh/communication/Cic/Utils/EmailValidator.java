package com.eoh.communication.Cic.Utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmailValidator {

    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private static Pattern pattern;
    private Matcher matcher;
    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
    }


    public boolean validateEmail(String email) {

        if (email !=null && !email.equalsIgnoreCase("")) {

            matcher = pattern.matcher(email);
        }else {
            email= "";
            matcher = pattern.matcher(email);
        }
            return matcher.matches();

    }
}
