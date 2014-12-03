package io.lordlambda.apollo.events;

import org.spongepowered.api.util.event.Event;
import org.spongepowered.api.util.event.callback.CallbackList;

import java.util.UUID;

/**
 * Creator: LordLambda
 * Date: 11/17/14.
 * Project: Apollo
 * Usage: Predicition Final event
 */
public class PredicitionFinalEvent implements Event {

    long[] predicted;
    UUID entityTargeting, entitySelf;

    public PredicitionFinalEvent(long[] result, UUID id, UUID self) {
        predicted = result;
        entityTargeting = id;
        entitySelf = self;
    }

    public CallbackList getCallbacks() {
        return null; //Fix Later
    }

    public long[] getPredicition() {
        return predicted;
    }

    public UUID getEntityTargeting() {return entityTargeting;}

    public UUID getSelf() {
        return entitySelf;
    }
}