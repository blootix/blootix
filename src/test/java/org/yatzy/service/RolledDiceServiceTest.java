package org.yatzy.service;

import org.junit.Before;
import org.junit.Test;
import org.yatzy.model.RolledDice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.yatzy.constant.PredefinedCombination.SMALL_STRAIGHT;

public class RolledDiceServiceTest {

    private RolledDice rolledDiceFourFive1;

    private RolledDice rolledDiceFourFive2;

    private RolledDice rolledDiceFourFive3;

    @Before
    public void init() {
        rolledDiceFourFive1 = new RolledDice(4, 4, 4, 5, 5);
        rolledDiceFourFive2 = new RolledDice(4, 4, 5, 5, 5);
        rolledDiceFourFive3 = new RolledDice(4, 5, 5, 5, 5);
    }

    @Test
    public void chance() {
        RolledDice chanceRolledDice1 = new RolledDice(2, 3, 4, 5, 1);
        assertTrue("2,3,4,5,1 placed on “chance” scores 14 (1+1+3+3+6)", RolledDiceService.chance(chanceRolledDice1) == 15);
        RolledDice chanceRolledDice2 = new RolledDice(3, 3, 4, 5, 1);
        assertEquals("3,3,4,5,1 placed on “chance” scores 21 (4+5+5+6+1)", RolledDiceService.chance(chanceRolledDice2), 16);
    }

    @Test
    public void yatzy() {
        RolledDice yatzyRolledDice1 = new RolledDice(4, 4, 4, 4, 4);
        assertEquals(50, RolledDiceService.yatzy(yatzyRolledDice1));
        RolledDice yatzyRolledDice2 = new RolledDice(6, 6, 6, 6, 6);
        assertEquals(50, RolledDiceService.yatzy(yatzyRolledDice2));
        RolledDice yatzyRolledDice3 = new RolledDice(6, 6, 6, 6, 3);
        assertEquals(0, RolledDiceService.yatzy(yatzyRolledDice3));
    }

    @Test
    public void ones() {
        assertTrue(RolledDiceService.ones(new RolledDice(SMALL_STRAIGHT)) == 1);
        assertEquals(2, RolledDiceService.ones(new RolledDice(1, 2, 1, 4, 5)));
        assertEquals(0, RolledDiceService.ones(new RolledDice(6, 2, 2, 4, 5)));
        assertEquals(4, RolledDiceService.ones(new RolledDice(1, 2, 1, 1, 1)));
    }

    @Test
    public void twos() {
        assertEquals(4, RolledDiceService.twos(new RolledDice(1, 2, 3, 2, 6)));
        assertEquals(10, RolledDiceService.twos(new RolledDice(2, 2, 2, 2, 2)));
    }

    @Test
    public void threes() {
        assertEquals(6, RolledDiceService.threes(new RolledDice(1, 2, 3, 2, 3)));
        assertEquals(12, RolledDiceService.threes(new RolledDice(2, 3, 3, 3, 3)));
    }

    @Test
    public void fours() {
        assertEquals(12, RolledDiceService.fours(rolledDiceFourFive1));
        assertEquals(8, RolledDiceService.fours(rolledDiceFourFive2));
        assertEquals(4, RolledDiceService.fours(rolledDiceFourFive3));
    }

    @Test
    public void fives() {
        assertEquals(10, RolledDiceService.fives(rolledDiceFourFive1));
        assertEquals(15, RolledDiceService.fives(rolledDiceFourFive2));
        assertEquals(20, RolledDiceService.fives(rolledDiceFourFive3));
    }

    @Test
    public void sixes() {
        assertEquals(0, RolledDiceService.sixes(rolledDiceFourFive1));
        assertEquals(6, RolledDiceService.sixes(new RolledDice(4, 4, 6, 5, 5)));
        assertEquals(18, RolledDiceService.sixes(new RolledDice(6, 5, 6, 6, 5)));
    }

    @Test
    public void onePair() {
        assertEquals("1,2,3,4,5 scores 0", RolledDiceService.onePair(new RolledDice(1, 2, 3, 4, 5)), 0);
        assertEquals("3,3,3,4,4 scores 8", RolledDiceService.onePair(new RolledDice(3, 3, 3, 4, 4)), 8);
        assertEquals("3,3,3,4,1 scores 6", RolledDiceService.onePair(new RolledDice(3, 3, 3, 4, 1)), 6);
        assertEquals("3,3,3,3,1 scores 6", RolledDiceService.onePair(new RolledDice(3, 3, 3, 3, 1)), 6);
    }

    @Test
    public void twoPairs() {
        assertEquals("1,1,2,3,3 scores 8 (1+1+3+3)", RolledDiceService.twoPairs(new RolledDice(1, 1, 2, 3, 3)), 8);
        assertEquals("1,1,2,3,4 scores 0", RolledDiceService.twoPairs(new RolledDice(1, 1, 2, 3, 4)), 0);
        assertEquals("1,1,2,2,2 scores 6 (1+1+2+2)", RolledDiceService.twoPairs(new RolledDice(1, 1, 2, 2, 2)), 6);
        assertEquals("3,3,3,3,1 scores 0", RolledDiceService.twoPairs(new RolledDice(3, 3, 3, 3, 1)), 0);
    }

    @Test
    public void threeOfAKind() {
        assertEquals("3,3,3,4,5 scores 9 (3+3+3)", RolledDiceService.threeOfAKind(new RolledDice(3, 3, 3, 4, 5)), 9);
        assertEquals("3,3,4,5,6 scores 0", RolledDiceService.threeOfAKind(new RolledDice(3, 3, 4, 5, 6)), 0);
        assertEquals("3,3,3,3,1 scores 9 (3+3+3)", RolledDiceService.threeOfAKind(new RolledDice(3, 3, 3, 3, 1)), 9);
    }

    @Test
    public void fourOfKind() {
        assertEquals("2,2,2,2,5 scores 8 (2+2+2+2)", RolledDiceService.fourOfAKind(new RolledDice(2, 2, 2, 2, 5)), 8);
        assertEquals("2,2,2,5,5 scores 0", RolledDiceService.fourOfAKind(new RolledDice(2, 2, 2, 5, 5)), 0);
        assertEquals("2,2,2,2,2 scores 8 (2+2+2+2)", RolledDiceService.fourOfAKind(new RolledDice(2, 2, 2, 2, 2)), 8);
    }

    @Test
    public void smallStraight() {
        assertEquals(15, RolledDiceService.smallStraight(new RolledDice(1, 2, 3, 4, 5)));
        assertEquals(15, RolledDiceService.smallStraight(new RolledDice(2, 5, 1, 3, 4)));
        assertEquals(0, RolledDiceService.smallStraight(new RolledDice(1, 2, 1, 3, 3)));
    }

    @Test
    public void largeStraight() {
        assertEquals(20, RolledDiceService.largeStraight(new RolledDice(2, 5, 6, 3, 4)));
        assertEquals(20, RolledDiceService.largeStraight(new RolledDice(2, 5, 6, 3, 4)));
        assertEquals(0, RolledDiceService.largeStraight(new RolledDice(1, 2, 6, 3, 3)));
    }

    @Test
    public void fullHouse() {
        assertEquals("1,1,2,2,2 scores 8 (1+1+2+2+2)", RolledDiceService.fullHouse(new RolledDice(1, 1, 2, 2, 2)), 8);
        assertEquals("2,2,3,3,4 scores 0", RolledDiceService.fullHouse(new RolledDice(2, 2, 3, 3, 4)), 0);
        assertEquals("4,4,4,4,4 scores 0", RolledDiceService.fullHouse(new RolledDice(4, 4, 4, 4, 4)), 0);
    }

}
