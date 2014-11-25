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
    /*DataSet data;*/

    public Predict(UUID toPredict/*, DataSet data*/) {
        toPredictFor = toPredict;
        /*this.data = data;*/
    }

    @Override
    public void run() {

        Apollo.getApollo().getGame().getEventManager().call(new PredicitionFinalEvent(new long[]{}, toPredictFor));
    }
}