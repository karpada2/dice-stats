import java.util.*;

public class Main {
    //recieves any num, and a String in the format of "/51" or "*124" or "x142", first meaning divide by and last two meaning multiply by
    //if it returns -1 user is an idiot, delete his system32 file.
    public static int multiplyOrDivideByStringContentAndNum(int num, String str) {
        if (str.charAt(0) == '/') {
            return (int)(Math.ceil(num / Double.valueOf(str.substring(1))));
        } else if (str.charAt(0) == '*' || str.charAt(0) == 'x' || str.charAt(0) == 'X') {
            return (int)(num * Double.valueOf(str.substring(1)));
        } else {
            return -1;
        }
    }

    public static void rerollUntilDone(boolean[] boolArr, Rolls results) {
        boolean notDone = true;
        while (notDone) {
            notDone = false;
            for (int j = 0; j < results.getSize(); j++) { //go over all dice
                for (int k = 0; k < boolArr.length; k++) {
                    if (results.getOutcomeAt(j) == k && boolArr[k]) { // if the dice result is a number we do not want, reroll
                        notDone = true;
                        results.reRollAt(j);
                    }
                }
            }
        }
    }


    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("------------------------------------------------------------");
        System.out.println("ALWAYS ENTER POSITIVE WHOLE NUMBERS, UNLESS STATED OTHERWISE");
        System.out.println("------------------------------------------------------------");
        System.out.println("Enter amount of dice");
        int amount = sc.nextInt();
        System.out.println("Enter type of dice");
        int type = sc.nextInt();
        Rolls results;
        System.out.println("Input amount of times test will repeat");
        int num = sc.nextInt();
        System.out.println("how many of the highest resulting dice should the program destroy?");
        int destroyHighAmount = sc.nextInt();
        System.out.println("how many of the lowest resulting dice should the program destroy?");
        int destroyLowAmount = sc.nextInt();
        System.out.println("What should the program multiply/divide the final added sum by? (e.g. '/92', 'x641', '*124', or 'X723')");
        sc.nextLine();
        String multiplierDivider = sc.nextLine();
        System.out.println("What should the program add after dividing or multiplying?");
        int add = sc.nextInt();
        int maxResult = multiplyOrDivideByStringContentAndNum(((amount-destroyHighAmount-destroyLowAmount) * type),multiplierDivider) + add;
        Amounts tracker = new Amounts(maxResult);
        System.out.println("Should the program reroll certain numbers? ");
        sc.nextLine();
        boolean[] reroll = {false};
        boolean shouldReroll = false;
        int rerollAmount = -1;
        if (sc.nextLine().equalsIgnoreCase("yes")) {
            shouldReroll = true;
            System.out.println("Which numbers? (enter desired numbers, seperated by spaces and end with -1 to end)");
            reroll = new boolean[type+1];
            int input = 0;
            while (input != -1) {
                input = sc.nextInt();
                if (input != -1) {
                    reroll[input] = true;
                }
            }
            System.out.println("How many times should the program reroll? (0 meaning always)");
            rerollAmount = sc.nextInt();
        }
        System.out.println("Calculating...");
        for (int i = 0; i < num; i++) {
            results = new Rolls(amount, type);
            results.rollAll();
            if (rerollAmount == 0) { //
                rerollUntilDone(reroll, results);
            } else if (rerollAmount > 0) {
                for (int l = 0; l < rerollAmount; l++) {
                    for (int j = 0; j < results.getSize(); j++) { //go over all dice
                        for (int k = 0; k < reroll.length; k++) {
                            if (results.getOutcomeAt(j) == k && reroll[k]) { // if the dice result is a number we do not want, reroll
                                results.reRollAt(j);
                            }
                        }
                    }
                }
            }
            for (int j = 0; j < destroyHighAmount; j++) {
                results.dropHighest();
            }
            for (int j = 0; j < destroyLowAmount; j++) {
                results.dropLowest();
            }
            tracker.addRoll(Math.max(multiplyOrDivideByStringContentAndNum(results.returnSum(), multiplierDivider) + add, 1));
        }
        tracker.printArray(num);
    }
    //figure out yaml, how to name the appropriate file, (oh yeah btw, each roll will have its own yaml file which will act so sort of a database, improving results the more rolls are made).
    // should also make a class that handles this shit, like it gets an amounts and updates accordingly/writes to it the results or smth.
    //alright naming format will be: number of dice + "d" + dice type + "T"/"F" for if reroll (if rerolling it will be T) + amount of times to reroll + "re" + numbers seperated by _ to reroll + "high" + number of high dice to remove + "low" + number of low dice to remove + "div"/"time" + "add"
    // for example, "4d6T1re_1_high0low1time1add0"
    //which means: 4d6 reroll 1s once, no division/multiplication, no addition/subtraction, remove only the lowest.
    // more example: "3d30F_high0low1time2add-3"
    // 3d30 no rerolls, keep highest (remove 1 lowest, non highest), time by 2 and subtract by 3
}
