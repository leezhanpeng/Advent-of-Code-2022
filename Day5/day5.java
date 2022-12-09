import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class day5 {
    public static void main(String[] args) {
        ArrayList<String> inputs = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            String input;
            while ((input = reader.readLine()) != null) {
                inputs.add(input);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] inputLines = inputs.toArray(new String[0]);

        int stackCount = (inputLines[0].length() + 1) / 4;;
        ArrayList<ArrayDeque<Character>> partOneStacks = new ArrayList<>();
        ArrayList<ArrayDeque<Character>> partTwoStacks = new ArrayList<>();

        for (int i = 0; i < stackCount; i++) {
            ArrayDeque<Character> dequeOne = new ArrayDeque<Character>();
            ArrayDeque<Character> dequeTwo = new ArrayDeque<Character>();
            partOneStacks.add(dequeOne);
            partTwoStacks.add(dequeTwo);
        }
        
        // Stack building
        int inputIndex = 0;
        while (true) {

            if (inputLines[inputIndex].charAt(1) == '1') {
                inputIndex += 2;
                break;
            }

            for (int stack = 0; stack < stackCount; stack++) {
                Character crate = inputLines[inputIndex].charAt(stack * 4 + 1);
                if (crate != ' ') {
                    partOneStacks.get(stack).addFirst(crate);
                    partTwoStacks.get(stack).addFirst(crate);
                }
            }
            inputIndex++;
        }

        // Crate movement
        while (inputIndex < inputLines.length) {
            String[] instruction = inputLines[inputIndex].split(" ");
            int crateAmt = Integer.parseInt(instruction[1]);
            int fromStack = Integer.parseInt(instruction[3]) - 1;
            int toStack = Integer.parseInt(instruction[5]) - 1;

            Stack<Character> tempStack = new Stack<>();
            for (int i = 0; i < crateAmt; i++) {
                partOneStacks.get(toStack).addLast((partOneStacks.get(fromStack).removeLast()));
                tempStack.push(partTwoStacks.get(fromStack).removeLast());
            }

            for (int i = 0; i < crateAmt; i++) {
                partTwoStacks.get(toStack).addLast(tempStack.pop());
            }

            inputIndex++;
        }

        // Part 1 Answer
        for (int i = 0; i < stackCount; i++) {
            if (partOneStacks.get(i).size() != 0) {
                System.out.print(partOneStacks.get(i).removeLast());
            }
        }
        System.out.println();

        // Part 2 Answer
        for (int i = 0; i < stackCount; i++) {
            if (partTwoStacks.get(i).size() != 0) {
                System.out.print(partTwoStacks.get(i).removeLast());
            }        
        }
        System.out.println();

    }
}
