package io.lordlambda.apollo;

import io.lordlambda.apollo.ai.AIManager;
import io.lordlambda.apollo.ai.PredicitionManager;
import io.lordlambda.apollo.ai.action.ActionManager;
import io.lordlambda.apollo.entropy.EntropyGenerator;
import io.lordlambda.apollo.io.RFileManager;
import io.lordlambda.apollo.io.XMLConfiguration;
import io.lordlambda.apollo.listeners.SpawnListener;
import io.lordlambda.apollo.world.RegionManager;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.event.state.PreInitializationEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.util.event.Subscribe;

import javax.annotation.Nullable;

/**
 * Creator: LordLambda
 * Date: 11/12/14.
 * Project: Apollo
 * Usage: Apollo main class
 */
@Plugin(id="apollo", name="Apollo AI")
public class Apollo {

    /////////////////////////////////////////////////////////////
    //
    //                               ___   ___
    //                              (   ) (   )
    //    .---.     .-..     .--.    | |   | |    .--.
    //   / .-, \   /    \   /    \   | |   | |   /    \
    //  (__) ; |  ' .-,  ; |  .-. ;  | |   | |  |  .-. ;
    //    .'`  |  | |  . | | |  | |  | |   | |  | |  | |
    //   / .'| |  | |  | | | |  | |  | |   | |  | |  | |
    //  | /  | |  | |  | | | |  | |  | |   | |  | |  | |
    //  ; |  ; |  | |  ' | | '  | |  | |   | |  | '  | |
    //  ' `-'  |  | `-'  ' '  `-' /  | |   | |  '  `-' /
    //  `.__.'_.  | \__.'   `.__.'  (___) (___)  `.__.'
    //            | |
    //           (___)
    //
    //   Sorry for the cheesy ASCII art. I felt it was needed.
    /////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////
    //  Variables
    //
    //      Sponge Hook Ins
    //        g - The Sponge Game instance. (Private)
    //        logger - The Logger instance. (Private)
    //
    //      Class Variables
    //        apollo - A static instance of apollo to get
    //          statically.
    //        useR - Checks if we should use R scripts for
    //          our config.
    //
    /////////////////////////////////////////////////////////////
    private Game g;
    private Logger logger;

    static Apollo apollo;
    boolean useR;

    /**
     * Default constructor.
     */
    public Apollo() {
        apollo = this;
    }


    /**
     * Pre initialization function
     * @param event
     *  The pre initialization event
     * </p>
     * The goal here is to:
     *  1. Grab sponge instances.
     *  2. Create instances of managers so people can statically get them later.
     *  3. Register Listeners
     *  4. Check sponge support version.
     */
    @Subscribe
    public void preInit(PreInitializationEvent event) {
        /////////////////////////////////////////////////////////////
        //
        // Check sponge version.
        //
        /////////////////////////////////////////////////////////////
        if(!(VersionHandler.versionSupported(g.getAPIVersion()))) {
            event.getPluginLog().error("Sponge version not supported!");
            System.exit(-1);
        }
        event.getPluginLog().info("[Apollo] Starting Up....");
        /////////////////////////////////////////////////////////////
        //
        // Hook sponge necessities.
        //
        /////////////////////////////////////////////////////////////
        logger = (Logger) event.getPluginLog(); //Intellij wants this cause the sponge logger is in a different package. Same logger though
        g = event.getGame();

        log("Hooked sponged necessities...");

        log("Starting Managers...");

        /////////////////////////////////////////////////////////////
        //
        // Initialize Managers.
        //
        /////////////////////////////////////////////////////////////
        log("Starting XML Manager...");
        new XMLConfiguration("configuration.xml");
        log("XML Manager started...");
        if(XMLConfiguration.getSelf().parseValue("configuration.xml", "configuration", "useR").equalsIgnoreCase("true")) {
            log("Using R Language... Starting R Manager...");
            useR = true;
            new RFileManager();
        }else {
            log("Language R is not being used... Setting up Replacement...");
            useR = false;
        }
        log("Starting Entropy Generator...");
        new EntropyGenerator();
        log("Done... Starting Region Manager...");
        new RegionManager();
        log("Done... Starting Predicition Manager...");
        new PredicitionManager();
        log("Done... Starting Action Manager...");
        new ActionManager();
        log("Done. Finally starting AI Manager...");
        new AIManager();
        log("Done. Registering Listeners...");


        /////////////////////////////////////////////////////////////
        //
        // Register Listeners
        //
        /////////////////////////////////////////////////////////////

        log("Registering Spawn Listener...");
        g.getEventManager().register(this, new SpawnListener());
        log("Done. Started.");
    }

    /**
     * @return
     *  The static instance of apollo
     */
    @Nullable
    public static Apollo getApollo() {return apollo;}

    /**
     * A log function with a little more options
     * @param level
     *  The level to log at.
     * @param s
     *  The string to log.
     */
    public void log(Level level, String s) {
        logger.log(level, String.format("[Apollo] %s", s));
    }

    /**
     * Your default log function.
     * @param s
     *  The string to log.
     */
    public void log(String s) {
        log(Level.INFO, s);
    }

    /**
     * Get the sponge game instance.
     * @return
     *  The game instance;
     */
    @Nullable
    public Game getGame() {return g;}

    /**
     * Check if I am using R for heuristics (for anyone that cares).
     * @return
     *  If I am using R.
     */
    public boolean usingR() {return useR;}
}