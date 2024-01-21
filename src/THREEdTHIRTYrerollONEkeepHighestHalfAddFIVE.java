public class THREEdTHIRTYrerollONEkeepHighestHalfAddFIVE { // rounding up when half
    public static void main(String[] args) {
        rolls results = new rolls(3, 30);
        int num = 10000000;
        amounts tracker = new amounts(20);
        for (int i = 0; i < num; i++) {
            results.rollAll();
            for (int j = 0; j < results.getSize(); j++) {
                while (results.getOutcomeAt(j) == 1) {
                    results.reRollAt(j);
                }
            }
            results.dropLowest();
            results.dropLowest();
            tracker.addRoll((int)(Math.ceil(results.returnSum()/2.0))+5);
            results = new rolls(3, 30);
        }
        tracker.printArray(num);
    }
}
