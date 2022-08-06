package org.yatzy.constant;

import java.util.Arrays;
import java.util.List;

/**
 * @author bilel_boughanmi
 * @apiNote Predefined combination for the distinction of the small or the large straight line
 * allowing to calculate the corresponding score
 * @since 20220805
 */
public final class PredefinedCombination {

    /**
     * @apiNote No need to instantiate the class, we can hide its constructor
     */
    private PredefinedCombination() {
    }

    public static final List<Integer> SMALL_STRAIGHT = Arrays.asList(1, 2, 3, 4, 5);
    public static final List<Integer> LARGE_STRAIGHT = Arrays.asList(2, 3, 4, 5, 6);
}
