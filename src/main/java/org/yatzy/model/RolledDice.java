package org.yatzy.model;

import org.yatzy.constant.PredefinedCombination;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toList;
import static org.yatzy.constant.PredefinedCombination.LARGE_STRAIGHT;
import static org.yatzy.constant.PredefinedCombination.SMALL_STRAIGHT;

/**
 * @author bilel_boughanmi
 * @apiNote This class contains the result of the roll of the 5 dice allowing the deduction of the score thereafter
 * @since 20220805
 */
public class RolledDice implements Serializable {

    private final List<Integer> resultLaunchOfDice;

    /**
     * @param resultLaunchOfDice1
     * @param resultLaunchOfDice2
     * @param resultLaunchOfDice3
     * @param resultLaunchOfDice4
     * @param resultLaunchOfDice5
     * @apiNote Creation of a launch instance of the 5 dice
     */
    public RolledDice(int resultLaunchOfDice1, int resultLaunchOfDice2, int resultLaunchOfDice3, int resultLaunchOfDice4, int resultLaunchOfDice5) {
        this.resultLaunchOfDice = Arrays.asList(resultLaunchOfDice1, resultLaunchOfDice2, resultLaunchOfDice3, resultLaunchOfDice4, resultLaunchOfDice5);
    }

    /**
     * @param resultLaunchOfDice
     * @apiNote Creation of a launch instance of the 5 dice
     */
    public RolledDice(List<Integer> resultLaunchOfDice) {
        this.resultLaunchOfDice = List.copyOf(resultLaunchOfDice);
    }


    /**
     * @return sum of all element
     */
    public int sum() {
        return this.resultLaunchOfDice.stream().reduce(0, Integer::sum);
    }

    /**
     * @return true if yatzy, false otherwise.
     * @apiNote The big jackpot ! If all dice have the same number, the player scores 50 points. For example:
     * 1,1,1,1,1 placed on “yatzy” scores 50
     * 1,1,1,2,1 placed on “yatzy” scores 0
     */
    public boolean isYatzy() {
        return diceValueGroupedByNbOccurrence().values().stream().anyMatch(nbOccurrence -> nbOccurrence == 5);
    }

    /**
     * @param valueDice
     * @return number of occurrence for a particular value
     * @apiNote This method is designed to get dice number occurrence for a value param
     */
    public int getNbOccurrenceForValueDice(int valueDice) {
        return diceValueGroupedByNbOccurrence().getOrDefault(valueDice, 0);
    }

    /**
     * @return Ordered list of distinct value dice
     * @apiNote This method allows to retrieve the list of dice values ordered in descending order
     */
    public List<Integer> getAllPairsOrderedByDesc() throws IndexOutOfBoundsException {
        return getAllDiceValuesWithOccurGreaterOrEqualTo(2).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }

    /**
     * @param nbOccur
     * @return The first value dice with number occurrence greater than param
     */
    public int getFirstDiceValuesWithOccurGreaterOrEqualTo(int nbOccur) {
        return getAllDiceValuesWithOccurGreaterOrEqualTo(nbOccur).findFirst().orElse(0);
    }

    /**
     * @return True if some value in list
     * @apiNote This method compares the static list 1, 2, 3, 4, 5 with rolling values of 5 dice
     */
    public boolean isSmallStraight() {
        return SMALL_STRAIGHT.equals(this.resultLaunchOfDice.stream().sorted().collect(toList()));
    }

    /**
     * @return True if some value in list
     * @apiNote This method compares the static list 2, 3, 4, 5, 6 with rolling values of 5 dice
     */
    public boolean isLargeStraight() {
        return LARGE_STRAIGHT.equals(this.resultLaunchOfDice.stream().sorted().collect(toList()));
    }

    /**
     * @return True if we are wo of a kind and three of a kind
     */
    public boolean isFullHouse() {
        int threeOfAKind = getFirstDiceValuesWithOccurGreaterOrEqualTo(3);
        int onePair = getAllDiceValuesWithOccurGreaterOrEqualTo(2).findFirst().orElse(0);
        return threeOfAKind != 0 && onePair != 0 && threeOfAKind != onePair;
    }

    /**
     * @return Grouping By result is : {3=3, 4=2} for resultLaunchOfDice {4, 4, 3, 3, 3}
     * @apiNote This private method is designed to get dice result grouped by number of occurrence
     */
    private Map<Integer, Integer> diceValueGroupedByNbOccurrence() {
        return resultLaunchOfDice.stream().collect(Collectors.groupingBy(valueDice -> valueDice, reducing(0, element -> 1, Integer::sum)));
    }

    /**
     * @param nbOccur
     * @return The list of dice values with a number of occurrences greater than or equal to the nbOccur parameter
     * @apiNote This private method is designed to get dice values with a number of occurrences greater than or equal to the nbOccur parameter
     */
    private Stream<Integer> getAllDiceValuesWithOccurGreaterOrEqualTo(int nbOccur) {
        return diceValueGroupedByNbOccurrence().entrySet().stream().filter(entry -> entry.getValue() >= nbOccur).map(Map.Entry::getKey);
    }
}
