package org.san.Profiles;

import java.util.Optional;

public interface ProfilesRepository {

    void addNewProfile(Profile profile);

    Optional<Profile> findProfileById(ProfileId profileId);

    void changePassword(ProfileId profileId, Password oldPassword, Password newPassword);

    void changeEmail(ProfileId profileId, Email email);

    void deleteProfile(ProfileId profileId);
}
