import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class day9 {
    public static void main(String[] args) {

        Rope ropeHead = new Rope();
        Rope knotPointer = ropeHead;
        for (int i = 0; i < 9; i++) {
            Rope middleKnot = new Rope();
            knotPointer.nextKnot = middleKnot;
            middleKnot.prevKnot = knotPointer;
            knotPointer = middleKnot;
        }
        Rope ropeEnd = knotPointer;

        ArrayList<Coordinates> knotOneTracker = new ArrayList<>();
        ArrayList<Coordinates> lastKnotTracker = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            String input;
            while ((input = reader.readLine()) != null) {
                String[] movement = input.split(" ");
                String direction = movement[0];
                int steps = Integer.parseInt(movement[1]);
                
                for (int i = 0; i < steps; i++) {
                    switch (direction) {
                        case "U":
                            ropeHead.y++;
                            break;

                        case "D":
                            ropeHead.y--;
                            break;

                        case "L":
                            ropeHead.x--;
                            break;

                        case "R":
                            ropeHead.x++;
                            break;
                    }
                    moveRope(ropeHead);

                    Coordinates knotOneLocation = new Coordinates();
                    knotOneLocation.x = ropeHead.nextKnot.x;
                    knotOneLocation.y = ropeHead.nextKnot.y;
                    boolean newSpot = true;
                    for (Coordinates coord : knotOneTracker) {
                        if (coord.x == knotOneLocation.x && coord.y == knotOneLocation.y) {
                            newSpot = false;
                            break;
                        }
                    }
                    if (newSpot) {

                        knotOneTracker.add(knotOneLocation);
                    }

                    Coordinates lastKnotLocation = new Coordinates();
                    lastKnotLocation.x = ropeEnd.x;
                    lastKnotLocation.y = ropeEnd.y;
                    newSpot = true;
                    for (Coordinates coord : lastKnotTracker) {
                        if (coord.x == lastKnotLocation.x && coord.y == lastKnotLocation.y) {
                            newSpot = false;
                            break;
                        }
                    }
                    if (newSpot) {
                        lastKnotTracker.add(lastKnotLocation);
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
       
        // Part 1 Answer
        System.out.println(knotOneTracker.size());

        //Part 2 Answer
        System.out.println(lastKnotTracker.size());
    }

    public static void moveRope(Rope rope) {
        Rope pointerKnot = rope;
        while (pointerKnot.nextKnot != null) {
            int nextKnotX = pointerKnot.nextKnot.x;
            int nextKnotY = pointerKnot.nextKnot.y;

            int frontKnotX = pointerKnot.x;
            int frontKnotY = pointerKnot.y;

            if (Math.abs(frontKnotX - nextKnotX) == 2 && Math.abs(frontKnotY - nextKnotY) == 2) {
                if (frontKnotX > nextKnotX) {
                    pointerKnot.nextKnot.x++;

                    if (frontKnotY > nextKnotY) {
                        pointerKnot.nextKnot.y++;
                    } else {
                        pointerKnot.nextKnot.y--;
                    }
                } else {
                    pointerKnot.nextKnot.x--;

                    if (frontKnotY > nextKnotY) {
                        pointerKnot.nextKnot.y++;
                    } else {
                        pointerKnot.nextKnot.y--;
                    }
                }
            } else if (Math.abs(frontKnotX - nextKnotX) == 2) {
                pointerKnot.nextKnot.y = pointerKnot.y;

                if (frontKnotX > nextKnotX) {
                    pointerKnot.nextKnot.x++;
                } else {
                    pointerKnot.nextKnot.x--;
                }
            } else if (Math.abs(frontKnotY - nextKnotY) == 2) {
                pointerKnot.nextKnot.x = pointerKnot.x;

                if (frontKnotY > nextKnotY) {
                    pointerKnot.nextKnot.y++;
                } else {
                    pointerKnot.nextKnot.y--;
                }
            }

            pointerKnot = pointerKnot.nextKnot;
        }
    }
}

class Rope {
    int x = 0;
    int y = 0;
    Rope nextKnot;
    Rope prevKnot;
}

class Coordinates {
    int x;
    int y;
}