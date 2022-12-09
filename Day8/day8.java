import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class day8 {
    public static void main(String[] args) {
        ArrayList<Integer[]> mapping = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            String input;
            while ((input = reader.readLine()) != null) {
                char[] treeRow = input.toCharArray();
                Integer[] treeRowInt = new Integer[treeRow.length];
                for (int i = 0; i < treeRow.length; i++) {
                    treeRowInt[i] = Character.getNumericValue(treeRow[i]);
                }
                mapping.add(treeRowInt);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int visible_tree_count = 0;
        int max_scenic_score = 0;

        for (int i = 0; i < mapping.size(); i++) {
            for (int j = 0; j < mapping.get(i).length; j++) {
                boolean visible = false;
                int up = 0;
                int down = 0;
                int left = 0;
                int right = 0;

                for (int k = i - 1; k > -1; k--) {
                    up++;
                    if (mapping.get(i)[j] <= mapping.get(k)[j]) {
                        break;
                    }
                    if (k == 0) {
                        visible = true;
                    }
                }

                for (int k = i + 1; k < mapping.size(); k++) {
                    down++;
                    if (mapping.get(i)[j] <= mapping.get(k)[j]) {
                        break;
                    }
                    if (k == mapping.size() - 1) {
                        visible = true;
                    }
                }

                for (int k = j - 1; k > -1; k--) {
                    left++;
                    if (mapping.get(i)[j] <= mapping.get(i)[k]) {
                        break;
                    }
                    if (k == 0) {
                        visible = true;
                    }
                }

                for (int k = j + 1; k < mapping.get(i).length; k++) {
                    right++;
                    if (mapping.get(i)[j] <= mapping.get(i)[k]) {
                        break;
                    }
                    if (k == mapping.get(i).length - 1) {
                        visible = true;
                    }
                }

                int scenic_score = up*down*left*right;

                if (visible || scenic_score == 0) {
                    visible_tree_count++;
                }

                if (scenic_score > max_scenic_score) {
                    max_scenic_score = scenic_score;
                }
            }
        }

        // Part 1 Answer
        System.out.println(visible_tree_count);

        // Part 2 Answer
        System.out.println(max_scenic_score);
    }
}
