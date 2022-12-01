package com.gchase.adventOfCode.twentyTwentyOne.firstWeek;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class dayThree {
    private dayThree() { }

    public static void main(String[] args) throws IOException {
        List<BitSet> binaryVals = getData("src/main/resources/binary");
//        List<BitSet> binaryVals = getData("resources/testBinary");

        System.out.println(part1(binaryVals));
//        System.out.println(part2(binaryVals));
    }

    static int part1(List<BitSet> binaryVals) {
        Map<Integer, Integer> counts = new HashMap<>();
        BitSet testBit = (BitSet) binaryVals.get(0).clone();


        for (int i = binaryVals.get(0).length(); i >= 0; i--) {
            for (BitSet bitset : binaryVals) {
                if (bitset.get(i)) {
                    if (counts.containsKey(i)) {
                        counts.put(i, counts.get(i) + 1);
                    } else {
                        counts.put(i, 1);
                    }
                }
            }
        }






//        StringBuilder gammaSB = new StringBuilder();
//        StringBuilder epsilonSB = new StringBuilder();
//        for (int count : counts) {
//            gammaSB.append(count > (binaryVals.size() / 2) ? "1" : "0");
//            epsilonSB.append(count < (binaryVals.size() / 2) ? "1" : "0");
//        }
//
//        int gamma = Integer.parseInt(gammaSB.toString(), 2);
//        int epsilon = Integer.parseInt(epsilonSB.toString(), 2);
//
//        return gamma * epsilon;
//    }
//
//    static int part2(List<BitSet> binaryVals) {
//        // todo
//        // starting with first bit, find bit criteria
//        // filter out all values from master list that don't have that bit
//        // continue until one value remains (rating)
//        // oxygen generator rating bit criteria: most common (gamma)
//        // co2 scrubber rating bit criteria: least common (epsilon)
//
//        int[] counts = new int[12];
//
//        for (BitSet binaryVal : binaryVals) {
//            String[] val = binaryVal.split("");
//            counts[0] += Integer.parseInt(val[0]);
//            counts[1] += Integer.parseInt(val[1]);
//            counts[2] += Integer.parseInt(val[2]);
//            counts[3] += Integer.parseInt(val[3]);
//            counts[4] += Integer.parseInt(val[4]);
//            counts[5] += Integer.parseInt(val[5]);
//            counts[6] += Integer.parseInt(val[6]);
//            counts[7] += Integer.parseInt(val[7]);
//            counts[8] += Integer.parseInt(val[8]);
//            counts[9] += Integer.parseInt(val[9]);
//            counts[10] += Integer.parseInt(val[10]);
//            counts[11] += Integer.parseInt(val[11]);
//        }
//
//
////        return oxygen * co2Scrubber;
        return 4; // todo
    }

    private static int mostSignificant(int value, int size) {
        return value >= (size / 2) ? 1 : 0;
    }

    private static List<BitSet> getData(String path) throws IOException {
        try (Stream<String> lines = Files.lines(Path.of(path))) {
            return lines.flatMap(Pattern.compile("\n")::splitAsStream)
                    .map(s -> Integer.parseInt(s, 2))
                    .map(i -> BitSet.valueOf(new long[]{i}))
                    .collect(Collectors.toCollection(LinkedList::new));
        }
    }

    public record BinaryNum(BitSet bitSet, int length) {
        public static BinaryNum parseFromString(String string) {
            return new BinaryNum(, string.length());
        }
    }
}
