package org.san.Profiles.app;

import org.san.Profiles.*;

import java.time.LocalDate;

public record ProfileRecord(ProfileId profileId,
                            Login login,
                            Password password,
                            ProfileUserInformation profileUserInformation,
                            ProfileHistory profileHistory,
                            LocalDate lastLoggedIn) implements Profile {
}
