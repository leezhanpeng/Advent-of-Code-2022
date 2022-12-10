import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day4 {
    public static void main(String[] args) {

        int partOneAnswer = 0;
        int partTwoAnswer = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));

            String input;
            while ((input = reader.readLine()) != null) {
                String[] sections = input.split(",");
                String[] sectionOne = sections[0].split("-");
                String[] sectionTwo = sections[1].split("-");

                int startOne = Integer.parseInt(sectionOne[0]);
                int endOne = Integer.parseInt(sectionOne[1]);
                int startTwo = Integer.parseInt(sectionTwo[0]);
                int endTwo = Integer.parseInt(sectionTwo[1]);

                // To calculate number of pairs that encapsulate one another
                if ((startOne >= startTwo && endOne <= endTwo) || (startTwo >= startOne && endTwo <= endOne)) {
                    partOneAnswer++;
                }
        
                // To calculate number of pairs that overlap one another
                if ((startOne <= endTwo && startTwo <= endOne) || (startTwo <= endOne && startOne <= endTwo)) {
                    partTwoAnswer++;
                }
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
