package io.lordlambda.apollo.ai;

/**
 * Creator: LordLambda
 * Date: 11/16/14.
 * Project: Apollo
 * Usage: Manages Predicitions
 */
public class PredicitionManager {

    static PredicitionManager pm;


    public PredicitionManager() {
        pm = this;
    }

    public static PredicitionManager getSelf() {return pm;}
}