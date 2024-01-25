import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DatabaseHandler {

    //timeToReroll can be inputted as whatever if reroll is false, won't check it at all, best idea is to default to -1 apparently
    public static String buildRollStringID(int diceCount, int diceType, boolean reroll, int timeToReroll, boolean[] numbersToReroll, int high, int low, String divTime, int addSub) {
        String out = diceCount + "d" + diceType;
        if (reroll) {
            out += "T" + timeToReroll + "re_";
            for (int i = 1; i < numbersToReroll.length; i++) {
                if (numbersToReroll[i]) {
                    out += i + "_";
                }
            }
        } else {
            out += "F_";
        }
        out += "high" + high + "low" + low;
        if (divTime.charAt(0) == '*' || divTime.charAt(0) == 'x' || divTime.charAt(0) == 'X') {
            out += "time";
        } else {
            out += "div";
        }
        out += divTime.substring(1);
        out += "add" + addSub;
        return out;
    }

    //use BufferedReader instead of Scanner apparently

    public static boolean createFile(String ID) throws IOException {
        File fileyBoy = new File("database\\" + ID + ".txt");
        if (fileyBoy.exists()) {
            return false;
        }
        fileyBoy.createNewFile();
        FileWriter writeShitter = new FileWriter(fileyBoy);
        writeShitter.write("IT FUCKING WORKS LETS GOOOOOOOOOOOO");
        writeShitter.flush();
        return true;
    }
}
