// programmer: Russell Vanderveen
// date: 3/4/2022
// class: CS 145
// program: Assignment #1 Word Search Generator
// this program will create a Word Search puzzle
import java.util.*;

public class RVWordSearchTest {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();// capture words
        char [][] board = new char[0][0];// game board for word Search
        int length = 0;// length of game board
        int width = 0;// width of game board
        RVWordSearchGenerator.menu(words, board, length, width);
    }// end of main
}// end of wordSearchTest
