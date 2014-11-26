package io.lordlambda.apollo.ai;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;

/**
 * Creator: LordLambda
 * Date: 11/25/14.
 * Project: Apollo
 * Usage: Manages AI Class
 */
public class AIManager {

    /////////////////////////////////////////////////////////////
    //  Variables
    //
    //      Class Variables
    //        entityAIMap - A map of the name of entity -> it's
    //          AI class.
    //        am - A static instance of self.
    //
    /////////////////////////////////////////////////////////////

    LinkedHashMap<String, Class> entityAIMap;
    static AIManager am;

    public AIManager() {
        am = this;
        entityAIMap = new LinkedHashMap<>();
    }

    /**
     * Get the class for the entity
     * @param entityName
     *  The entity name to get the class for
     * @return
     *  The class for specified entity.
     */
    @Nullable
    public Class getAIClass(String entityName) {
        if(entityAIMap.containsKey(entityName)) {
            return entityAIMap.get(entityName);
        }
        return null;
    }

    /**
     * If an AI exists for entity
     * @param entityName
     *  The entity name.
     * @return
     *  The AI class.
     */
    public boolean aiExists(String entityName) {
        return entityAIMap.containsKey(entityName);
    }

    /**
     * Registers the AI Class
     * @param entityName
     *  The entity Name to register for
     * @param aiClass
     *  The class to set up.
     * @throws IllegalArgumentException
     *  If the passed class does not extend AI.
     */
    public void registerAIClass(String entityName, Class aiClass) throws IllegalArgumentException {
        if(aiClass.getSuperclass() != AI.class) {
            throw new IllegalArgumentException("Class for new entity does not extend AI!");
        }
        if(aiExists(entityName)) {
            entityAIMap.remove(entityName);
        }
        entityAIMap.put(entityName, aiClass);
    }

    /**
     * @return
     *  The static instance of AIManager
     */
    @Nullable
    public static AIManager getSelf() {return am;}
}