package io.lordlambda.apollo.ai.behavior.behaviors;

import io.lordlambda.apollo.ai.behavior.Behavior;

/**
 * Creator: LordLambda
 * Date: 12/3/14.
 * Project: Apollo
 * Usage: Rage behavior
 */
public class Rage implements Behavior {

    //Note this const hasn't been tested. It's an example to show your more likely to make rash decisions so closer to 1.
    @Override
    public float getConst() {return 0.782231452357124945f;}

    @Override
    public String getName() {return "Rage";}

    @Override
    public boolean isNeutral() {return false;}
}