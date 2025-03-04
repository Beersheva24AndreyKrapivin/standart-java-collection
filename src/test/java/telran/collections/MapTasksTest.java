package telran.collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

public class MapTasksTest {

    @Test
    void displayOccurrencesTest() {
        String[] strings = {"lpm", "ab", "a", "c", "cb", "cb", "c", "lpm", "lpm"};
        MapTasks.displayOccurrences(strings);
    }

    @Test
    void displayOccurrencesStreamTest() {
        String[] strings = {"lpm", "ab", "a", "c", "cb", "cb", "c", "lpm", "lpm"};
        MapTasks.displayOccurrencesStream(strings);
    }

    @Test
    void getGroupingByNubmerOfDigitsTest() {
        int[][] array = {{100, 1, 50}, {20, 30}, {1}};
        Map<Integer, Integer[]> map = MapTasks.getGroupingByNubmerOfDigits(array);
        Integer[] oneDigitNumbers = {1, 1};
        Integer[] twoDigitsNumbers = {50, 20, 30};
        Integer[] threeDigitsNumbers = {100};
        assertArrayEquals(map.get(1), oneDigitNumbers);
        assertArrayEquals(map.get(2), twoDigitsNumbers);
        assertArrayEquals(map.get(3), threeDigitsNumbers);
    }

    @Test
    void getDistributionByNumberOfDigits() {
        int[][] array = {{100, 1, 50}, {20, 30}, {1}};
        Map<Integer, Long> map = MapTasks.getDistributionByNumberOfDigits(array);
        assertEquals(2, map.get(1));
        assertEquals(3, map.get(2));
        assertEquals(1, map.get(3));
    }

    @Test
    void displayDigitsDistributionTest() {
        MapTasks.displayDigitsDistribution();
    }

    @Test
    void gerParenthesesMapsTest() {
        Character[][] openCloseParentheses = {
            {'[', ']'}, {'(', ')'}, {'{', '}'}
        };
        ParenthsesMaps maps = MapTasks.gerParenthesesMaps(openCloseParentheses);
        Map<Character, Character> openCloseMap = maps.openCloseMap();
        Map<Character, Character> closeOpenMap = maps.closeOpenMap();
        assertEquals(']', openCloseMap.get('['));
        assertEquals('[', closeOpenMap.get(']'));
        assertEquals(')', openCloseMap.get('('));
        assertEquals('(', closeOpenMap.get(')'));
    }
}
