import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Part2_A {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Not enough arguments, exiting...");
        }

        if (args.length > 3) {
            System.out.println("Too many arguments, exiting...");
            return;
        }

        String inFileName = args[0];
        String outFileName = args[1];

        Scanner fileInScanner;

        try {
            fileInScanner = new Scanner(new File(inFileName));
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Input file not found, exiting...");
            return;
        }

        var readGames = ReadInFile(fileInScanner);

        for (int i = 0; i < readGames.length; i++) {
            readGames[i] = Solve(readGames[i]);
        }
    }

    /**
     * Reads input file and creates a GameDescriptor object for each game in the file.
     *
     * @param fileScanner Scanner object to read the file.
     * @return An array of GameDescriptor objects.
     */
    private static GameDescriptor[] ReadInFile(Scanner fileScanner) {
        GameDescriptor[] descriptors = new GameDescriptor[0];

        try {
            int gamesNumb = fileScanner.nextInt();

            descriptors = new GameDescriptor[gamesNumb];

            for (int i = 0; i < gamesNumb; i++) {
                GameDescriptor game = new GameDescriptor();

                //reads the dataset size
                int datasetSize = fileScanner.nextInt();
                game.dataset = new int[datasetSize];

                //reads initial initialPosition position
                game.initialPosition = fileScanner.nextInt();

                //reads each value of the dataset
                for (int j = 0; j < datasetSize - 1; j++) {
                    game.dataset[j] = fileScanner.nextInt();
                }

                game.dataset[datasetSize - 1] = 0;

                descriptors[i] = game;
            }
        } catch (InputMismatchException inputMismatchException) {
            System.out.println("Input file incorrectly formatted, exiting...");
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println("Input file missing elements, exiting...");
        } catch (IllegalStateException illegalStateException) {
            System.out.println("Could not read input file, exiting...");
        }

        return descriptors;
    }

    private static void WriteOutFile(GameDescriptor[] descriptors){

    }

    /**
     * Starts the solving algorithm with each game
     *
     * @param descriptor GameDescriptor object describing the game.
     * @return The same GameDescriptor object, with an updated resolvability flag.
     */
    private static GameDescriptor Solve(GameDescriptor descriptor) {
        descriptor.solvable = TrySolve(descriptor.dataset.clone(), descriptor.dataset[descriptor.initialPosition]);

        return descriptor;
    }


    /**
     * Checks if the dataset is solvable.
     *
     * @param dataset Integer array to be checked.
     * @param sum     The signed sum of all moves
     * @return True if the dataset is solvable; false otherwise.
     */
    private static boolean TrySolve(int[] dataset, int sum) {
        //our current position is too large for the dataset or we've already been here: we've failed
        if (sum >= dataset.length || dataset[sum] == -1) return false;
        //if we reached the end of the dataset: we've succeeded
        if (sum == dataset.length - 1) return true;

        int move = dataset[sum];
        int currentMove = 0;

        if (sum + move < dataset.length) {
            currentMove = move; //we're moving right peeps, because we're within bounds
        } else if (sum - move >= 0) {
            currentMove = -move; //we're moving left peeps, because we're within bounds
        }

        dataset[sum] = -1; //invalidate this position
        sum = sum + currentMove;

        return TrySolve(dataset, sum);
    }
}