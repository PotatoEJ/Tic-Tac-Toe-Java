////////////////////////////////
//
//   Ethan-John Rasmussen
//   Assignment 7
//   11/09/2017
//
////////////////////////////////
////////////////////////////////////////////////////////////////
//
//   DESCRIPTION: This program allows the user to play a two player   
//                game of tic tac toe, which can reset after finishing.
//
//   INPUTS: Users input the row and collumn of what space they want to play.
//
//   OUTPUTS: The gameboard that fills with game pieces as the game is played
//
////////////////////////////////////////////////////////////////
package tictactoemain;

import java.util.Scanner;

public class TicTacToeMain {
    Scanner scnr = new Scanner(System.in);
    
    // Initialize private fields
    private char[][] gameBoard = new char[3][3];
    private String winner = "";
    
    /*****************************************************************
    *
    * Name: displayBoard
    * Description: Takes in the 2 dimensional char array and displays them inside a grid
    * Inputs: Two dimensional array of type char
    * Output: displays game board.
    * Preconditions: N/A
    *
    *
    *****************************************************************/ 
    public void displayBoard() {
        System.out.println(" " + gameBoard[0][0] + " | " + gameBoard[0][1] + " | " + gameBoard[0][2]);
        System.out.println("-----------");
        System.out.println(" " + gameBoard[1][0] + " | " + gameBoard[1][1] + " | " + gameBoard[1][2]);
        System.out.println("-----------");
        System.out.println(" " + gameBoard[2][0] + " | " + gameBoard[2][1] + " | " + gameBoard[2][2]);
    }
    
    /*****************************************************************
    *
    * Name: addMove
    * Description: Prompts the user for input of row and column.
    *              Only allows a space that has not been played.
    *              Computes which character to use X or O, depending on moveCounter
    * Inputs: Two integers indicating the index of a 2d array.
    * Output: Outputs a char to a specified array position.
    * Preconditions: User inputs must be from 1-3, and the specified position
    *                must not already have been played.
    * 
    *
    * @param moveCounter
    *****************************************************************/ 
    public void addMove(int moveCounter) {
        // Initialize variables
        int rowNum;
        int columnNum;
        char playerChar = ' ';
        
        // Assign the current  player character based on the amount of moves made so far.
        // First move is O then X then O repeating.
        if (moveCounter % 2 == 1) {
            playerChar = 'X';
        }
        else playerChar = 'O';
        
        // Prompt user for input.
        do {
            // Prompt for the row number, only accepts 0<X<4
            do {
                System.out.println("Enter the row (1-3) for " + playerChar);
                rowNum = scnr.nextInt();
            }while (!((rowNum > 0) && (rowNum < 4)));
            
            // Prompt for the column number, only accepts 0<X<4
            do {
                System.out.println("Enter the column (1-3) for " + playerChar);
                columnNum = scnr.nextInt();
            }while (!((columnNum > 0) && (columnNum < 4)));
        // Check if position has already been played.
        }while (gameBoard[rowNum - 1][columnNum - 1] != ' ');
        
        // Assign the value pf playerChar to the user defined position.
        gameBoard[rowNum - 1][columnNum - 1] = playerChar;   
    }
    
    /*****************************************************************
    *
    * Name: completeGameCheck
    * Description: Accesses the values of the 2D Array.
    *              Checks to see if game has been won or tied.
    *              Returns true if game is complete, false if incomplete.
    *              If game is complete sets String winner to the appropriate value.
    * Inputs: One integer moveCounter, 2D char array
    * Output: One boolean, and assigns a winner.
    * Preconditions: Equal values must also not be spaces. Otherwise game would finish at startup.
    *
    *
    * @param moveCounter
    * @return true or false.
    *****************************************************************/ 
    public boolean completeGameCheck(int moveCounter) {
        // For loop checks to see if any rows are equal and not spaces.
        for (int i = 0; i < 3; ++i) {
            if ((gameBoard[i][0] == gameBoard[i][1])&&(gameBoard[i][1] == gameBoard[i][2])&&(gameBoard[i][0] != ' ')) {
                winner = Character.toString(gameBoard[i][0]);
                return true;
            }
        }
        
        // For loop checks to see if columns are equal and not spaces.
        for (int i = 0; i < 3; ++i) {
            if ((gameBoard[0][i] == gameBoard[1][i])&&(gameBoard[1][i] == gameBoard[2][i])&&(gameBoard[0][i] != ' ')) {
                winner = Character.toString(gameBoard[0][i]);
                return true;
            }
        }
        
        // Check diagonal top left bottom right.
        if ((gameBoard[0][0] == gameBoard[1][1])&&(gameBoard[1][1] == gameBoard[2][2])&&(gameBoard[0][0] != ' ')) {
            winner = Character.toString(gameBoard[0][0]);
            return true;
        }
        
        // Check diagonal top right bottom left
        if ((gameBoard[0][2] == gameBoard[1][1])&&(gameBoard[1][1] == gameBoard[2][0])&&(gameBoard[0][2] != ' ')) {
            winner = Character.toString(gameBoard[0][2]);
            return true;
        }
        
        // If game has not been won and the moveCounter is 9 this means the game has been tied.
        if (moveCounter == 9) {
            winner = "tie";
            return true;
        }
        
        // For all else return false, game has not been finished and no winner yet.
        return false;
    }
    
    /*****************************************************************
    *
    * Name: resetGameBoard
    * Description: Resets the values of the char array to spaces.
    * Inputs: One 2D char array.
    * Output: Modified array filled with spaces.
    * Preconditions: N/A
    *
    *
    *****************************************************************/ 
    public void resetGameBoard() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                gameBoard[i][j] = ' ';
            }
        }
    }
    
    /*****************************************************************
    *
    * Name: announceWinner
    * Description: Uses class field winner to print out the winner of the game.
    * Inputs: One String winner
    * Output: Print statement announcing winner.
    * Preconditions: N/A
    *
    *
    *****************************************************************/ 
    public void announceWinner() {
        if (winner.equals("tie")) {
            System.out.println("Tie game.");
        }
        else System.out.println("The winner is: " + winner + ".");
    }
    
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        
        // Use default java constructor to create an instance
        TicTacToeMain game1 = new TicTacToeMain();
        
        // Initialize variables
        int moveCounter;
        char userIn;
        
        do {
            game1.resetGameBoard();
            moveCounter = 0;
            game1.displayBoard();
            // Will loop until game has been completed.
            while(!game1.completeGameCheck(moveCounter)) {
                game1.addMove(moveCounter);
                ++moveCounter;
                game1.displayBoard();
            }
            game1.announceWinner();
            // Prompt user to play again.
            System.out.println("Would you like to play another game? Enter 'y' to continue.");
            userIn = scnr.next().charAt(0);
        }while (userIn == 'y');
    }
    
}
