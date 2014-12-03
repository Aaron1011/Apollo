package io.lordlambda.apollo.ai.behavior.behaviors;

import io.lordlambda.apollo.ai.behavior.Behavior;

/**
 * Creator: LordLambda
 * Date: 12/3/14.
 * Project: Apollo
 * Usage: Peaceful Behavior
 */
public class Peaceful implements Behavior {

    //Note this const hasn't been fully tested just an example to show less rash so closer to 0
    @Override
    public float getConst() {return 0.234164012571234f;}

    @Override
    public String getName() {return "Peaceful";}

    @Override
    public boolean isNeutral() {return true;}
}