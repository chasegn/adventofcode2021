package old;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sonar {

    public Sonar() {

    }

    static String depthCalculations(String orderPath) {
        List<Integer> depths;

        try (Stream<String> lines = Files.lines(Path.of(orderPath))) {
            depths = lines.flatMap(Pattern.compile("\n")::splitAsStream)
                    .map(Integer::parseInt)
                    .collect(Collectors.toCollection(LinkedList::new));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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

        return String.valueOf(totalDepthIncrease);
    }
}
