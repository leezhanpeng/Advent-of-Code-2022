import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class day10 {
    public static void main(String[] args) {

        HashMap<String, Integer> information = new HashMap<>();
        information.put("signalSum", 0);
        information.put("regX", 1);
        information.put("cycleCount", 0);
        information.put("pixelIndex", 0);

        ArrayList<String> CRT_image = new ArrayList<>();
        String currentCRTRow = "";

        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));

            String input;
            while ((input = reader.readLine()) != null) {
                currentCRTRow = cycleExecution(information, currentCRTRow, CRT_image);
                information.replace("cycleCount", information.get("cycleCount") + 1);
                information.replace("signalSum", information.get("signalSum") + checkSignalStrength(information.get("cycleCount")) * information.get("regX"));
                
                if (!input.equals("noop")) {
                    String[] instruction = input.split(" ");
                    currentCRTRow = cycleExecution(information, currentCRTRow, CRT_image);
                    information.replace("cycleCount", information.get("cycleCount") + 1);
                    information.replace("regX", information.get("regX") + Integer.parseInt(instruction[1]));
                    information.replace("signalSum", information.get("signalSum") + checkSignalStrength(information.get("cycleCount")) * information.get("regX"));
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Part 1 Answer
        System.out.println(information.get("signalSum"));

        // Part 2 Answer
        for (String CRTRow : CRT_image) {
            System.out.println(CRTRow);
        }
    }

    public static String cycleExecution(HashMap<String, Integer> information, String currentCRTRow, ArrayList<String> CRT_image) {
        int pixelIndex = information.get("pixelIndex");
        int regX = information.get("regX");
        
        if (pixelIndex == regX || pixelIndex == regX + 1 || pixelIndex == regX - 1) {
            currentCRTRow += "#";
        } else {
            currentCRTRow += " ";
        }

        information.replace("pixelIndex", pixelIndex + 1);

        if (pixelIndex + 1 == 40) {
            CRT_image.add(currentCRTRow);
            currentCRTRow = "";
            information.replace("pixelIndex", 0);
        }

        return currentCRTRow;
    }

    public static int checkSignalStrength(int cycleCount) {
        if ((cycleCount - 20) % 40 == 0) {
            return cycleCount;
        }

        return 0;
    }
}
