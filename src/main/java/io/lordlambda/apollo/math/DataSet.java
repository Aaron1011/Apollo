package io.lordlambda.apollo.math;

import javax.annotation.Nullable;

import rcaller.RCode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Creator: LordLambda
 * Date: 11/26/14.
 * Project: Apollo
 * Usage: A Data set.
 */
public class DataSet {

    /////////////////////////////////////////////////////////////
    //  Variables
    //      Class Variables
    //        map - A LinkedHashMap of Name of Data -> Data to
    //          store.
    //
    /////////////////////////////////////////////////////////////

    LinkedHashMap<String, List<BigDecimal>> map;

    /**
     * Default Constructor
     */
    public DataSet() {
        map = new LinkedHashMap<>();
    }

    /**
     * Get The data stored as RCode
     * @return
     *  The data stored as an RCode Vector.
     */
    public RCode getDataAsRCode() {
        RCode rc = new RCode();
        for(String s : map.keySet()) {
            String toAdd = String.format("%s <- (", s);
            int argc = map.get(s).size();
            for(int i = 0; i < argc; i++) {
                if(i == 0)
                    toAdd += String.format("%09.4f", (BigDecimal) map.get(s).toArray()[i]);
                else
                    toAdd += String.format(", %09.4f", (BigDecimal) map.get(s).toArray()[i]);
            }
            rc.addRCode(String.format("%s)", toAdd));
        }
        return rc;
    }

    /**
     * Get the data stored as RCode
     * @return
     *  The data stored as an RCode Data Frame. The Data frame has a name of "frame.data".
     */
    public RCode getDataAsDataFrame() {
        RCode rc = new RCode();
        List<String> names = new ArrayList<>();
        for(String s : map.keySet()) {
            names.add(s);
            String toAdd = String.format("%s <- (", s);
            int argc = map.get(s).size();
            for(int i = 0; i < argc; i++) {
                if(i == 0)
                    toAdd += String.format("%09.4f", (BigDecimal) map.get(s).toArray()[i]);
                else
                    toAdd += String.format(", %09.4f", (BigDecimal) map.get(s).toArray()[i]);
            }
            rc.addRCode(String.format("%s)", toAdd));
        }
        String toAdd = "";
        for(int i = 0; i < names.size(); ++i) {
            if(i == 0) {
                toAdd += (String.format("frame.data <- data.frame(%s", names.get(i)));
            }else {
                toAdd += (String.format(", %s", names.get(i)));
            }
        }
        toAdd += ", stringsAsFactors=FALSE)";
        rc.addRCode(toAdd);
        return rc;
    }

    /**
     * Get The Data Stored as RCode
     * @param nameOfFrame
     *  What to name the data frame
     * @return
     *  The data stored as RCode within DataFrame with a <code>nameOfFrame</code>.
     */
    public RCode getDataAsDataFrame(String nameOfFrame) {
        RCode rc = new RCode();
        List<String> names = new ArrayList<>();
        for(String s : map.keySet()) {
            names.add(s);
            String toAdd = String.format("%s <- (", s);
            int argc = map.get(s).size();
            for(int i = 0; i < argc; i++) {
                if(i == 0)
                    toAdd += String.format("%09.4f", (BigDecimal) map.get(s).toArray()[i]);
                else
                    toAdd += String.format(", %09.4f", (BigDecimal) map.get(s).toArray()[i]);
            }
            rc.addRCode(String.format("%s)", toAdd));
        }
        String toAdd = "";
        for(int i = 0; i < names.size(); ++i) {
            if(i == 0) {
                toAdd += (String.format("%s <- data.frame(%s", nameOfFrame, names.get(i)));
            }else {
                toAdd += (String.format(", %s", names.get(i)));
            }
        }
        toAdd += ", stringsAsFactors=FALSE)";
        rc.addRCode(toAdd);
        return rc;
    }

    /**
     * If the key exists in the data set
     * @param s
     *  The key to check.
     * @return
     *  If the key <code>s</code> exists in the data set.
     */
    public boolean keyExists(String s) {
        return map.keySet().contains(s);
    }

    /**
     * Check if a value is in this data set.
     * @param lbd
     *  The list of big decimals (the data).
     * @return
     *  If <code>lbd</code> exists in the data set.
     */
    public boolean valueExists(List<BigDecimal> lbd) {
        return map.values().contains(lbd);
    }

    /**
     * Where the data exists at. (So we can grab the key since linked).
     * @param lbd
     *  The list of big decimals (the data).
     * @return
     *  Where <code>lbd</code> is located.
     */
    public int valueExistsAt(List<BigDecimal> lbd) {
        if(valueExists(lbd)) {
           for(int i = 0; i < map.values().size(); ++i) {
               if((List<BigDecimal>) map.values().toArray()[i] == lbd) {
                   return i;
               }
           }
        }
        return -1;
    }

    /**
     * Get a List of big decimals (the data) based off a key.
     * @param s
     *  The key to check.
     * @return
     *  The data that correlates with <code>s</code>.
     */
    @Nullable
    public List<BigDecimal> get(String s) {
        if(keyExists(s)) {
            return map.get(s);
        }
        return null;
    }

    /**
     * Get the key based off of the list of big decimals (the data).
     * @param lbd
     *  The list of big decimals (the data).
     * @return
     *  The key that correlates with <code>lbd</code>.
     */
    @Nullable
    public String getReverse(List<BigDecimal> lbd) {
        if(valueExists(lbd)) {
            return (String) map.keySet().toArray()[valueExistsAt(lbd)];
        }
        return null;
    }

    /**
     * Remove a value/key pair from data set by the key
     * @param s
     *  The key.
     * @throws IllegalArgumentException
     *  If the key does not exist.
     */
    public void removeValueByKey(String s) throws IllegalArgumentException {
        if(keyExists(s)) {
            map.remove(s);
        }else
            throw new IllegalArgumentException(String.format("%s does not exist om DataSet!!", s));
    }

    /**
     * Remove a value/key pair by its value.
     * @param lbd
     *  The Value.
     * @throws IllegalArgumentException
     *  If the value does not exist.
     */
    public void removeValueByValue(List<BigDecimal> lbd) throws IllegalArgumentException {
        if(valueExists(lbd)) {
            map.remove(getReverse(lbd));
        }else
            throw new IllegalArgumentException(String.format("Value %s does not exist!!!", lbd.toString()));
    }

    /**
     * Set a piece of data
     * @param key
     *  The key of the data to set.
     * @param lbd
     *  The data part of the data to set.
     * </p>
     * Note! This will override any value in the data set!
     */
    public void set(String key, List<BigDecimal> lbd) {
        if(keyExists(key)) {
            removeValueByKey(key);
        }
        map.put(key, lbd);
    }

    /**
     * Adds one big decimal to an already existing value
     * @param key
     *  The key of the data to add
     * @param toAdd
     *  The data to add.
     * @throws IllegalArgumentException
     *  If the key does not exist
     */
    public void addBigDecimalToValueList(String key, BigDecimal toAdd) throws IllegalArgumentException {
        if(!(keyExists(key))) {
            throw new IllegalArgumentException(String.format("%s does not exist in DataSet!", key));
        }
        List<BigDecimal> toSet = get(key);
        toSet.add(toAdd);
        set(key, toSet);
    }

    /**
     * Adds a list big decimal to an already existing value
     * @param key
     *  The key of the data to add
     * @param toAdd
     *  The data to add.
     * @throws IllegalArgumentException
     *  If the key does not exist
     */
    public void addBigDecimalListToValueSet(String key, List<BigDecimal> toAdd) throws IllegalArgumentException {
        for(BigDecimal bd : toAdd) {
            addBigDecimalToValueList(key, bd);
        }
    }

    /**
     * Adds data to the data set. Regardless of existance
     * @param key
     *  The key of the data to add.
     * @param toAdd
     *  The data to add.
     * </p>
     * This add data function will do one of two things:
     * 1. If the key exists it will simply just append the data.
     * 2. If the key does not exist it will create the key with the data.
     */
    public void addData(String key, List<BigDecimal> toAdd) {
        if(!keyExists(key)) {
            set(key, toAdd);
        }else {
            addBigDecimalListToValueSet(key, toAdd);
        }
    }
}