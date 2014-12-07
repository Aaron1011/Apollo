package io.lordlambda.apollo.entropy;

import io.lordlambda.apollo.diehard.PiTest;
import io.lordlambda.apollo.diehard.RNG;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.text.chat.ChatTypes;

/**
 * Creator: LordLambda
 * Date: 12/6/14.
 * Project: Apollo
 * Usage: Entropy Tester
 */
public class EntropyTester {

    static String testMessage = "[Apollo] %s Test has chosen %s";

    public static RNG test() {
        //Perform each test
        int yarrow = 0, securerandom = 0, mer = 0;
        PiTest pi = new PiTest();
        switch(pi.getTestResult()) {
            case YARROW:
                yarrow++;
                break;
            case SecureRandom:
                securerandom++;
                break;
            case MersenneTwister:
                mer++;
                break;
        }

        //TODO: Add in more tests

        if(yarrow > securerandom) {
            if(yarrow > mer) {
                return RNG.YARROW;
            }else {
                return RNG.MersenneTwister;
            }
        }else {
            if(securerandom > mer) {
                return RNG.SecureRandom;
            }else {
                return RNG.MersenneTwister;
            }
        }
    }

    public static RNG test(Player player) {
        int yarrow = 0, securerandom = 0, mer = 0;
        PiTest pi = new PiTest();
        switch(pi.getTestResult()) {
            case YARROW:
                yarrow++;
                player.sendMessage(ChatTypes.CHAT, String.format(testMessage, "Pi", "Yarrow"));
                break;
            case SecureRandom:
                player.sendMessage(ChatTypes.CHAT, String.format(testMessage, "Pi", "SecureRandom"));
                securerandom++;
                break;
            case MersenneTwister:
                player.sendMessage(ChatTypes.CHAT, String.format(testMessage, "Pi", "Mersenne Twister"));
                mer++;
                break;
        }

        //TODO: Add in more tests

        if(yarrow > securerandom) {
            if(yarrow > mer) {
                return RNG.YARROW;
            }else {
                return RNG.MersenneTwister;
            }
        }else {
            if(securerandom > mer) {
                return RNG.SecureRandom;
            }else {
                return RNG.MersenneTwister;
            }
        }
    }
}