package org.yatzy.service;

import org.yatzy.model.RolledDice;

import java.util.List;

/**
 * @author bilel_boughanmi
 * @apiNote This class centralizes all the methods for calculating the score following the roll of the dice
 * @since 20220805
 */
public class RolledDiceService {

    /**
     * @apiNote No need to instantiate the class, we can hide its constructor
     */
    private RolledDiceService() {
    }

    /**
     * @param rolledDice
     * @return an integer representing the sum of all dice
     * @apiNote The player scores the sum of all dice, no matter what they read. For example:
     * 1,1,3,3,6 placed on “chance” scores 14 (1+1+3+3+6)
     * 4,5,5,6,1 placed on “chance” scores 21 (4+5+5+6+1)
     */
    public static int chance(RolledDice rolledDice) {
        return rolledDice.sum();
    }

    /**
     * @param rolledDice
     * @return score of 50 point if yatzy, 0 point otherwise.
     * @apiNote The big jackpot ! If all dice have the same number, the player scores 50 points. For example:
     * 1,1,1,1,1 placed on “yatzy” scores 50
     * 1,1,1,2,1 placed on “yatzy” scores 0
     */
    public static int yatzy(RolledDice rolledDice) {
        return rolledDice.isYatzy() ? 50 : 0;
    }

    /**
     * The player scores the sum of the dice that reads one. For example:
     * 3,3,3,4,5 placed on “ones” scores 0
     *
     * @param rolledDice
     * @return the score valueDice * nbOccurrence
     */
    public static int ones(RolledDice rolledDice) {
        return (rolledDice.getNbOccurrenceForValueDice(1) * 1);
    }

    /**
     * The player scores the sum of the dice that reads two. For example:
     * 2,3,2,5,1 placed on “twos” scores 4 (2+2)
     *
     * @param rolledDice
     * @return the score valueDice * nbOccurrence
     */
    public static int twos(RolledDice rolledDice) {
        return (rolledDice.getNbOccurrenceForValueDice(2) * 2);
    }

    /**
     * The player scores the sum of the dice that reads three. For example:
     * 2,3,2,5,1 placed on “threes” scores 3
     *
     * @param rolledDice
     * @return the score valueDice * nbOccurrence
     */
    public static int threes(RolledDice rolledDice) {
        return rolledDice.getNbOccurrenceForValueDice(3) * 3;
    }

    /**
     * The player scores the sum of the dice that reads four. For example:
     * 2,3,2,5,1 placed on “twos” scores 4 (2+2)
     *
     * @param rolledDice
     * @return the score valueDice * nbOccurrence
     */
    public static int fours(RolledDice rolledDice) {
        return rolledDice.getNbOccurrenceForValueDice(4) * 4;
    }

    /**
     * The player scores the sum of the dice that reads five. For example:
     * 2,3,2,5,1 placed on “twos” scores 5
     *
     * @param rolledDice
     * @return the score valueDice * nbOccurrence
     */
    public static int fives(RolledDice rolledDice) {
        return rolledDice.getNbOccurrenceForValueDice(5) * 5;
    }

    /**
     * @param rolledDice
     * @return the score valueDice * nbOccurrence
     * @apiNote The player scores the sum of the dice that reads six. For example:
     * 1,1,6,2,6 placed on “sixes” scores 12 (6+6)
     */
    public static int sixes(RolledDice rolledDice) {
        return rolledDice.getNbOccurrenceForValueDice(6) * 6;
    }

    /**
     * @param rolledDice
     * @return The sum of the two highest matching dice
     * @apiNote The player scores the sum of the two highest matching dice. For example, when placed on “pair”:
     */
    public static int onePair(RolledDice rolledDice) {
        List<Integer> allPairs = rolledDice.getAllPairsOrderedByDesc();
        return allPairs.isEmpty() ? 0 : allPairs.get(0) * 2;
    }

    /**
     * @param rolledDice
     * @return The sum of the two pairs matching dice
     * @apiNote If there are two pairs of dice with the same number, the player scores the sum of these dice. For example, when placed on “two pairs”:
     * 1,1,2,3,3 scores 8 (1+1+3+3)
     * 1,1,2,3,4 scores 0
     * 1,1,2,2,2 scores 6 (1+1+2+2)
     * 3,3,3,3,1 scores 0
     */
    public static int twoPairs(RolledDice rolledDice) {
        try {
            List<Integer> allPairs = rolledDice.getAllPairsOrderedByDesc();
            return allPairs.isEmpty() ? 0 : allPairs.get(0) * 2 + allPairs.get(1) * 2;
        } catch (IndexOutOfBoundsException exception) {
            return 0;
        }
    }

    /**
     * @param rolledDice
     * @return
     * @apiNote If there are three dice with the same number, the player scores the sum of these dice. For example, when placed on “three of a kind”:
     * 3,3,3,4,5 scores 9 (3+3+3)
     * 3,3,4,5,6 scores 0
     * 3,3,3,3,1 scores 9 (3+3+3)
     */
    public static int threeOfAKind(RolledDice rolledDice) {
        return rolledDice.getFirstDiceValuesWithOccurGreaterOrEqualTo(3) * 3;
    }

    /**
     * @param rolledDice
     * @return
     * @apiNote If there are four dice with the same number, the player scores the sum of these dice. For example, when placed on “four of a kind”:
     * 2,2,2,2,5 scores 8 (2+2+2+2)
     * 2,2,2,5,5 scores 0
     * 2,2,2,2,2 scores 8 (2+2+2+2)
     */
    public static int fourOfAKind(RolledDice rolledDice) {
        return rolledDice.getFirstDiceValuesWithOccurGreaterOrEqualTo(4) * 4;
    }

    /**
     * @param rolledDice
     * @return The sum of all the dice if value of dice is 1, 2, 3, 4 and 5
     * @apiNote When placed on “small straight”, if the dice read
     * 1,2,3,4,5, the player scores 15 (the sum of all the dice).
     */
    public static int smallStraight(RolledDice rolledDice) {
        return rolledDice.isSmallStraight() ? 15 : 0;
    }

    /**
     * @param rolledDice
     * @return The sum of all the dice
     * @apiNote When placed on “large straight”, if the dice read
     * 2,3,4,5,6, the player scores 20 (the sum of all the dice).
     */
    public static int largeStraight(RolledDice rolledDice) {
        return rolledDice.isLargeStraight() ? 20 : 0;
    }

    /**
     * @param rolledDice
     * @return the sum of all the dice
     * @apiNote If the dice are two of a kind and three of a kind, the player scores the sum of all the dice. For example, when placed on “full house”:
     * 1,1,2,2,2 scores 8 (1+1+2+2+2)
     * 2,2,3,3,4 scores 0
     * 4,4,4,4,4 scores 0
     */
    public static int fullHouse(RolledDice rolledDice) {
        return rolledDice.isFullHouse() ? rolledDice.sum() : 0;
    }

}
