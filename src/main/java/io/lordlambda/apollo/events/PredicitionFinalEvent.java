package io.lordlambda.apollo.events;

import org.spongepowered.api.event.Event;
import org.spongepowered.api.event.Result;

import java.util.UUID;

/**
 * Creator: LordLambda
 * Date: 11/17/14.
 * Project: Apollo
 * Usage: Predicition Final event
 */
public class PredicitionFinalEvent implements Event {

    long[] predicted;
    UUID entityTargeting;

    public PredicitionFinalEvent(long[] result, UUID id) {
        predicted = result;
        entityTargeting = id;
    }

    @Override
    public boolean isCancellable() {
        return false;
    }

    @Override
    public Result getResult() {
        return Result.DEFAULT;
    }

    @Override
    public void setResult(Result result) {
        /**
         * This event can't have a result set, it only returns a result.
         */
    }

    public long[] getPredicition() {
        return predicted;
    }

    public UUID getEntityTargeting() {return entityTargeting;}
}