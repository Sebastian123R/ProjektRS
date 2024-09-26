package org.san.Profiles;

public interface ProfilesFacade {

    boolean isLoggedIn();

    boolean isCorrectEmail(String email);

    boolean isCorrectPassword(String password);
}
