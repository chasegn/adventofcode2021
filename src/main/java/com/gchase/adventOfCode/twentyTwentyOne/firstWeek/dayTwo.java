package com.gchase.adventOfCode.twentyTwentyOne.firstWeek;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class dayTwo {
    private dayTwo() { }

    public static void main(String[] args) throws IOException {
//        List<Direction> directions = getData("resources/testDirections");
        List<Direction> directions = getData("src/main/resources/directions");

        System.out.println(part1(directions));
        System.out.println(part2(directions));
    }

    static int part1(List<Direction> directions) {
        int horiz = 0;
        int vert = 0;

        for (Direction direction : directions) {
            switch (direction.movement) {
                case "forward" -> horiz += direction.amount;
                case "up" -> vert -= direction.amount;
                case "down" -> vert += direction.amount;
            }
        }

        return horiz * vert;
    }

    static int part2(List<Direction> directions) {
        int horiz = 0;
        int vert = 0;
        int aim = 0;

        for (Direction direction : directions) {
            switch (direction.movement) {
                case "forward" -> {
                    horiz += direction.amount;
                    vert += (aim * direction.amount);
                }
                case "up" -> aim -= direction.amount;
                case "down" -> aim += direction.amount;
            }
        }

        return horiz * vert;
    }

    private static List<Direction> getData(String path) throws IOException {
        try (Stream<String> lines = Files.lines(Path.of(path))) {
            return lines.flatMap(Pattern.compile("\n")::splitAsStream)
                    .map(Direction::parseFromString)
                    .collect(Collectors.toCollection(LinkedList::new));
        }
    }

    public record Direction(String movement, int amount) {
        public static Direction parseFromString(String string) {
            return new Direction(string.split(" ")[0], Integer.parseInt(string.split(" ")[1]));
        }
    }
}
