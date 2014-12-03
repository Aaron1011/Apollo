package io.lordlambda.apollo.ai;

import io.lordlambda.apollo.Apollo;
import io.lordlambda.apollo.ai.action.Action;
import io.lordlambda.apollo.ai.behavior.Behavior;
import io.lordlambda.apollo.events.PredicitionFinalEvent;
import org.spongepowered.api.entity.living.Living;
import org.spongepowered.api.util.event.Subscribe;

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
    Living entity;
    PriorityQueue<Action> actionQueue;

    public AI(Living entity) {
        currentBehavior = null;
        id = UUID.randomUUID();
        this.entity = entity;
        actionQueue = new PriorityQueue<>();
        Apollo.getApollo().getGame().getEventManager().register(Apollo.getApollo(), this);
    }

    public abstract void calcNextMove();

    private void defaultCalculation() {
        if(currentBehavior.isNeutral()) {

        }else {

        }
    }

    private void defaultCalculation(PredicitionFinalEvent event) {

    }

    public abstract void reAssesGoals(PredicitionFinalEvent event);

    @Subscribe
    public void eventHandler(PredicitionFinalEvent event) {
        if(event.getSelf() == id) {
            reAssesGoals(event);
        }
    }

    public abstract void spawnAI();
}