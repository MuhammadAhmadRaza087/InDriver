public class Validator {
    public static boolean isValidName (String name) {
        if (name.length() < 3 || name.length() > 40) {
            return false;
        }
        for (int i = 0; i < name.length(); i++) {
            if (!Character.isAlphabetic(name.charAt(i)) && name.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidCnic(String cnic) {
        if (cnic.length() != 15) {
            return false;
        }
        for (int i = 0; i < cnic.length(); i++) {
            if (i == 5 || i == 13) {
                if (cnic.charAt(i) != '-'){
                    return false;
                }
            } else if (!Character.isDigit(cnic.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidPhoneNo (String phoneNo) {
        if (phoneNo.length() != 11) {
            return false;
        }
        if (phoneNo.charAt(0) != '0' || phoneNo.charAt(1) != '3') {
            return false;
        }
        for (int i = 0; i < phoneNo.length(); i++) {
            if (!Character.isDigit(phoneNo.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidLicenceNo (String lic) {
        if (lic.length() < 5 || lic.length() > 10) {
            return false;
        }
        for (int i = 0; i < lic.length(); i++) {
            if (!Character.isDigit(lic.charAt(i)) && !Character.isAlphabetic(lic.charAt(i)) &&
            lic.charAt(i) != '-') {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidUsername(String name) {
        if (name.length() < 5 || name.length() > 15) {
            return false;
        }
        if (Character.isDigit(name.charAt(0))) {
            return false;
        }
        for (int i = 0; i < name.length(); i++) {
            if (!Character.isAlphabetic(name.charAt(i)) && !Character.isDigit(name.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidPassword(String password) {
        return password.length() >= 8 && password.length() <= 15;
    }

    public static boolean isValidRegistrationNo(String regNo) {
        if (regNo.length() < 3 || regNo.length() > 9 || !Character.isAlphabetic(regNo.charAt(0))) {
            return false;
        }
        int dashes = 0;
        boolean valid = false;
        for (int i = 0; i < regNo.length(); i++) {
            if (regNo.charAt(i) == '-') {
                dashes++;
            } else if (Character.isDigit(regNo.charAt(i))) {
                valid = true;
            }
        }
        if (dashes != 1) {
            valid = false;
        }
        return valid;
    }

    public static boolean isValidModel(String mod) {
        if (mod.length() < 3 || mod.length() > 20) {
            return false;
        }
        for (int i = 0; i < mod.length(); i++) {
            if (!Character.isAlphabetic(mod.charAt(i)) && !Character.isDigit(mod.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidAmount(String amount) {
        try {
            int a = Integer.parseInt(amount);
            if (a > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidDistance(String amount) {
        try {
            double a = Double.parseDouble(amount);
            if (a > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
