// programmer: Russell Vanderveen
// date: 3/4/2022
// class: CS 145
// program: Assignment #1 Word Search Generator
// this program will create a Word Search puzzle
import java.util.*;

public class RVWordSearchGenerator {
    // this method will print a menu for the program
    public static void menu(List<String> words, char [][] board,
                            int length, int width){
        int response;
        System.out.println("Please select an option");
        System.out.println("Generate a new word search [1]");
        System.out.println("Print out your word search [2]");
        System.out.println("Show the solution to your word search [3]");
        System.out.println("Quit the program [4]");
        Scanner input = new Scanner(System.in);
        String answer = input.next();
        try {// this try catch statement for catch non-int values
            response = Integer.parseInt(answer);
        }// end of try
        catch (Exception e) {// if non-int value given, this will print
            System.out.println("invalid option");
            System.out.println();
            menu(words, board, length, width);
        }// end of catch
        int choice = Integer.parseInt(answer);
        printChoice(choice, input, words, board, length, width);
    }// end of menu

    // this method will capture the user's input and use the corresponding method
    public static void printChoice(int choice, Scanner input, List<String> words,
                                   char [][] board, int length, int width){
        switch(choice){
            case 1: // generates words for the word Search
                generate(input, words, board, length, width);
                break;
            case 2:// creates board and places generated words into it
                createBoard(words, board, length, width);
                break;
            case 3:// adds random letters to empty spaces on the board
                completeBoard(words, board, length, width);
                break;
            case 4:// exits the game
                System.exit(0);
                break;
            default:// captures a non 1-4 answer
                System.out.println("Invalid input");
                menu(words, board, length, width);
                break;
        }// end of switch/case
    }// end of printChoice

    // this method will store the words the user creates
    public static void generate(Scanner input, List<String> words,
                                char [][] board, int length, int width){
        System.out.println("How many words would you like to add?");
        int response = input.nextInt();
        getWords(input, response, words);
        System.out.println("Words have been added");
        System.out.println();
        menu(words, board, length, width);
    } // end of generateWordSearch

    // this method will generate a certain amount of words from the user
    public static List<String> getWords(Scanner input, int response,
                                        List<String> words){
        // populates words arraylist with every word created by user
        for(int i = 0; i <response; i++){
            System.out.println("Word: ");
            String wordCapture = input.next();
            words.add(wordCapture);
        }// end of for loop
        return words;
    }// end of getWords

    // this method will create the game board's width and length
    public static void createBoard(List<String> words, char [][] board,
                                  int length, int width){
        if(words.isEmpty()){
            System.out.println("Please add some words first!");
            System.out.println();
            menu(words, board, length, width);
        }// end of if statement

        // sets the length of the longest word as the board's width
        for(int i =0; i<words.size(); i++){
            if(words.get(i).length() > width){
                width = words.get(i).length();
            }// end of if statement
        }// end of for loop

        width = width *2;
        length = width + (width/4);
        board = new char[width][length];
        fillBoard(board, words, width, length);
    }// end of createBoard

    // this method will place out all the created words into the game board
    public static void fillBoard(char [][] board, List<String> words,
                                     int width, int length){
        int betweenWords;
        int stringLength;
        int x, y;
        int numOfWords = words.size();
        int [] wordPlacement = new int[numOfWords];

        // places each word into the board's plain
        for(int i =0; i < numOfWords; i++){
            stringLength = words.get(i).length();
            betweenWords = width -stringLength;
            x = randomRange(0, betweenWords);
            y = randomRange(0, length);
            if(findSpace(wordPlacement,  y) ){
                y++;
            }// end of if statement
            wordPlacement[i] = y;

            // places a specific word's characters into the board
            for(int j =0; j <stringLength; j++){
                board[x][y] = words.get(i).charAt(j);
                x++;
            }// end of integrated for loop
        }// end of for loop
        printBoard(board);
        menu(words, board, length, width);
    }// end of fillBoard

    // this method will find empty space on the board for a word
    public static boolean findSpace(int [ ] wordPlacement, int y) {
        for (int i = 0; i < wordPlacement.length; i++){
            if (wordPlacement[i] == y){
                return true;
            }// end of if statement
        }// end of for loop
        return false;
    }// end of findSpace

    // this method will print the board
    public static void printBoard(char [][] board){
        for(int i=0; i<board.length; i++) {// prints current word search board
            for(int j=0; j<board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }// end of nested for loop
            System.out.println();
        }// end of for loop
    }// end of printBoard

    // this method picks a random number from a range of two set numbers
    public static int randomRange(int lowestNum, int highestNum){
        Random rand = new Random();
        return rand.nextInt(highestNum-lowestNum+1) + lowestNum;
    } //end of randomRange

    // this method fills in blank spaces with random letters and complete it
    public static void completeBoard(List<String> words, char [][] board,
                                           int length, int width){
        if(words.isEmpty() || board.length == 0){
            System.out.println("Please add some words first!");
            System.out.println();
            menu(words, board, length, width);
        }// end of if statement

        for (int i =0; i <length; i ++) {// searches for empty spaces
            for (int j = 0; j < width; j++) {
                if (board[j][i] == 0) {// fills empty spaces
                    char character = (char) randomRange(97, 122);
                    board[j][i] = character;
                }// end of if statement
            }// end of nested for loop
        }// end of for loop
        printBoard(board);
        System.out.println();
        menu(words, board, length, width);
    }// end of completeBoard
}// end of WordSearchGenerator
