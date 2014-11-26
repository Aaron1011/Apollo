package io.lordlambda.apollo.math;

import javax.annotation.Nullable;

import rcaller.RCode;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Creator: LordLambda
 * Date: 11/26/14.
 * Project: Apollo
 * Usage: A Data set.
 */
public class DataSet {

    //TODO: This

    LinkedHashMap<String, List<BigDecimal>> map;

    public DataSet() {
        map = new LinkedHashMap<>();
    }

    public RCode getDataAsRCode() {
        RCode rc = new RCode();
        for(String s : map.keySet()) {
            String toAdd = String.format("%s <- (", s);
            int argc = map.get(s).size();
            for(int i = 0; i < argc; i++) {
                if(i == 0)
                    toAdd += String.format("%09.4f", (BigDecimal) map.get(s).toArray()[i]);
                else
                    toAdd += String.format(",%09.4f", (BigDecimal) map.get(s).toArray()[i]);
            }
            rc.addRCode(String.format("%s)", toAdd));
        }
        return rc;
    }

    public boolean keyExists(String s) {
        return map.keySet().contains(s);
    }

    public boolean valueExists(List<BigDecimal> lbd) {
        return map.values().contains(lbd);
    }

    @Nullable
    public List<BigDecimal> get(String s) {
        if(keyExists(s)) {
            return map.get(s);
        }
        return null;
    }
}