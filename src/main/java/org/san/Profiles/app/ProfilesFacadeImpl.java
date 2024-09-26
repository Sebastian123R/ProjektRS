package org.san.Profiles.app;

import org.san.Profiles.ProfilesFacade;

import java.util.regex.Pattern;

public class ProfilesFacadeImpl implements ProfilesFacade {
    @Override
    public boolean isLoggedIn() {
        return false;
    }

    @Override
    public boolean isCorrectEmail(String email) {
        final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

        if (email == null) {

            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }
}
