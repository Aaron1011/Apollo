package io.lordlambda.apollo.ai.action;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.UUID;

/**
 * Creator: LordLambda
 * Date: 12/3/14.
 * Project: Apollo
 * Usage: Manages Actions
 */
public class ActionManager {

    /////////////////////////////////////////////////////////////
    //  Variables
    //      Class Variables
    //        self - A static instance of itself
    //        map - A map of Action -> ID
    //
    /////////////////////////////////////////////////////////////
    static ActionManager self;
    LinkedHashMap<Action, UUID> map;

    public ActionManager() {
        self = this;
        map = new LinkedHashMap<>();
    }

    @Nullable
    public static ActionManager getSelf() {
        return self;
    }

    public UUID genUUID(Action a) {
        if(map.containsKey(a)) {
            return map.get(a);
        }
        while(true) {
            UUID toTest = UUID.randomUUID();
            if(!map.containsValue(toTest)) {
                return toTest;
            }
        }
    }

    public void register(Action a, UUID uuid) {
        map.put(a, uuid);
    }

    public boolean isRegistered(Action a) {
        return map.containsKey(a);
    }

    public boolean isRegistered(UUID uuid) {
        return map.containsValue(uuid);
    }

    public void unregister(Action a) {
        if(map.containsKey(a))  map.remove(a);
    }
}