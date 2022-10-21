//COMP 352 - Assignment #2, Part 2B
//Due Date: October 30th
//Written by: Augusto Mota Pinheiro (40208080)

import java.io.*;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * StrikeZeroEdge solver class for the iterative method.
 */
public class Part2_B {
    /**
     * Base class that describes a game. It's used for easier communication between methods.
     * Structured as a data type, all its attributes are public (similar to structs in other languages).
     */
    private static class GameDescriptor {
        /**
         * Game dataset.
         */
        public int[] dataset;

        /**
         * Initial position of the game.
         */
        public int initialPosition;

        /**
         * Whether the game is solvable or not.
         */
        public boolean solvable;
    }

    /**
     * Driver method for the iterative solution.
     * @param args Command line arguments: input file name and output file name.
     */
    public static void main(String[] args) {
        //argument count check
        if (args.length < 2) {
            System.out.println("Not enough arguments, exiting...");
        }

        if (args.length > 3) {
            System.out.println("Too many arguments, exiting...");
            return;
        }

        //argument parsing
        String inFileName = args[0];
        String outFileName = args[1];

        //input file parsing
        GameDescriptor[] readGames = ReadInFile(inFileName);

        //solving
        for (int i = 0; i < readGames.length; i++) {
            readGames[i] = Solve(readGames[i]);
        }

        //output file writing
        WriteOutFile(outFileName, readGames);
    }

    /**
     * Reads input file and creates a GameDescriptor object for each game in the file.
     *
     * @param fileName The name of the input file.
     * @return An array of GameDescriptor objects.
     */
    private static GameDescriptor[] ReadInFile(String fileName) {
        Scanner fileScanner;
        GameDescriptor[] descriptors = new GameDescriptor[0];

        try {
            fileScanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Input file not found, exiting...");
            return new GameDescriptor[0];
        }

        try {
            //reads the number of games in the file
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

        fileScanner.close(); //for good measure

        return descriptors;
    }

    /** Writes out the results of the games to the output file.
     * @param fileName The desired name of the output file.
     * @param descriptors The array of GameDescriptor objects to write out.
     */
    private static void WriteOutFile(String fileName, GameDescriptor[] descriptors) {
        try {
            File fileOut = new File(fileName);

            if (!fileOut.exists()) {
                fileOut.createNewFile();
            }

            PrintWriter fileWriter = new PrintWriter(fileOut);

            for (GameDescriptor descriptor : descriptors) {
                fileWriter.println(descriptor.solvable ? "1" : "0");
                fileWriter.flush();
            }

            fileWriter.close();
        } catch (SecurityException securityException) {
            System.out.println("Denied access to the file, exiting...");
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Could not write to output file, exiting...");
        } catch (IOException ioException) {
            System.out.println("Could not create output file, exiting...");
        }
    }

    /**
     * Starts the solving algorithm for each game.
     *
     * @param descriptor GameDescriptor object describing the game.
     * @return The same GameDescriptor object, with an updated resolvability flag.
     */
    private static GameDescriptor Solve(GameDescriptor descriptor) {
        descriptor.solvable = TrySolve(descriptor.dataset, descriptor.dataset[descriptor.initialPosition]);

        return descriptor;
    }

    /**
     * Iteratively checks if the dataset is solvable.
     *
     * @param dataset Integer array to be checked.
     * @param sum     The signed sum of all moves.
     * @return True if the dataset is solvable; false otherwise.
     */
    private static boolean TrySolve(int[] dataset, int sum) {
        ArrayList<Integer> positionsToCheck = new ArrayList<>(dataset.length);

        //populate our array list with our indices
        for (int i = 0; i < dataset.length; i++)
            positionsToCheck.Add(i, dataset[i]);

        //keep moving the marker until we reach the end or an invalid index
        while (positionsToCheck.Get(sum) != -1 && sum < positionsToCheck.Size() - 1) {
            int move = positionsToCheck.Get(sum);
            int currentMove = 0;

            if (sum + move < positionsToCheck.Size()) {
                currentMove = move; //we're moving right peeps, because we're within bounds
            } else if (sum - move >= 0) {
                currentMove = -move; //we're moving left peeps, because we're within bounds
            }

            positionsToCheck.Set(sum, -1); //invalidate this position
            sum = sum + currentMove; //move to the next
        }

        return sum == dataset.length - 1; //returns if we have reached the end (or not)
    }
}