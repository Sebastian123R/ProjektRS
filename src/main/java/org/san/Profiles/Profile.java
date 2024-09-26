package org.san.Profiles;

import java.time.LocalDate;

public interface Profile {
    ProfileId profileId();
    Login login();
    Password password();
    ProfileUserInformation profileUserInformation();
    ProfileHistory profileHistory();
    LocalDate lastLoggedIn();
}