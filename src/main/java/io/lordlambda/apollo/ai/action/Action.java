package io.lordlambda.apollo.ai.action;

import org.spongepowered.api.entity.living.Living;

import java.util.UUID;

/**
 * Creator: LordLambda
 * Date: 11/16/14.
 * Project: Apollo
 * Usage: Action interface
 */
public interface Action {

    /**
     * Perform this action.
     */
    void doAction(Living e);

    /**
     * @return
     *  The current action name.
     */
    String actionName();

    /**
     * @return
     *  The Action ID
     */
    UUID getActionID();
}