import java.util.Scanner;
import java.util.Random;
public class numberguess {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        Random random = new Random();
        int numberToGuess = random.nextInt(100) +1;
        int numberofTries = 0;
        int guess;
        boolean win = false;
        System.out.println("welcome to the number guessing game! Try to guess the number between 1 to 100");
        while (!win) {
            System.out.print("Enter your guess: ");
            guess = scanner.nextInt();
            numberofTries++;
            if (guess == numberToGuess) {
                win = true;
                System.out.println("Congratulations! You guessed the number in " + numberofTries + " tries.");
            } else if (guess < numberToGuess) {
                System.out.println("Too low! Try again.");
            } else {
                System.out.println("Too high! Try again.");
            }
        }
    }
}