import java.util.*;

public class FOURdSIXrerollONEsONCEandDropLowest {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int num = 5000000;
        amounts tracker = new amounts(18);
        rolls results = new rolls(4, 6);
        for (int i = 0; i < num; i++) {
            results.rollAll();
            for (int j = 0; j < results.getSize(); j++) {
                if (results.getOutcomeAt(j) == 1) {
                    results.reRollAt(j);
                }
            }
            results.dropLowest();
            tracker.addRoll(results.returnSum());
            results = new rolls(4, 6);
        }
        tracker.printArray(num);
    }
}
