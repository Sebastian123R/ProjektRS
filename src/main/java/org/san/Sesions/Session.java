package org.san.Sesions;

import org.san.Profiles.Profile;

public interface Session {
    SessionId sessionId();
    Profile profile();
    SessionParameters sessionParameters();
}
