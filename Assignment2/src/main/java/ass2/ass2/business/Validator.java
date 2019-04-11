package ass2.ass2.business;

public class Validator {
    public enum CheckType {
        NO_NULL_CHECK{
            @Override
            public int getValue() {
                return 0;
            }
        },
        CHECK_ALL{
            @Override
            public int getValue() {
                return 1;
            }
        };
        public abstract int getValue();
    }

    public Validator(){super();}

    protected ValidatorResponse validateUserName(String userName,int lengthLimit,CheckType checkType){
        ValidatorResponse vr = new ValidatorResponse(true,null);
        if(userName == null || userName.isEmpty()){
            if(checkType.getValue() == 1){
                vr.setValidity(false);
                vr.setMessage("No actual userName introduced");
            }
        }
        else{
            if(!userName.matches("[A-Za-z0-9_]+")){
                vr.setValidity(false);
                vr.setMessage("UserName contains invalid characters!Valid pattern: [A-Za-z0-9_]");
            }
            if(userName.length() > lengthLimit){
                vr.setValidity(false);
                vr.setMessage("UserName is to long!Maximum length: "+lengthLimit);
            }
        }
        return vr;
    }

    protected ValidatorResponse validatePassword(String password,int lengthLimit,CheckType checkType){
        ValidatorResponse vr = new ValidatorResponse(true,null);
        if(password == null || password.isEmpty()){
            if(checkType.getValue() == 1) {
                vr.setValidity(false);
                vr.setMessage("No actual password introduced");
            }
        }
        else{
            if(!password.matches("[A-Za-z0-9_+!?~&$@]+")){
                vr.setValidity(false);
                vr.setMessage("Password contains invalid characters!Valid pattern: [A-Za-z0-9_+!?~&$@]");
            }
            if(password.length() > lengthLimit){
                vr.setValidity(false);
                vr.setMessage("Password is to long!Maximum length: "+lengthLimit);
            }
        }
        return vr;
    }

    protected ValidatorResponse validateLastName(String lastName,int lengthLimit,CheckType checkType){
        ValidatorResponse vr = new ValidatorResponse(true,null);
        if(lastName == null || lastName.isEmpty()){
            if(checkType.getValue() == 1) {
                vr.setValidity(false);
                vr.setMessage("No actual Last Name introduced");
            }
        }
        else{
            String[] splitresult = lastName.split("-");
            for(String slipItem: splitresult)
                if(!slipItem.matches("[A-Za-z]+")){
                    vr.setValidity(false);
                    vr.setMessage("Last name contains invalid characters!Valid pattern: [A-Za-z]");
                }
            if(lastName.length() > lengthLimit){
                vr.setValidity(false);
                vr.setMessage("Last name is to long!Maximum length: "+lengthLimit);
            }
        }
        return vr;
    }

    protected ValidatorResponse validateFirstName(String firstName,int lengthLimit,CheckType checkType){
        ValidatorResponse vr = new ValidatorResponse(true,null);
        if(firstName == null || firstName.isEmpty()){
            if(checkType.getValue() == 1) {
                vr.setValidity(false);
                vr.setMessage("No actual First Name introduced");
            }
        }
        else{
            String[] splitresult = firstName.split(" ");
            for(String slipItem: splitresult)
                if(!slipItem.matches("[A-Za-z]+")){
                    vr.setValidity(false);
                    vr.setMessage("First name contains invalid characters!Valid pattern: [A-Za-z]");
                }
            if(firstName.length() > lengthLimit){
                vr.setValidity(false);
                vr.setMessage("First name is to long!Maximum length: "+lengthLimit);
            }
        }
        return vr;
    }

    protected ValidatorResponse validateIdentityCardNumber(String identityCardNumber,int lengthLimit,CheckType checkType){
        ValidatorResponse vr = new ValidatorResponse(true,null);
        if(identityCardNumber == null || identityCardNumber.isEmpty()){
            if(checkType.getValue() == 1) {
                vr.setValidity(false);
                vr.setMessage("No actual identity card number introduced");
            }
        }
        else{
            if(!identityCardNumber.matches("[A-Za-z0-9]+")){
                vr.setValidity(false);
                vr.setMessage("Identity card number contains invalid characters!Valid pattern: [A-Za-z0-9]");
            }
            if(identityCardNumber.length() > lengthLimit){
                vr.setValidity(false);
                vr.setMessage("Identity card number is to long!Maximum length: "+lengthLimit);
            }
        }
        return vr;
    }

    protected ValidatorResponse validatePersonalNumericalCode(String personalNumericalCode,int lengthLimit,CheckType checkType){
        ValidatorResponse vr = new ValidatorResponse(true,null);
        if(personalNumericalCode == null || personalNumericalCode.isEmpty()){
            if(checkType.getValue() == 1) {
                vr.setValidity(false);
                vr.setMessage("No actual personal numerical code introduced");
            }
        }
        else{
            if(!personalNumericalCode.matches("[0-9]+")){
                vr.setValidity(false);
                vr.setMessage("Personal numerical code contains invalid characters!Valid pattern: [0-9]");
            }
            if(personalNumericalCode.length() > lengthLimit){
                vr.setValidity(false);
                vr.setMessage("Personal numerical code is to long!Maximum length: "+lengthLimit);
            }
        }
        return vr;
    }

    protected ValidatorResponse validateEmailAddress(String emailAddress,int lengthLimit,CheckType checkType){
        ValidatorResponse vr = new ValidatorResponse(true,null);
        if(emailAddress == null || emailAddress.isEmpty()){
            if(checkType.getValue() == 1) {
                vr.setValidity(false);
                vr.setMessage("No actual email address introduced");
            }
        }
        else{
            if(!emailAddress.matches("[A-Za-z0-9_@.<>&$]+")){
                vr.setValidity(false);
                vr.setMessage("Email address contains invalid characters!valid pattern: [A-Za-z0-9_@.<>&$]");
            }
            if(emailAddress.length() > lengthLimit){
                vr.setValidity(false);
                vr.setMessage("Email address is to long!Maximum length: "+lengthLimit);
            }
        }
        return vr;
    }

    protected ValidatorResponse validateDoubleNumber(String doubleNumber,double lowLimit,double highLimit,CheckType checkType){
        ValidatorResponse vr = new ValidatorResponse(true,null);
        if(doubleNumber == null || doubleNumber.isEmpty()){
            if(checkType.getValue() == 1) {
                vr.setValidity(false);
                vr.setMessage("No actual value introduced");
            }
        }
        else{
            try{
                double doubleValue = Double.parseDouble(doubleNumber);
                if(!(lowLimit <= doubleValue && doubleValue <= highLimit)){
                    vr.setValidity(false);
                    vr.setMessage("Double value introduced not in range!Range: [" + lowLimit+","+highLimit+"]");
                }
            }catch(NumberFormatException e){
                vr.setValidity(false);
                vr.setMessage("Value introduced was not double!");
            }
        }
        return vr;
    }

    protected ValidatorResponse validateIntegerNumber(String intNumber,int lowLimit,int highLimit,CheckType checkType){
        ValidatorResponse vr = new ValidatorResponse(true,null);
        if(intNumber == null || intNumber.isEmpty()){
            if(checkType.getValue() == 1) {
                vr.setValidity(false);
                vr.setMessage("No actual value introduced");
            }
        }
        else{
            try{
                int intValue = Integer.parseInt(intNumber);
                if(!(lowLimit <= intValue && intValue <= highLimit)){
                    vr.setValidity(false);
                    vr.setMessage("Integer value introduced not in range!Range: [" + lowLimit+","+highLimit+"]");
                }
            }catch(NumberFormatException e){
                vr.setValidity(false);
                vr.setMessage("Value introduced was not integer!");
            }
        }
        return vr;
    }

    protected ValidatorResponse validateScholarShipState(String scholarship,CheckType checkType){
        ValidatorResponse vr = new ValidatorResponse(true,null);
        if(scholarship == null || scholarship.isEmpty()){
            if(checkType.getValue() == 1){
                vr.setValidity(false);
                vr.setMessage("No actual scholarship state introduced");
            }
        }
        else{
            if(!scholarship.matches("[A-Z]+")){
                vr.setValidity(false);
                vr.setMessage("Scholarship state contains invalid characters!");
            }
        }
        return vr;
    }

    protected ValidatorResponse validateAddress(String address,int lengthLimit, CheckType checkType){
        ValidatorResponse vr = new ValidatorResponse(true,null);
        if(address == null || address.isEmpty()){
            if(checkType.getValue() == 1) {
                vr.setValidity(false);
                vr.setMessage("No actual address introduced");
            }
        }
        else{
            String[] splitresult = address.split(" ");
            for(String slipItem: splitresult)
                if(!slipItem.matches("[A-Za-z0-9.]+")){
                    vr.setValidity(false);
                    vr.setMessage("Address contains invalid characters!Valid pattern: [A-Za-z0-9]");
                }
            if(address.length() > lengthLimit){
                vr.setValidity(false);
                vr.setMessage("Address is to long!Maximum length: "+lengthLimit);
            }
        }
        return vr;
    }

    protected ValidatorResponse validateCourseSearch(String courseSession,CheckType checkType){
        ValidatorResponse vr = new ValidatorResponse(true,null);
        if(courseSession == null || courseSession.isEmpty()){
            if(checkType.getValue() == 1){
                vr.setValidity(false);
                vr.setMessage("No actual value introduced");
            }
        }
        else{
            String[] splitText = courseSession.split("-");
            if(splitText.length != 2) return null;
            String courseName = splitText[0];
            String session = splitText[1];

            if(!courseName.matches("[A-Za-z]+")){
                vr.setValidity(false);
                vr.setMessage("Course name contains invalid characters!Valid pattern: [A-Za-z]");
            }
            if(!session.matches("[0-9/]+")){
                vr.setValidity(false);
                vr.setMessage("Session contains invalid characters!Valid pattern: [0-9/]");
            }
        }
        return vr;
    }
}
