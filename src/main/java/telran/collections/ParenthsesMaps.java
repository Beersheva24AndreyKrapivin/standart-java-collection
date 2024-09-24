package telran.collections;

import java.util.*;

public record ParenthsesMaps(Map<Character, Character> openCloseMap, Map<Character, Character> closeOpenMap) {
    //openCloseMap - key is openning parentheses, value is closing parentheses
    //closeOpenMap - key is closeng parentheses, value is openning parentheses
}
