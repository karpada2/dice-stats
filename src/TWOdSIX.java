public class TWOdSIX {

    public static void main(String[] args) {
        rolls results = new rolls(2, 6);
        int num = 5000000; // amount of rolls to make
        amounts tracker = new amounts(12);
        for (int i = 0; i < num; i++) {
            results.rollAll();
            tracker.addRoll(results.returnSum());
            results = new rolls(2, 6);
        }
        tracker.printArray(num);
    }
}
