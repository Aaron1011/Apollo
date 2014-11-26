package io.lordlambda.apollo.ai;

import io.lordlambda.apollo.ai.action.Action;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;

/**
 * Creator: LordLambda
 * Date: 11/16/14.
 * Project: Apollo
 * Usage: Manages Predicitions
 */
public class PredicitionManager {
    //TODO: This
    static PredicitionManager pm;
    LinkedHashMap<AI, Action> predicitions;


    public PredicitionManager() {
        pm = this;
        predicitions = new LinkedHashMap<>();
    }

    @Nullable
    public static PredicitionManager getSelf() {return pm;}

}