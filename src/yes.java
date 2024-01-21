public class yes {
    public static void main(String[] args) {
        rolls results = new rolls(3, 8);
        int num = 5000000;
        amounts tracker = new amounts(19);
        for (int i = 0; i < num; i++) {
            results.rollAll();
            results.dropLowest();
            tracker.addRoll(results.returnSum()+3);
            results = new rolls(3,8);
        }
        tracker.printArray(num);
    }
}
