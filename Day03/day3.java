import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class day3 {
    public static void main(String[] args) {

        int partOneAnswer = 0;
        int partTwoAnswer = 0;

        ArrayList<String> allRucksacks = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            String input;
            while ((input = reader.readLine()) != null) {
                allRucksacks.add(input);
                HashSet<Character> itemTracker = new HashSet<>();
                int rucksackSize = input.length();
                char[] compartmentOne = input.substring(0, rucksackSize / 2).toCharArray();
                char[] compartmentTwo = input.substring(rucksackSize / 2, rucksackSize).toCharArray();

                for (char letter : compartmentOne) {
                    if (!itemTracker.contains(letter)) {
                        itemTracker.add(letter);
                    }
                }

                for (char letter : compartmentTwo) {
                    if (itemTracker.contains(letter)) {
                        int value = letter;
                        if (value >= 97) {
                            partOneAnswer += value - 96;
                        } else {
                            partOneAnswer += value - 65 + 27;
                        }
                        break;
                    }
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] rucksacks = allRucksacks.toArray(new String[0]);
        for (int i = 0; i < rucksacks.length; i += 3) {

            HashSet<Character> itemTrackerOne = new HashSet<>();
            for (char letter : rucksacks[i].toCharArray()) {
                if (!itemTrackerOne.contains(letter)) {
                    itemTrackerOne.add(letter);
                }
            }

            HashSet<Character> itemTrackerTwo = new HashSet<>();
            for (char letter : rucksacks[i + 1].toCharArray()) {
                if (itemTrackerOne.contains(letter) && !itemTrackerTwo.contains(letter)) {
                    itemTrackerTwo.add(letter);
                }
            }

            for (char letter : rucksacks[i + 2].toCharArray()) {
                if (itemTrackerTwo.contains(letter)) {
                    int value = letter;
                    if (value >= 97) {
                        partTwoAnswer += value - 96;
                    } else {
                        partTwoAnswer += value - 65 + 27;
                    }
                    break;
                }
            }
        }

        // Part 1 Answer
        System.out.println(partOneAnswer);

        // Part 2 Answer
        System.out.println(partTwoAnswer);
    }
}
