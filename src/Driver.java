import java.util.Scanner;

/**
 * Created by Anirwan on 17/02/2016.
 */
public class Driver {

    public static void main (String[] args) {
        int userInput = 0;

        while(userInput == 0) {
            userInput = choose();
        }

        FruitSelector fruits = new FruitSelector(userInput);
        fruits.compute();
        fruits.print();
    }

    public static int choose() {
        int input;
        Scanner scan = new Scanner(System.in);

        System.out.println("--------------------------------------------");
        System.out.println("       WELCOME TO THE FRUIT MARKET          ");
        System.out.println("--------------------------------------------");
        System.out.println("1. Market 1 (5 fruits, $500 cash in hand)");
        System.out.println("2. Market 2 (13 fruits, $400 cash in hand)");
        System.out.print("\nPlease choose a scenario: ");

        input = scan.nextInt();

        if(input < 1 || input > 2) {
            System.err.println("Incorrect input, please try again: ");
            return 0;
        } else {
            scan.close();
            return input;
        }
    }
}
