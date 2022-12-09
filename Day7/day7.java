import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class day7 {
    public static void main(String[] args) {
        Directory headDir = new Directory();
        headDir.name = "/";
        Directory currentDir = headDir;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            String input = reader.readLine();
            while ((input = reader.readLine()) != null) {
                String[] cmd = input.split(" ");
                if (cmd[0].equals("$")) {
                    if (cmd[1].equals("cd")) {
                        if (cmd[2].equals("..")) {
                            currentDir = currentDir.parent;
                            continue;
                        }
                        for (Directory dir : currentDir.children) {
                            if (dir.name.equals(cmd[2])) {
                                currentDir = dir;
                                break;
                            }
                        }
                    }
                } else if (cmd[0].equals("dir")) {
                    Directory childDir = new Directory();
                    childDir.parent = currentDir;
                    childDir.name = cmd[1];
                    currentDir.children.add(childDir);
                } else {
                    currentDir.fileSizes += Integer.parseInt(cmd[0]);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Part 1 Answer
        System.out.println(partOneAnswer(headDir));

        // Part 2 Answer
        System.out.println(partTwoAnswer(headDir, 30000000 - (70000000 - countSize(headDir))));
    }

    public static int countSize(Directory directory) {
        int totalSize = directory.fileSizes;
        for (Directory dir : directory.children) {
            totalSize += countSize(dir);
        }
        return totalSize;
    }

    public static int partOneAnswer(Directory directory) {
        int result = 0;
        int totalSize = countSize(directory);

        if (totalSize <= 100000) {
            result += totalSize;
        }

        for (Directory dir : directory.children) {
            result += partOneAnswer(dir);
        }

        return result;
    }

    public static int partTwoAnswer(Directory directory, int spaceNeeded) {
        int resultSize = countSize(directory);
        if (resultSize < spaceNeeded) {
            return Integer.MAX_VALUE;
        }
        for (Directory dir : directory.children) {
            int resultSubSize = partTwoAnswer(dir, spaceNeeded);
            if (resultSubSize < resultSize) {
                resultSize = resultSubSize;
            }
        }
        return resultSize;
    }
}

class Directory {
    String name;
    Directory parent;
    ArrayList<Directory> children = new ArrayList<>();
    int fileSizes = 0;
}