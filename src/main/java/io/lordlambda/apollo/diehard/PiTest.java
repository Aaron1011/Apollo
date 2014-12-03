package io.lordlambda.apollo.diehard;

import io.lordlambda.apollo.io.XMLConfiguration;
import io.rev.splash.component.ComponentLoader;
import io.rev.splash.component.components.prngs.MersenneTwister;

import java.security.SecureRandom;

/**
 * Creator: LordLambda
 * Date: 12/3/14.
 * Project: Apollo
 * Usage: A closeness to Pi test. More Random = Closer to Pi
 */
public class PiTest {

    public RNG getTestResult() {
        double a, b, c;
        ComponentLoader cl = ComponentLoader.getLoader();
        int iter = Integer.parseInt(XMLConfiguration.getSelf().parseValue("configuration.xml", "configuration", "randomTestIter"));
        try {
            a = randomPi((MersenneTwister) cl.getCompenent("MersenneTwister"), iter);
        }catch(Exception e) {
            a = 0;
        }
        b = randomPi(new SecureRandom(), iter);
        c = 0;//c = randomPi(yarrow, iter);
        if(isCloser(a, b)) {
            if(isCloser(a, c)) {
                return RNG.MersenneTwister;
            }else {
                return RNG.YARROW;
            }
        }else {
            if(isCloser(b, c)) {
                return RNG.SecureRandom;
            }else {
                return RNG.YARROW;
            }
        }
    }

    private double distanceFromPi(double d) {
        return Math.PI - d;
    }

    private boolean isCloser(double isCloser, double toTest) {
        double d = distanceFromPi(isCloser);
        double d2 = distanceFromPi(toTest);
        return Math.abs( d ) < Math.abs ( d2 );
    }

    private boolean isInQuadrant(double x, double y) {
        double distance = Math.sqrt((x * x) + (y * y));
        return distance <= 1;
    }

    public double randomPi(MersenneTwister mt, int iter) {
        int totalInsideQuadrant = 0;
        for (int i = 0; i < iter; i++) {
            double x = mt.nextDouble();
            double y = mt.nextDouble();
            if (isInQuadrant(x, y)) {
                ++totalInsideQuadrant;
            }
        }
        return 4 * ((double) totalInsideQuadrant / iter);
    }

    public double randomPi(SecureRandom sr, int iter) {
        int totalInsideQuadrant = 0;
        for (int i = 0; i < iter; i++) {
            double x = sr.nextDouble();
            double y = sr.nextDouble();
            if (isInQuadrant(x, y)) {
                ++totalInsideQuadrant;
            }
        }
        return 4 * ((double) totalInsideQuadrant / iter);
    }

    /*
    public double randomPi(Yarrow yr, int iter) {
        int totalInsideQuadrant = 0;
        for (int i = 0; i < iter; i++) {
            double x = yr.nextDouble();
            double y = yr.nextDouble();
            if (isInQuadrant(x, y)) {
                ++totalInsideQuadrant;
            }
        }
        return 4 * ((double) totalInsideQuadrant / iter);
    }
     */
}