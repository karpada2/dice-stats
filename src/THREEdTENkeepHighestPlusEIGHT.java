import java.util.*;

public class THREEdTENkeepHighestPlusEIGHT {

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        rolls results = new rolls(3, 10);
        int num = 5000000;
        amounts tracker = new amounts(18);
        for (int i = 0; i < num; i++) {
            results.rollAll();
            results.dropLowest();
            results.dropLowest();
            tracker.addRoll(results.returnSum() + 8);
            results = new rolls(3, 10);
        }
        tracker.printArray(num);
    }
}



