import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class day2 {
    public static void main(String[] args) {

        HashMap<String, Integer> scoreRubricsPart1 = new HashMap<>();
        scoreRubricsPart1.put("A X", 3+1);
        scoreRubricsPart1.put("A Y", 6+2);
        scoreRubricsPart1.put("A Z", 0+3);
        scoreRubricsPart1.put("B X", 0+1);
        scoreRubricsPart1.put("B Y", 3+2);
        scoreRubricsPart1.put("B Z", 6+3);
        scoreRubricsPart1.put("C X", 6+1);
        scoreRubricsPart1.put("C Y", 0+2);
        scoreRubricsPart1.put("C Z", 3+3);

        HashMap<String, Integer> scoreRubricsPart2 = new HashMap<>();
        scoreRubricsPart2.put("A X", 0+3);
        scoreRubricsPart2.put("A Y", 3+1);
        scoreRubricsPart2.put("A Z", 6+2);
        scoreRubricsPart2.put("B X", 0+1);
        scoreRubricsPart2.put("B Y", 3+2);
        scoreRubricsPart2.put("B Z", 6+3);
        scoreRubricsPart2.put("C X", 0+2);
        scoreRubricsPart2.put("C Y", 3+3);
        scoreRubricsPart2.put("C Z", 6+1);       


        int partOneAnswer = 0;
        int partTwoAnswer = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            String input;
            while ((input = reader.readLine()) != null) {
                partOneAnswer += scoreRubricsPart1.get(input);
                partTwoAnswer += scoreRubricsPart2.get(input);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Part 1 Answer
        System.out.println(partOneAnswer);

        // Part 2 Answer
        System.out.println(partTwoAnswer);
    }
}
