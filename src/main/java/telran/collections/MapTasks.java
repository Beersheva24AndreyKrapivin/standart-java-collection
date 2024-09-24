package telran.collections;

import java.util.*;
import java.util.stream.*;
import java.util.function.Function;

public class MapTasks {

    public static void displayOccurrences(String[] strings) {
        //input {"lpm", "ab", "a", "c", "cb", "cb", "c", "lpm", "lpm"}
        //output:
        //lpm -> 3
        //c -> 2
        //cb -> 2
        //a -> 1
        //ab -> 1
        HashMap<String, Long> occurrenceMap = getMapOccurrences(strings);
        TreeMap<Long, TreeSet<String>> sortedOccurrencesMap = getSortedOccurrencesMap(occurrenceMap);
        displaySortedOccurrencesMap(sortedOccurrencesMap);
    }

    public static void displayOccurrencesStream(String[] strings) {
        Arrays.stream(strings).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().sorted((e1, e2) -> {
                    int res = Long.compare(e2.getValue(), e1.getValue());
                    return res == 0 ? e1.getKey().compareTo(e2.getKey()) : res;
                }).forEach(e -> System.out.printf("%s -> %d\n", e.getKey(), e.getValue()));
    }

    private static void displaySortedOccurrencesMap(TreeMap<Long, TreeSet<String>> sortedOccurrencesMap) {
        sortedOccurrencesMap.forEach((occurency, treeSet) -> treeSet.forEach(s -> System.out.printf("%s -> %d \n", s, occurency)));
    }

    private static TreeMap<Long, TreeSet<String>> getSortedOccurrencesMap(HashMap<String, Long> occurrenceMap) {
        TreeMap<Long, TreeSet<String>> result = new TreeMap<>(Comparator.reverseOrder());
        occurrenceMap.entrySet().forEach(e -> result.computeIfAbsent(e.getValue(), 
            k -> new TreeSet<>()).add(e.getKey()));
        return result;
    }

    private static HashMap<String, Long> getMapOccurrences(String[] strings) {
        HashMap<String, Long> result = new HashMap<>();
        Arrays.stream(strings).forEach(s -> result.merge(s, 1l, Long::sum));
        return result;
    }

    public static Map<Integer, Integer[]> getGroupingByNubmerOfDigits(int[][] array) {
        Map<Integer, List<Integer>> map = streamOfNumbers(array)
                .collect(Collectors.groupingBy(n -> Integer.toString(n).length()));
        return map.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().toArray(Integer[]::new)));
    }

    private static Stream<Integer> streamOfNumbers(int[][] array) {
        return Arrays.stream(array).flatMapToInt(Arrays::stream).boxed();
    }

    public static Map<Integer, Long> getDistributionByNumberOfDigits(int[][] array) {
        return streamOfNumbers(array).collect(Collectors.groupingBy(n -> Integer.toString(n).length(), Collectors.counting()));
    }

    public static void displayDigitsDistribution() {
        //1_000_000 random number from 0 to Integer.MAX_VALUE created
        //Output should contain all digits (0 - 9) with counters of occurrences
        //sorted by descending order of occurrences
        //example:
        //1 -> <counter of occurrences>
        //2 -> <counter of occurrences>
        //...........
        new Random().ints(1_000_000, 0, Integer.MAX_VALUE).boxed()
            .flatMap(num -> String.valueOf(num).chars().mapToObj(c -> (char)c))
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet().stream().sorted((e1, e2) -> {
                int res = Long.compare(e2.getValue(), e1.getValue());
                return res == 0 ? e1.getKey().compareTo(e2.getKey()) : res;
            }).forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue()));
    }

    public static ParenthsesMaps gerParenthesesMaps(Character[][] openCloseParentheses) {
        Map<Character, Character> openCloseMap = Arrays.stream(openCloseParentheses)
                .collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));
        Map<Character, Character> closeOpenMap = Arrays.stream(openCloseParentheses)
                .collect(Collectors.toMap(arr -> arr[1], arr -> arr[0]));
        return new ParenthsesMaps(openCloseMap, closeOpenMap);
    }
}
