package io.lordlambda.apollo.ai.action.actions;

import io.lordlambda.apollo.ai.action.Action;
import org.spongepowered.api.entity.living.Living;

import java.util.UUID;

/**
 * Creator: LordLambda
 * Date: 11/16/14.
 * Project: Apollo
 * Usage: A default standing action
 */
public class StandingAction implements Action {

    @Override
    public void doAction(Living e) {
        
    }

    @Override
    public String actionName() {return "Stand";}

    @Override
    public UUID getActionID() {return UUID.randomUUID();}
}