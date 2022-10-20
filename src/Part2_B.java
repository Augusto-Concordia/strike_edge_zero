import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part2_B {
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

    private static GameDescriptor[] ReadInFile(Scanner fileScanner) {
        int gamesNumb = fileScanner.nextInt();

        GameDescriptor[] games = new GameDescriptor[gamesNumb];

        for (int i = 0; i < gamesNumb; i++) {
            GameDescriptor game = new GameDescriptor();

            int datasetSize = fileScanner.nextInt();
            game.dataset = new int[datasetSize];

            game.initialPosition = fileScanner.nextInt(); //reads initial initialPosition position

            for (int j = 0; j < datasetSize - 1; j++) {
                game.dataset[j] = fileScanner.nextInt();
            }

            game.dataset[datasetSize - 1] = 0;

            games[i] = game;
        }

        return games;
    }

    private static GameDescriptor Solve(GameDescriptor data) {


        data.solvable = TrySolve(data);

        return data;
    }

    private static boolean TrySolve(GameDescriptor data) {


        return TrySolve(data);
    }
}