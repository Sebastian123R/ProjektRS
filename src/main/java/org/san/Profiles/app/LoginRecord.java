package org.san.Profiles.app;

import org.san.Profiles.Email;
import org.san.Profiles.Login;

public record LoginRecord(Email login) implements Login {
}
