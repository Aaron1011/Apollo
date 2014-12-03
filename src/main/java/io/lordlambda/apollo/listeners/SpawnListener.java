package io.lordlambda.apollo.listeners;

import io.lordlambda.apollo.Apollo;
import io.lordlambda.apollo.ai.AI;
import io.lordlambda.apollo.ai.AIManager;
import io.lordlambda.apollo.io.XMLConfiguration;
import io.lordlambda.apollo.math.VectorMath;
import io.lordlambda.apollo.world.*;
import io.lordlambda.apollo.world.types.Region2D;
import io.lordlambda.apollo.world.types.Region3D;
import org.apache.log4j.Level;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.Living;
import org.spongepowered.api.event.entity.EntitySpawnEvent;
import org.spongepowered.api.math.Vector3d;
import org.spongepowered.api.math.Vector3f;
import org.spongepowered.api.math.Vectors;
import org.spongepowered.api.util.event.Subscribe;

import java.util.List;

/**
 * Creator: LordLambda
 * Date: 11/12/14.
 * Project: Apollo
 * Usage: Listens for an Entity Spawn event, determines if it's in points
 */
public class SpawnListener {

    /////////////////////////////////////////////////////////////
    //  Variables
    //
    //      Class Variables
    //          one - A boolean to determine if the
    //              configuration file has been checked.
    //          two - A boolean to determine if spawn enforcement
    //              is on.
    //
    /////////////////////////////////////////////////////////////
    boolean one = false;
    boolean two = false;

    /**
     * Called on an entity spawn event.
     * @param event
     *  The entity spawning event.
     * </p>
     * Here we parse through the list of entites spawned on this event, and determine if we need
     * to remove any entities from spawning due to them not being in a correct region.
     * </p>
     * Note this supports 2D, and 3D spawn regions.
     */
    @Subscribe
    public void entitySpawnEvent(EntitySpawnEvent event) {
        if(one) {
            if (two) {
                List<Region> spawnRegions = RegionManager.getSelf().getSpawnRegions();
                AIManager am = AIManager.getSelf();
                Entity e = event.getEntity();
                    if(!(spawnRegions.size() == 0)) {
                        for (Region r : spawnRegions) {
                            if (r instanceof Region3D) {
                                Region3D ddd = (Region3D) r;
                                Vector3d toConvert = e.getPosition();
                                Vector3f converted = Vectors.create3f((float) toConvert.getX(), (float) toConvert.getY(), (float) toConvert.getZ());

                                if (!(ddd.pointInRegion(converted))) {
                                    e.remove();
                                }
                                else {
                                    if(am.aiExists(e.getClass().getSimpleName())) {
                                        Class clazz = am.getAIClass(e.getClass().getSimpleName());
                                        AI ai;
                                        try {
                                            ai = (AI) clazz.getConstructor(Living.class).newInstance(e);
                                        }catch(Exception e1) {
                                            Apollo.getApollo().log(Level.ERROR, String.format("Creating new AI instance Error: %s", e1.getLocalizedMessage()));
                                            break;
                                        }
                                        e.remove();
                                        ai.spawnAI();
                                        break;
                                    }else {
                                        break;
                                    }
                                }
                            }
                            else if (r instanceof Region2D) {
                                Region2D dd = (Region2D) r;
                                Vector3d toConvert = e.getPosition();
                                Vector3f converted = Vectors.create3f((float) toConvert.getX(), (float) toConvert.getY(), (float) toConvert.getZ());
                                if (!(dd.pointInRegion(VectorMath.vector3ToVector2(converted, e.getWorld())))) {
                                    e.remove();
                                }
                                else {
                                    break;
                                }
                            }
                        }
                    }else {
                        if(am.aiExists(e.getClass().getSimpleName())) {
                            Class clazz = am.getAIClass(e.getClass().getSimpleName());
                            AI ai;
                            try {
                                ai = (AI) clazz.getConstructor(Living.class).newInstance(e);
                            }catch(Exception e1) {
                                Apollo.getApollo().log(Level.ERROR, String.format("Creating new AI instance Error: %s", e1.getLocalizedMessage()));
                                return;
                            }
                            e.remove();
                            ai.spawnAI();
                            return;
                        }else {
                            return;
                        }
                    }
            }
        }else {
            one = true;
            two = XMLConfiguration.getSelf().parseValue("configuration.xml", "configuration", "forceSpawnRegions")
                                  .equalsIgnoreCase("true");
            entitySpawnEvent(event);
        }
    }
}