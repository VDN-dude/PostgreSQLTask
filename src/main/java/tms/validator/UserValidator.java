package tms.validator;

import java.util.regex.Pattern;

public class UserValidator {
    private static final Pattern NAME = Pattern.compile("([A-Za-z])*");
    private static final Pattern EMAIL = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    private static final Pattern USERNAME = Pattern.compile("\\w{6,20}");
    private static final Pattern PASSWORD = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");

    public boolean isValidNAME(String type){return NAME.matcher(type).matches();}
    public boolean isValidEMAIL(String type){return EMAIL.matcher(type).matches();}
    public boolean isValidUSERNAME(String type){return USERNAME.matcher(type).matches();}
    public boolean isValidPASSWORD(String type){return PASSWORD.matcher(type).matches();}
}
