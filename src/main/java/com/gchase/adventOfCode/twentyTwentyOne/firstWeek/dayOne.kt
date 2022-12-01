package com.gchase.adventOfCode.twentyTwentyOne.firstWeek

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.util.*
import java.util.function.Supplier
import java.util.regex.Pattern
import java.util.stream.Collectors

object dayOne {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val depths = getData("src/main/resources/intDepths")
        //        List<Integer> depths = getData("resources/testDepths");
        println("part1: " + part1(depths))
        println("part2: " + part2(depths))
        //        streamPart2(depths);
    }

    fun part1(depths: List<Int>): Int {
        var totalDepthIncrease = 0
        var prevDepth = 0
        for (depth in depths) {
            if (prevDepth == 0) {
                prevDepth = depth
                continue
            }
            if (depth > prevDepth) {
                totalDepthIncrease++
            }
            prevDepth = depth
        }
        return totalDepthIncrease
    }

    fun part2(depths: List<Int>): Int {
        var a = depths[0]
        var b = depths[1]
        var c = depths[2]
        var x = depths[3]
        var totalDepthIncreases = 0
        var i = 3
        while (i < depths.size) {
            val sum1 = a + b + c
            val sum2 = b + c + x
            if (sum2 > sum1) {
                totalDepthIncreases++
            }
            a = b
            b = c
            c = x
            x = depths[i++]
        }
        return totalDepthIncreases
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
    @Throws(IOException::class)
    private fun getData(path: String): List<Int> {
        Files.lines(Path.of(path)).use { lines ->
            return lines.flatMap { input: String? -> Pattern.compile("\n").splitAsStream(input) }
                    .map { s: String -> s.toInt() }
                    .collect(Collectors.toCollection(Supplier { LinkedList() }))
        }
    }
}