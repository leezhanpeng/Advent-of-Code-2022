import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class day1 {
    public static void main(String[] args) {
        ArrayList<Integer> elfCalories = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));

            int caloriesSum = 0;

            String input;
            while ((input = reader.readLine()) != null) {
                if (!input.equals("")) {
                    caloriesSum += Integer.parseInt(input);
                } else {
                    elfCalories.add(caloriesSum);
                    caloriesSum = 0;
                }
            }
            elfCalories.add(caloriesSum);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(elfCalories, Collections.reverseOrder());

        // Part 1 Answer
        System.out.println(elfCalories.get(0));

        // Part 2 Answer
        System.out.println(elfCalories.get(0) + elfCalories.get(1) + elfCalories.get(2));
    }
}
