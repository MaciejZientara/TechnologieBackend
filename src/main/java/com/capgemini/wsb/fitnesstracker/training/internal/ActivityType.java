package com.capgemini.wsb.fitnesstracker.training.internal;

/**
 * Enumeration representing various types of physical activities.
 * Each activity type is associated with a human-readable display name.
 * The available activity types include running, cycling, walking, swimming, and tennis.
 */
public enum ActivityType {

    RUNNING("Running"),
    CYCLING("Cycling"),
    WALKING("Walking"),
    SWIMMING("Swimming"),
    TENNIS("Tenis");

    /**
     * A human-readable name for the activity type.
     */
    private final String displayName;

    /**
     * Constructs an {@code ActivityType} with a specified display name.
     *
     * @param displayName the human-readable name of the activity type
     */
    ActivityType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Retrieves the display name of the activity type.
     *
     * @return the display name as a {@link String}
     */
    public String getDisplayName() {
        return displayName;
    }

}
