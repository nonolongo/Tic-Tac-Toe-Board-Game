import java.util.Scanner;
import java.util.Random;

public class TicTacToe {

    // Place your methods here
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numplayers = getNumberPlayers(scan);
        if (numplayers == 1)
            onePlayerGame(scan);
        if (numplayers == 2)
            twoPlayerGame(scan);
    }

    public static void onePlayerGame(Scanner scan) {
        Board board = new Board();
        Random rand = new Random();
        while (board.getGameState() == GameState.ONGOING) {
            System.out.println(board);
            Location nextLocation = getInput("X", scan);
            while (!board.placement(nextLocation, 'X')) {
                nextLocation = getInput("X", scan);
            }
            board.placement(nextLocation, 'X');
            int computerCoordX = rand.nextInt(3);
            int computerCoordY = rand.nextInt(3);
            Location computerCoord = new Location(computerCoordX, computerCoordY);
            while (!board.placement(computerCoord, 'O')) {
                computerCoordX = rand.nextInt(3);
                computerCoordY = rand.nextInt(3);
                computerCoord = new Location(computerCoordX, computerCoordY);
            }
            if (board.getGameState() != GameState.ONGOING) {
                if (board.getGameState() == GameState.PLAYER1_WIN) {
                    System.out.println(board);
                    System.out.println("Player One won!");
                }
                if (board.getGameState() == GameState.PLAYER2_WIN) {
                    System.out.println(board);
                    System.out.println("Player Two won!");
                }
                if (board.getGameState() == GameState.TIE) {
                    System.out.println(board);
                    System.out.println("It is a tie!");
                }
            }
        }

    }

    public static void twoPlayerGame(Scanner scan) {
        Board board = new Board();
        while (board.getGameState() == GameState.ONGOING) {
            System.out.println(board);
            Location nextLocation = getInput("X", scan);
            while (!board.placement(nextLocation, 'X')) {
                nextLocation = getInput("X", scan);
            }
            if (board.getGameState() == GameState.PLAYER1_WIN || board.getGameState() == GameState.PLAYER2_WIN
                    || board.getGameState() == GameState.TIE) {
                System.out.println(board);
                break;
            }

            System.out.println(board);
            nextLocation = getInput("O", scan);
            while (!board.placement(nextLocation, 'O')) {
                nextLocation = getInput("O", scan);
            }

        }
        if (board.getGameState() == GameState.PLAYER1_WIN)
            System.out.println("Player One won!");
        if (board.getGameState() == GameState.PLAYER2_WIN)
            System.out.println("Player Two won!");
        if (board.getGameState() == GameState.TIE)
            System.out.println("It is a tie!");

    }

    private static int getNumberPlayers(Scanner sc) {
        boolean repeatPrompt = true;
        int numPlayers = 0;
        while (repeatPrompt) {
            System.out.print("How many players (1 or 2)? ");
            String input = sc.next();
            try {
                numPlayers = Integer.parseInt(input);
                if (numPlayers == 1 || numPlayers == 2) {
                    repeatPrompt = false;
                } else {
                    System.out.println("Enter 1 or 2 players.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please only enter a number.");
            }
        }
        return numPlayers;
    }

    private static Location getInput(String player, Scanner sc) {
        boolean repeatPrompt = true;
        int row = -1;
        int col = -1;
        while (repeatPrompt) {
            System.out.print("Enter desired square for " + player + ": ");
            String input = sc.next();
            input = input.trim();
            String[] splitInput = input.split(",");
            try {
                row = Integer.parseInt(splitInput[0]);
                col = Integer.parseInt(splitInput[1]);
                repeatPrompt = false;
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Please follow the format 'row,col'; for ex '1,2'");
            }
        }
        Location loc = new Location(row, col);
        return loc;
    }

}
