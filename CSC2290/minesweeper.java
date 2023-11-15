// Minesweeper V2 / Jake Roman / CSC2290
import java.util.*;

public class Minesweeper {
    // Main Function
    public static void main(String[] args) {
        // Welcome
        Scanner input = new Scanner(System.in);
        System.out.println("  _____ ______   ___  ________   _______   ________  ___       __   _______   _______   ________  _______   ________      \n |\\   _ \\  _   \\|\\  \\|\\   ___  \\|\\  ___ \\ |\\   ____\\|\\  \\     |\\  \\|\\  ___ \\ |\\  ___ \\ |\\   __  \\|\\  ___ \\ |\\   __  \\       \n \\ \\  \\\\\\__\\ \\  \\ \\  \\ \\  \\\\ \\  \\ \\   __/|\\ \\  \\___|\\ \\  \\    \\ \\  \\ \\   __/|\\ \\   __/|\\ \\  \\|\\  \\ \\   __/|\\ \\  \\|\\  \\    \n  \\ \\  \\\\|__| \\  \\ \\  \\ \\  \\\\ \\  \\ \\  \\_|/_\\ \\_____  \\ \\  \\  __\\ \\  \\ \\  \\_|/_\\ \\  \\_|/_\\ \\   ____\\ \\  \\_|/_\\ \\   _  _\\   \n   \\ \\  \\    \\ \\  \\ \\  \\ \\  \\\\ \\  \\ \\  \\_|\\ \\|____|\\  \\ \\  \\|\\__\\_\\  \\ \\  \\_|\\ \\ \\  \\_|\\ \\ \\  \\___|\\ \\  \\_|\\ \\ \\  \\\\  \\   \n    \\ \\__\\    \\ \\__\\ \\__\\ \\__\\\\ \\__\\ \\_______\\____\\_\\  \\ \\____________\\ \\_______\\ \\_______\\ \\__\\    \\ \\_______\\ \\__\\\\ _\\  \n     \\|__|     \\|__|\\|__|\\|__| \\|__|\\|_______|\\_________\\|____________|\\|_______|\\|_______|\\|__|     \\|_______|\\|__|\\|__| \n                                             \\|_________|"); // this gigantic line displays the ASCII logo art
        System.out.println("Select Difficulty:");
        System.out.println("1 - Novice\t9x9\t10 Mines");
        System.out.println("2 - Adept\t16x16\t40 Mines");
        System.out.println("3 - Master\t16x30\t99 Mines");
        int difficulty = Integer.parseInt(input.nextLine());

        // Configuration
        int boardRows = 9; // height of the board
        int boardCols = 9; // width of the board
        int boardMines = 10;
        if (difficulty == 2) {
            // Intermediate
            boardRows = 16;
            boardCols = 16;
            boardMines = 40;
        }
        else if (difficulty == 3) {
            // Armageddon
            boardRows = 16;
            boardCols = 32;
            boardMines = 99;
        }

        // Initialization
        Random rng = new Random(); // centralized random object to allow setting of the seed
        int[][] board = new int[boardRows][boardCols]; // number of adjacent mines (0-8) or -1 mine
        boolean[][] explored = new boolean[boardRows][boardCols]; // keeps track of cleared cells
        int[][] mines = new int[boardMines][2]; // list of all mines on the board (row, col)
        int[] choice; // keeps track of the row and column that the player chose

        // Logic
        setMines(board,mines,rng);
        while (true) {
            // Display
            countAdjacentMines(board);
            showBoard(board, explored);

            // Input
            choice = getUserChoice(explored, input);

            // Process
            explored[choice[0]][choice[1]] = true;
            if (board[choice[0]][choice[1]] == 0) {
                // player chose a cell with no adjacent mines, recursively fill
                floodFill(board, explored, choice[0], choice[1]);
            }
            if (isGameOver(board, explored)) {
                // a win or lose condition was encountered, end the game loop
                break;
            }
        }
    }

    // Core Functions
    public static void setMines(int[][] board, int[][] mines, Random rng) {
        // Places the number of mines equal to the length of the mines array.
        int minesToPlace = mines.length;
        int minesPlaced = 0;
        int boardRows = board.length;
        int boardCols = board[0].length;

        while (minesPlaced < minesToPlace) {
            // while we still have mines to place, keep trying
            int row = rng.nextInt(0,boardRows-1);
            int col = rng.nextInt(0,boardCols-1);
            if (board[row][col] >= 0) {
                // there is not currently a mine in this position, place one
                board[row][col] = -1;
                mines[minesPlaced][0] = row;
                mines[minesPlaced][1] = col;
                minesPlaced++;
            }
        }
    }

    public static void countAdjacentMines(int[][] board) {
        // Updates the board state to count how many mines are adjacent to each cell
        int[][] adjacent = {{-1,-1},{0,-1},{1,-1},{-1,0},{1,0},{-1,1},{0,1},{1,1}}; // array of X,Y relations to each cell, this allows me to use one for loop instead of copy/pasting 8 times
        int boardRows = board.length;
        int boardCols = board[0].length;

        for (int y = 0; y < boardRows; y++) {
            for (int x = 0; x < boardCols; x++) {
                // iterate through each cell and calculate adjacent mines
                int mineCount = 0;
                for (int i = 0; i < adjacent.length; i++) {
                    int ax = x + adjacent[i][0];
                    int ay = y + adjacent[i][1];
                    if (ax >= 0 && ay >= 0 && ax < boardCols && ay < boardRows) { // validate that this adjacent position is within our board so we don't get an error
                        if (board[ay][ax] == -1) {
                            mineCount++;
                        }
                    }
                }
                if (board[y][x] >= 0) {
                    // this cell is not a mine, set it to the number of adjacent mines
                    board[y][x] = mineCount;
                }
            }
        }
    }

    public static void showBoard(int[][] board, boolean[][] explored) {
        // Writes the current board state to the screen
        System.out.println();
        int boardRows = board.length;
        int boardCols = board[0].length;
        String scalerCharacter = ""; // can be used to widen everything by 1 pixel for larger boards.
        if (boardCols > 9) {
            scalerCharacter = " ";
        }
        // Header
        System.out.print("    "+scalerCharacter);
        for (int i = 0; i < boardCols; i++) {
            System.out.print((i)+" ".repeat(2-Integer.toString(i).length())+scalerCharacter);
        }
        System.out.println("\n"+"-".repeat((boardCols*(2+scalerCharacter.length()))+4));
        // Board
        for (int y = 0; y < boardRows; y++) {
            System.out.print((y)+" ".repeat(2-Integer.toString(y).length())+scalerCharacter+"| ");
            for (int x = 0; x < boardCols; x++) {
                int cell = board[y][x];
                if (!explored[y][x]) // unexplored cell
                    System.out.print(". ");
                else if (cell == -1) // mine
                    System.out.print("x ");
                else if (cell == -2) // defused mine
                    System.out.print("* ");
                else if (cell == 0) // no adjacent mines
                    System.out.print("  ");
                else if (cell > 0) // some adjacent mines
                    System.out.print(cell+" ");
                System.out.print(scalerCharacter);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int[] getUserChoice(boolean[][] explored, Scanner input) {
        // Prompts the user for which cell they would like to explore
        while (true) { // Infinite loop, so we can continue asking until a valid input is provided
            System.out.print("Select a valid cell (row,col): ");
            String str = input.nextLine();
            String[] split = str.split(" "); // break up the string by spaces
            if (split.length == 2) {
                // input was two values
                if (isNumber(split[0]) && isNumber(split[1])) {
                    // inputs are valid numbers
                    int[] choice = {Integer.parseInt(split[0]), Integer.parseInt(split[1])}; // convert strings to integers and put them in the choice array (row,col)

                    // check within bounds
                    if (choice[0] >= 0 && choice[1] >= 0 && choice[0] < explored.length && choice[1] < explored[0].length) { // check if input is within the board
                        // check for explored cells
                        if (explored[choice[0]][choice[1]]) {
                            System.out.println("Sorry, that cell has already been explored.");
                            continue;
                        }

                        // end function and return the user's choice
                        return choice; 
                    }
                }
            }

            System.out.println("Invalid input. Example: 1 1");
        }
    }

    public static void floodFill(int[][] board, boolean[][] explored, int row, int col) {
        // Recursively clears out empty cells
        int[][] adjacent = {{-1,-1},{0,-1},{1,-1},{-1,0},{1,0},{-1,1},{0,1},{1,1}}; // array of X,Y relations to each cell, this allows me to use one for loop instead of copy/pasting 8 times
        int boardRows = board.length;
        int boardCols = board[0].length;
        for (int i = 0; i < adjacent.length; i++) {
            int ax = col + adjacent[i][0];
            int ay = row + adjacent[i][1];
            if (ax >= 0 && ay >= 0 && ax < boardCols && ay < boardRows) {
                if (board[ay][ax] == 0 && !explored[ay][ax]) {
                    explored[ay][ax] = true;
                    floodFill(board, explored, ay, ax);
                }
                if (board[ay][ax] >= 0) {
                    explored[ay][ax] = true;
                }
            }
        }
    }

    public static boolean isGameOver(int[][] board, boolean[][] explored) {
        // Checks the board and explored state to determine if the game has been won or lost
        int boardRows = board.length;
        int boardCols = board[0].length;
        boolean unexploredCells = false; // boolean to track if there are any cells left to explore that are not mines
        boolean mineHit = false; // booelan to track if the player has hit any mine cells

        // Scan Board
        for (int y = 0; y < boardRows; y++) {
            for (int x = 0; x < boardCols; x++) {
                // check each cell
                int cell = board[y][x];
                if (cell >= 0 && !explored[y][x]) {
                    // unexplored safe cell, we are not in a win state
                    unexploredCells = true;
                }
                if (cell == -1 && explored[y][x]) {
                    // explored mine, we have lost the game
                    mineHit = true;
                }
            }
        }

        // Check Conditions
        if (mineHit) {
            // you lose! (mine was hit)
            for (int y = 0; y < boardRows; y++) {
                for (int x = 0; x < boardCols; x++) {
                    // set mines to explored
                    if (board[y][x] == -1) {
                        explored[y][x] = true;
                    }
                }
            }
            showBoard(board, explored);
            System.out.println("YOU LOSE!");
            return true;
        }
        else if (!unexploredCells) {
            // you win! (no cells left to explore)
            for (int y = 0; y < boardRows; y++) {
                for (int x = 0; x < boardCols; x++) {
                    // set mines to defused mines
                    if (board[y][x] == -1) {
                        board[y][x] = -2;
                        explored[y][x] = true;
                    }
                }
            }
            showBoard(board, explored);
            System.out.println("YOU WIN!");
            return true;
        }

        // Continue Playing
        return false;
    }

    // Additional Functions
    public static boolean isNumber(String str) {
        // Returns whether or not the input string is a valid integer
        boolean validNumber = true;
        for (int i=0; i<str.length(); i++) {
            char ch = str.charAt(i);
            if (!Character.isDigit(ch)) {
                validNumber = false;
            }
        }
        return validNumber;
    }
}
