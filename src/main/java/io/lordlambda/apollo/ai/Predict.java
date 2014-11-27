package io.lordlambda.apollo.ai;

import io.lordlambda.apollo.Apollo;
import io.lordlambda.apollo.events.PredicitionFinalEvent;

import java.util.UUID;

/**
 * Creator: LordLambda
 * Date: 11/17/14.
 * Project: Apollo
 * Usage: Predict
 */
public class Predict extends Thread {

    //TODO: THIS

    UUID toPredictFor;
    UUID self;
    /*DataSet data;*/

    public Predict(UUID toPredict, UUID self/*, DataSet data*/) {
        toPredictFor = toPredict;
        this.self = self;
        /*this.data = data;*/
    }

    @Override
    public void run() {

        Apollo.getApollo().getGame().getEventManager().post(new PredicitionFinalEvent(new long[]{}, toPredictFor, self));
    }
}