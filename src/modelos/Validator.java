package modelos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static boolean valdateEmail(String email){
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher matcher = pattern.matcher(email);

        return matcher.find();

    }

    public static boolean validateTelephone(String campoValidar){
        if (campoValidar.length() == 10) {
            System.out.println("Mayor que diez");
            for (int i = 0; i < campoValidar.length(); i++) {
                if (campoValidar.charAt(i) < 48 && campoValidar.charAt(i) > 57) {
                    System.out.println("Tiene un numero mal");

                    return false;
                }
            }
            return true;
        }
       return false;
    }

    public static boolean validateName(String campoValidar){
        for (int i = 0; i < campoValidar.length(); i++) {
            if(campoValidar.charAt(i) >= 48 && campoValidar.charAt(i) <= 57 ){

                return false;
            }
        }

        return true;
    }

    public static boolean validateRFC(String rfc){
        if (rfc.length()==12) {
            for (int i = 0; i < 3; i++) {
                if(rfc.charAt(i) >= 48 && rfc.charAt(i) <= 57 ){
                    return false;
                }
            }
            for (int i = 4; i < 9; i++) {
                if (rfc.charAt(i) < 48 && rfc.charAt(i) > 57) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
