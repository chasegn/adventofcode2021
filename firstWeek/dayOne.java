package firstWeek;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class dayOne {
    private dayOne() { }

    public static void main(String[] args) throws IOException {
        List<Integer> depths = getData("resources/intDepths");
//        List<Integer> depths = getData("resources/testDepths");

        System.out.println("part1: " + part1(depths));
        System.out.println("part2: " + part2(depths));
//        streamPart2(depths);
    }

    static int part1(List<Integer> depths) {
        int totalDepthIncrease = 0;
        int prevDepth = 0;


        for (Integer depth : depths) {
            if (prevDepth == 0) {
                prevDepth = depth;
                continue;
            }

            if (depth > prevDepth) {
                totalDepthIncrease++;
            }
            prevDepth = depth;
        }

        return totalDepthIncrease;
    }

    static int part2(List<Integer> depths) {
        int a = depths.get(0);
        int b = depths.get(1);
        int c = depths.get(2);
        int x = depths.get(3);
        int totalDepthIncreases = 0;

        for (int i = 3; i < depths.size();) {
            int sum1 = a + b + c;
            int sum2 = b + c + x;

            if (sum2 > sum1) {
                totalDepthIncreases++;
            }

            a = b;
            b = c;
            c = x;
            x = depths.get(i++);
        }

        return totalDepthIncreases;
    }


//    static void streamPart2(List<Integer> depths) {
//        depths.stream().map(Arrays::asList)
//                        .reduce(new ArrayList<>(), (a, v) -> {
//                            if (a.isEmpty()) {
//                                a.addAll(v);
//                            } else {
//
//                            }
//                        })
//
//
//        IntStream.range(0, depths.size() - 4)
//                .mapToObj(start -> depths.subList(start, start + 3))
//                .takeWhile(a -> a.get(0) < a.get(1))
//                .forEach(System.out::println);
//    }

    private static List<Integer> getData(String path) throws IOException {
        try (Stream<String> lines = Files.lines(Path.of(path))) {
            return lines.flatMap(Pattern.compile("\n")::splitAsStream)
                    .map(Integer::parseInt)
                    .collect(Collectors.toCollection(LinkedList::new));
        }
    }
}
