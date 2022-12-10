import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day6 {
    public static void main(String[] args) {

        int partOneAnswer = 0;
        int partTwoAnswer = 0;

        try {

            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            String input;
            while ((input = reader.readLine()) != null) {
                for (int i = 0; i < input.length() - 4; i++) {
                    char[] subInput = input.substring(i, i + 4).toCharArray();
                    boolean hasDuplicate = false;
                    for (int j = 0; j < subInput.length - 1; j++) {
                        for (int k = j + 1; k < subInput.length; k++) {
                            if (subInput[j] == subInput[k]) {
                                hasDuplicate = true;
                                break;
                            }
                        }

                        if (hasDuplicate) {
                            break;
                        }
                    }             
                    if (!hasDuplicate) {
                        partOneAnswer = i + 4;
                        break;
                    }
                }

                for (int i = 0; i < input.length() - 14; i++) {
                    char[] subInput = input.substring(i, i + 14).toCharArray();
                    boolean hasDuplicate = false;
                    for (int j = 0; j < subInput.length - 1; j++) {
                        for (int k = j + 1; k < subInput.length; k++) {
                            if (subInput[j] == subInput[k]) {
                                hasDuplicate = true;
                                break;
                            }
                        }

                        if (hasDuplicate) {
                            break;
                        }
                    }             
                    if (!hasDuplicate) {
                        partTwoAnswer = i + 14;
                        break;
                    }
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
