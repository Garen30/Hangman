import java.util.Scanner;

public class Hangman {
    private static String[] words = {"java", "programming", "computer", "algorithm", "software"};
    private static String word;
    private static char[] guessedLetters;
    private static int attemptsLeft;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        word = getRandomWord();
        guessedLetters = new char[word.length()];
        attemptsLeft = 7;

        // Initialize guessedLetters array
        for (int i = 0; i < guessedLetters.length; i++) {
            guessedLetters[i] = '_';
        }

        System.out.println("Welcome to Hangman!");
        System.out.println("Guess the word:");

        while (attemptsLeft > 0 && !isWordGuessed()) {
            System.out.println("Attempts left: " + attemptsLeft);
            System.out.println("Current progress: " + String.valueOf(guessedLetters));

            System.out.print("Enter a letter: ");
            char guess = scanner.next().charAt(0);

            if (!isLetterGuessed(guess)) {
                if (isLetterInWord(guess)) {
                    System.out.println("Correct guess!");
                } else {
                    System.out.println("Incorrect guess!");
                    attemptsLeft--;
                }
            } else {
                System.out.println("You've already guessed this letter.");
            }
        }

        if (isWordGuessed()) {
            System.out.println("Congratulations! You guessed the word: " + word);
        } else {
            System.out.println("Sorry, you ran out of attempts. The word was: " + word);
        }
        scanner.close();
    }//End main()

    private static String getRandomWord() {
        int index = (int) (Math.random() * words.length);
        return words[index];
    }

    private static boolean isLetterGuessed(char letter) {
        for (char c : guessedLetters) {
            if (c == letter) {
                return true;
            }
        }
        return false;
    }

    private static boolean isLetterInWord(char letter) {
        boolean found = false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                guessedLetters[i] = letter;
                found = true;
            }
        }
        return found;
    }

    private static boolean isWordGuessed() {
        for (char c : guessedLetters) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }
}
