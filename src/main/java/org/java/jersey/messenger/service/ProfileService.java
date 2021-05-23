
package org.java.jersey.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.java.jersey.messenger.database.DatabaseClass;
import org.java.jersey.messenger.model.Profile;

public class ProfileService {

    public ProfileService() {
        profiles.put("Janatan", new Profile(1L, "Janatan", "Janatan", "kothagal"));
    }

    private final Map<String, Profile> profiles = DatabaseClass.getProfiles();

    public List<Profile> getAllProfiles() {
        return new ArrayList<>(profiles.values());
    }

    public Profile getProfile(final String profileName) {
        return profiles.get(profileName);
    }

    public Profile addProfile(final Profile profile) {
        profile.setId(profiles.size() + 1);
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile updateMessage(final Profile profile) {
        if (profile.getProfileName().isEmpty()) {
            return null;
        }
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile removeMessage(final String profileName) {
        return profiles.remove(profileName);

    }

}
