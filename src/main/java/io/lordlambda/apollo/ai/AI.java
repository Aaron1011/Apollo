package io.lordlambda.apollo.ai;

import io.lordlambda.apollo.ai.action.Action;
import io.lordlambda.apollo.ai.behavior.Behavior;
import io.lordlambda.apollo.events.PredicitionFinalEvent;
import org.spongepowered.api.entity.LivingEntity;
import org.spongepowered.api.event.Subscribe;

import java.util.PriorityQueue;
import java.util.UUID;

/**
 * Creator: LordLambda
 * Date: 11/14/14.
 * Project: Apollo
 * Usage: The base AI class.
 */
public abstract class AI {
    //TODO: This.
    Behavior currentBehavior;
    UUID id;
    LivingEntity entity;
    PriorityQueue<Action> actionQueue;

    public AI(LivingEntity entity) {
        currentBehavior = null;
        id = UUID.randomUUID();
        this.entity = entity;
        actionQueue = new PriorityQueue<>();
    }

    public abstract void calcNextMove();

    private void defaultCalculation() {
        if(currentBehavior.isNeutral()) {

        }else {

        }
    }

    private void defaultCalculation(PredicitionFinalEvent event) {

    }

    @Subscribe
    public abstract void reAssesGoals(PredicitionFinalEvent event);

    public void spawnAI() {

    }
}