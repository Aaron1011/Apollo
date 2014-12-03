package io.lordlambda.apollo.ai;

import io.lordlambda.apollo.Apollo;
import io.lordlambda.apollo.events.PredicitionFinalEvent;
import io.lordlambda.apollo.io.RFileManager;
import io.lordlambda.apollo.math.DataSet;
import rcaller.RCode;
import rcaller.ROutputParser;

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
    DataSet data;

    public Predict(UUID toPredict, UUID self, DataSet data) {
        toPredictFor = toPredict;
        this.self = self;
        this.data = data;
    }

    @Override
    public void run() {
        if(Difficuluty.getCurrentDifficulty() == Difficuluty.HIGHEST) {

        }else if(Difficuluty.getCurrentDifficulty() == Difficuluty.HIGH) {
            RCode rc = data.getDataAsDataFrame(toPredictFor.toString());
            RFileManager executor = RFileManager.getSelf();
            String key = String.format("%d-%s", System.currentTimeMillis(), toPredictFor.toString());
            executor.addRCode(rc, key);
            RCode predict = new RCode();
            //Example on run check
            predict.addRCode(String.format("res <- predict(%s, newdata=%s, interval='confidence')", "onRun", toPredictFor.toString()));
            executor.addRCode(predict, key);
            ROutputParser rop = executor.runR(key, "res");
        }
        Apollo.getApollo().getGame().getEventManager().post(new PredicitionFinalEvent(new long[]{}, toPredictFor, self));
    }
}