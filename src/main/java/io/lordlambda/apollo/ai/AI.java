package io.lordlambda.apollo.ai;

import io.lordlambda.apollo.ai.action.Action;
import io.lordlambda.apollo.ai.behavior.Behavior;

import java.util.PriorityQueue;

/**
 * Creator: LordLambda
 * Date: 11/14/14.
 * Project: Apollo
 * Usage: The base AI class.
 */
public abstract class AI {
    //TODO: This.
    Behavior currentBehavior;
    PriorityQueue<Action> actionQueue;

    public abstract void calcNextMove();

    private void defaultCalculation() {
        if(currentBehavior.isNeutral()) {

        }else {

        }
    }
}