package org.san.Profiles;

import java.time.LocalDate;

public interface Profile {
    ProfileId profileId();
    Login login();
    Password password();
    ProfileUserInformation profileUserInformation();
    ProfileSettings profileSettings();
    ProfileHistory profileHistory();
    LocalDate lastLoggedIn();
}