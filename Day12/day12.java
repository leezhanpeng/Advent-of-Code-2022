import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class day12 {
    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> heightmap = new ArrayList<>();
        Integer[] startingPoint = new Integer[2];
        Integer[] endingPoint = new Integer[2];

        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            String input;
            while ((input = reader.readLine()) != null) {
                ArrayList<Integer> row = new ArrayList<>();
                heightmap.add(row);

                for (char point : input.toCharArray()) {
                    if (point == 'S') {
                        heightmap.get(heightmap.size() - 1).add(0);
                        startingPoint[0] = heightmap.size() - 1;
                        startingPoint[1] = heightmap.get(heightmap.size() - 1).size() - 1;
                    } else if (point == 'E') {
                        heightmap.get(heightmap.size() - 1).add(25);
                        endingPoint[0] = heightmap.size() - 1;
                        endingPoint[1] = heightmap.get(heightmap.size() - 1).size() - 1;
                    } else {
                        heightmap.get(heightmap.size() - 1).add((int) point - 97);
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Part 1 Answer
        System.out.println(findShortestPath(true, heightmap, startingPoint, endingPoint));

        // Part 2 Answer
        System.out.println(findShortestPath(false, heightmap, endingPoint, startingPoint));
    }

    public static int findShortestPath(boolean isPartOne, ArrayList<ArrayList<Integer>> heightmap, Integer[] startingPoint, Integer[] endingPoint) {
        Queue<Integer[]> BFSQueue = new LinkedList<>();
        Integer[] start = {startingPoint[0], startingPoint[1], 0};
        BFSQueue.offer(start);

        HashSet<Integer[]> tracker = new HashSet<>();
        tracker.add(startingPoint);

        while (BFSQueue.size() > 0) {

            Integer[] currentPoint = BFSQueue.poll();
            ArrayList<Integer[]> nextPoints = new ArrayList<>();
            if ((isPartOne && currentPoint[0] == endingPoint[0] && currentPoint[1] == endingPoint[1]) || (!isPartOne && heightmap.get(currentPoint[0]).get(currentPoint[1]) == 0)) {
                return currentPoint[2];
            }

            Integer[] up = {currentPoint[0] - 1, currentPoint[1]};
            if (currentPoint[0] > 0 && !hasPoint(tracker, up)) {
                nextPoints.add(up);
            }

            Integer[] down = {currentPoint[0] + 1, currentPoint[1]};
            if (currentPoint[0] < heightmap.size() - 1 && !hasPoint(tracker, down)) {
                nextPoints.add(down);
            }

            Integer[] left = {currentPoint[0], currentPoint[1] - 1};
            if (currentPoint[1] > 0 && !hasPoint(tracker, left)) {
                nextPoints.add(left);
            }

            Integer[] right = {currentPoint[0], currentPoint[1] + 1};
            if (currentPoint[1] < heightmap.get(currentPoint[0]).size() - 1 && !hasPoint(tracker, right)) {
                nextPoints.add(right);
            }

            for (Integer[] nextPoint : nextPoints) {
                int fromPosition = heightmap.get(nextPoint[0]).get(nextPoint[1]);
                int toPosition = heightmap.get(currentPoint[0]).get(currentPoint[1]);

                if (isPartOne) {
                    int temp = fromPosition;
                    fromPosition = toPosition;
                    toPosition = temp;
                }

                if (toPosition - fromPosition <= 1) {
                    Integer[] positionToQueue = {nextPoint[0], nextPoint[1], currentPoint[2] + 1};
                    
                    BFSQueue.offer(positionToQueue);
                    tracker.add(nextPoint);
                }
            }

        }
        return Integer.MAX_VALUE;
    }

    public static boolean hasPoint(HashSet<Integer[]> tracker, Integer[] point) {
        for (Integer[] trackedPoint : tracker) {
            if (trackedPoint[0] == point[0] && trackedPoint[1] == point[1]) {
                return true;
            }
        }

        return false;
    }
}