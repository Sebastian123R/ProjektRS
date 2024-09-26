package org.san.Profiles.app;

import org.san.Profiles.*;

import java.util.Optional;

public class ProfilesRepositoryImpl implements ProfilesRepository {
    @Override
    public void addNewProfile(Profile profile) {

    }

    @Override
    public Optional<Profile> findProfileById(ProfileId profileId) {
        return Optional.empty();
    }

    @Override
    public void changePassword(ProfileId profileId, Password oldPassword, Password newPassword) {

    }

    @Override
    public void changeEmail(ProfileId profileId, EmailRecord email) {

    }

    @Override
    public void deleteProfile(ProfileId profileId) {

    }
}
