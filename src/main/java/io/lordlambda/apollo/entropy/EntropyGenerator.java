package io.lordlambda.apollo.entropy;

import com.sk89q.intake.Command;
import com.sk89q.intake.Require;
import io.lordlambda.apollo.diehard.RNG;
import io.rev.splash.component.ComponentLoader;
import io.rev.splash.component.components.prngs.MersenneTwister;
import io.rev.splash.exceptions.AlreadyLoadedException;
import io.rev.splash.exceptions.InvalidCompenetType;
import io.rev.splash.exceptions.NotLoadedException;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.text.chat.ChatTypes;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * Creator: LordLambda
 * Date: 12/5/14.
 * Project: Apollo
 * Usage: Generates Entropy
 */
public class EntropyGenerator {

    RNG toUse;
    Object instance;
    ComponentLoader cl;
    static EntropyGenerator eg;

    public EntropyGenerator() {
        eg = this;
        cl = ComponentLoader.getLoader();
        toUse = EntropyTester.test();
        switch(toUse) {
            case YARROW:
                instance = null;//ComponentLoader.getCl().load("Yarrow");
            case MersenneTwister:
                if(cl.isComponentLoaded("MersenneTwister")) {
                    try {
                        instance = cl.getCompenent("MersenneTwister");
                    }catch(NotLoadedException e) {
                        //Will never happen.
                    }
                }else {
                    try {
                        instance = cl.loadCompenent("MersenneTwister");
                    }catch(AlreadyLoadedException e) {
                        //Will never happen
                    }catch(InvalidCompenetType ee) {
                        //Again will never happen.
                    }
                }
            case SecureRandom:
                instance = new SecureRandom();
        }
    }

    public void reTest() {
        //Retest log to console.
        toUse = EntropyTester.test();
    }

    @Command(aliases="entropytest", desc="Re Test Entropy")
    @Require("apollo.testEntro")
    public void reTest(Player player){
        toUse = EntropyTester.test(player);
        player.sendMessage(ChatTypes.CHAT, String.format("[Apollo] Entropy is now using: %s", toUse.name()));
    }

    public int nextInt() {
        switch(toUse) {
            case YARROW:
                //((Yarrow) instance).nextInt();
                return 0;
            case MersenneTwister:
                return ((MersenneTwister) instance).nextInt();
            case SecureRandom:
                return ((SecureRandom) instance).nextInt();
        }
        return 0; //this will never happen
    }

    public int nextInt(int n) {
        switch(toUse) {
            case YARROW:
                //((Yarrow) instance).nextInt();
                return 0;
            case MersenneTwister:
                return ((MersenneTwister) instance).nextInt(n);
            case SecureRandom:
                return ((SecureRandom) instance).nextInt(n);
        }
        return 0; //this will never happen
    }

    public EntropyGenerator getSelf() {
        return eg;
    }

    public List<Integer> generateInts(int number) {
        List<Integer> ints = new ArrayList<>();
        for(int i = 0; i < number; ++i) {
            ints.add(nextInt());
        }
        return ints;
    }
}