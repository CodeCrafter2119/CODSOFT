import java.util.Scanner;
import java.util.Random;

public class NumberGame {
    private static final int MAX_ATTEMPTS = 10;
    private static final int MIN_RANGE = 1;
    private static final int MAX_RANGE = 100;
    
    private static int totalRounds = 0;
    private static int totalWins = 0;
    private static int bestScore = Integer.MAX_VALUE;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;
        
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I'm thinking of a number between " + MIN_RANGE + " and " + MAX_RANGE + ".");
        System.out.println("You have " + MAX_ATTEMPTS + " attempts to guess it.\n");
        
        while (playAgain) {
            playRound(scanner);
            
            System.out.print("\nWould you like to play again? (yes/no): ");
            String response = scanner.next().toLowerCase();
            playAgain = response.equals("yes") || response.equals("y");
        }
        
        displayFinalStats();
        scanner.close();
    }
    
    private static void playRound(Scanner scanner) {
        Random random = new Random();
        int targetNumber = random.nextInt(MAX_RANGE - MIN_RANGE + 1) + MIN_RANGE;
        int attempts = 0;
        boolean hasWon = false;
        
        totalRounds++;
        
        System.out.println("\nRound " + totalRounds + " - Let's begin!");
        
        while (attempts < MAX_ATTEMPTS && !hasWon) {
            System.out.print("Attempt " + (attempts + 1) + "/" + MAX_ATTEMPTS + " - Enter your guess: ");
            
            try {
                int userGuess = scanner.nextInt();
                attempts++;
                
                if (userGuess == targetNumber) {
                    hasWon = true;
                    totalWins++;
                    
                    if (attempts < bestScore) {
                        bestScore = attempts;
                    }
                    
                    System.out.println("Congratulations! You guessed the number in " + attempts + " attempts!");
                } else if (userGuess < targetNumber) {
                    System.out.println("Too low! Try a higher number.");
                } else {
                    System.out.println("Too high! Try a lower number.");
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid number!");
                scanner.next(); // Clear the invalid input
            }
        }
        
        if (!hasWon) {
            System.out.println("\nGame over! You've used all your attempts.");
            System.out.println("The correct number was: " + targetNumber);
        }
    }
    
    private static void displayFinalStats() {
        System.out.println("\nGame Statistics:");
        System.out.println("Total rounds played: " + totalRounds);
        System.out.println("Rounds won: " + totalWins);
        
        if (totalWins > 0) {
            System.out.println("Best score (fewest attempts to win): " + bestScore);
            System.out.println("Win rate: " + (totalWins * 100 / totalRounds) + "%");
        }
        
        System.out.println("\nThanks for playing! Goodbye!");
    }
}
