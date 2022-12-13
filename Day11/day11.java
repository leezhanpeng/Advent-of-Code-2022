import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class day11 {
    public static void main(String[] args) {

        HashMap<Integer, Monkey> partOneMonkeys = new HashMap<>();
        HashMap<Integer, Monkey> partTwoMonkeys = new HashMap<>();
        int divisor_CM = 1;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            Monkey partOneMonkey = new Monkey();
            Monkey partTwoMonkey = new Monkey();

            String input;
            while ((input = reader.readLine()) != null) {
                String[] inputs = input.split(": ");
                if (inputs[0].equals("")) {
                    continue;
                }
                String inputType = inputs[0].stripLeading();
                if (inputType.equals(inputs[0])) {
                    int id = Integer.parseInt(inputType.split(" ")[1].replace(":", ""));
                    partOneMonkey = new Monkey();
                    partTwoMonkey = new Monkey();

                    partOneMonkeys.put(id, partOneMonkey);
                    partTwoMonkeys.put(id, partTwoMonkey);

                    partOneMonkey.id = id;
                    partTwoMonkey.id = id;
                } else if (inputType.equals("Starting items")){
                    String[] items = inputs[1].split(", ");
                    for (String item : items) {
                        partOneMonkey.items.add(Long.parseLong(item));
                        partTwoMonkey.items.add(Long.parseLong(item));
                    }
                } else if (inputType.equals("Operation")) {
                    String[] inputValues = inputs[1].split(" ");
                    partOneMonkey.operator = inputValues[3];
                    partTwoMonkey.operator = inputValues[3];

                    partOneMonkey.operant = inputValues[4];
                    partTwoMonkey.operant = inputValues[4];
                } else if (inputType.equals("Test")) {
                    divisor_CM *= Integer.parseInt(inputs[1].split(" ")[2]);
                    partOneMonkey.divisor = Integer.parseInt(inputs[1].split(" ")[2]);
                    partTwoMonkey.divisor = Integer.parseInt(inputs[1].split(" ")[2]);
                } else if (inputType.equals("If true")) {
                    partOneMonkey.true_pass = Integer.parseInt(inputs[1].split(" ")[3]);
                    partTwoMonkey.true_pass = Integer.parseInt(inputs[1].split(" ")[3]);
                } else if (inputType.equals("If false")) {
                    partOneMonkey.false_pass = Integer.parseInt(inputs[1].split(" ")[3]);
                    partTwoMonkey.false_pass = Integer.parseInt(inputs[1].split(" ")[3]);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Part 1 Answer
        System.out.println(monkeyBusiness(20, partOneMonkeys, true, divisor_CM));

        // Part 2 Answer
        System.out.println(monkeyBusiness(10000, partTwoMonkeys, false, divisor_CM));
    }

    public static long monkeyBusiness(int rounds, HashMap<Integer, Monkey> monkeys, boolean isPartOne, int divisor_CM) {
        for (int round = 0; round < rounds; round++) {
            for (int monkeyID = 0; monkeyID < monkeys.size(); monkeyID++) {
                Monkey monkey = monkeys.get(monkeyID);
                for (long item : monkey.items) {
                    monkey.itemsInspected++;

                    long worryValue = item;
                    if (!monkey.operant.equals("old")) {
                        worryValue = Integer.parseInt(monkey.operant);
                    }

                    if (monkey.operator.equals("+")) {
                        worryValue += item;
                    } else {
                        worryValue *= item;
                    }

                    if (isPartOne) {
                        worryValue /= 3;
                    } else {
                        worryValue %= divisor_CM;
                    }

                    if (worryValue % monkey.divisor == 0) {
                        monkeys.get(monkey.true_pass).items.add(worryValue);
                    } else {
                        monkeys.get(monkey.false_pass).items.add(worryValue);
                    }
                }

                monkey.items.clear();
            }
        }

        long largestInspectCount = 0;
        long secondLargestInspectCount = 0;
        for (int monkeyID = 0; monkeyID < monkeys.size(); monkeyID++) {
            if (monkeys.get(monkeyID).itemsInspected > largestInspectCount) {
                secondLargestInspectCount = largestInspectCount;
                largestInspectCount = monkeys.get(monkeyID).itemsInspected;
            } else if (monkeys.get(monkeyID).itemsInspected > secondLargestInspectCount) {
                secondLargestInspectCount = monkeys.get(monkeyID).itemsInspected;
            }
        }

        return largestInspectCount * secondLargestInspectCount;
    }
}

class Monkey {
    int id, divisor, true_pass, false_pass;
    String operant, operator;
    ArrayList<Long> items = new ArrayList<>();

    long itemsInspected = 0;
}