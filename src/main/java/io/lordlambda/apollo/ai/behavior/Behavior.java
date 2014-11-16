package io.lordlambda.apollo.ai.behavior;

/**
 * Creator: LordLambda
 * Date: 11/14/14.
 * Project: Apollo
 * Usage: Behavior Manager
 */
public interface Behavior {

    /**
     * The const value for this behavior for calculations
     * @return
     *  The const
     * </p>
     * The constant value for each behavior is a number anywhere from 0 to 1.
     * This constant value is how "vain" the behavior makes the decisions act.
     */
    float getConst();

    /**
     * The name of the behavior
     * @return
     *  The user name to see.
     */
    String getName();

    /**
     * If the ai is netural
     * @return
     *  If the AI is.
     */
    boolean isNeutral();
}