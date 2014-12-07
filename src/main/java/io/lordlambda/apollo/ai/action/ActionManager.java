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
        if(!map.containsKey(a))
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

    @Nullable
    public Action getReverse(UUID id) {
        for(int i = 0; i < map.values().size(); ++i) {
            if((UUID) map.values().toArray()[i] == id) {
                return (Action) map.keySet().toArray()[i];
            }
        }
        return null;
    }

    @Nullable
    public UUID get(Action a) {
        return map.get(a);
    }

    @Nullable
    public Action getActionByName(String name) {
        for(Action a : map.keySet()) {
            if(a.actionName().equalsIgnoreCase(name)) {
                return a;
            }
        }
        return null;
    }
}