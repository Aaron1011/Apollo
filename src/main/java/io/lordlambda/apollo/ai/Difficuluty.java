package io.lordlambda.apollo.ai;

import io.lordlambda.apollo.io.XMLConfiguration;

/**
 * Creator: LordLambda
 * Date: 11/26/14.
 * Project: Apollo
 * Usage: The Difficulty enu,
 */
public enum Difficuluty {
    LOWEST,
    LOW,
    MEDIUM,
    HIGH,
    HIGHEST;

    public static Difficuluty getCurrentDifficulty() {
        String s = XMLConfiguration.getSelf().parseValue("configuration.xml", "configuration", "mobDifficulty");
        return Difficuluty.valueOf(s);
    }
}