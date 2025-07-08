package game.chess;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Board {

    char[][] board = new char[8][8];
    char[][] savedBoard = new char[8][8];
    //char[] whitePieces = {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'};
    //char[] blackPieces = {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'};
    char[] whitePieces = {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'};
    char[] blackPieces = {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'};
    char[] characters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    Vector<Character>defeatedPieces = new Vector<>();

    String red = "\u001B[31m";
    String blue = "\u001B[34m";
    String reset = "\u001B[0m";

    public void defaultBoard() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 0) {
                    board[i][j] = whitePieces[j + 8];
                } else if (i == 1) {
                    board[i][j] = whitePieces[j];
                } else if (i == 6) {
                    board[i][j] = blackPieces[j];
                } else if (i == 7) {
                    board[i][j] = blackPieces[j + 8];
                } else {
                    board[i][j] = '.';
                }
            }
        }
    }

    public void dynamicBoard(Integer c_1, Integer c_2, Integer d_1, Integer d_2) {

        if (c_1 != null && c_2 != null && d_1 != null && d_2 != null) {
            if (board[d_2][d_1] != '.') {
                char temp = board[d_2][d_1];
                defeatedPieces.add(temp);
                board[d_2][d_1] = board[c_2][c_1];
                board[c_2][c_1] = '.';
            } else {
                char temp = board[d_2][d_1];
                board[d_2][d_1] = board[c_2][c_1];
                board[c_2][c_1] = '.';
            }
        }

        System.out.println();
        System.out.print("    ");
        for (int i = 0; i < 8; i++) {
            System.out.print(characters[i] + " ");
        }
        System.out.println();
        System.out.println();

        for (int i = 0; i < 8; i++) {
            System.out.print(i + 1 + "   ");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] >= 65 && board[i][j] <= 82) {
                    System.out.print(blue + board[i][j] + reset + " ");
                } else if (board[i][j] >= 97 && board[i][j] <= 114) {
                    System.out.print(red + board[i][j] + reset + " ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.print("  ");
            System.out.print(i + 1);
            System.out.println();
        }

        System.out.println();
        System.out.print("    ");
        for(int i=0;i<8;i++){
            System.out.print(characters[i] + " ");
        }
        System.out.println();
    }

    public boolean validTurn(int c1, int c2,int player) {
        char piece_1 = board[c2][c1];

        if (player == 0) {
            System.out.println(piece_1 >= 'A' && piece_1 <= 'Z');
            return ((piece_1 >= 'A' && piece_1 <= 'Z'));
        }
        else if (player == 1) {
            System.out.println(piece_1 >= 'a' && piece_1 <= 'z');
            return ((piece_1 >= 'a' && piece_1 <= 'z'));
        } else {
            return false;
        }
    }

    public boolean selfAttack(int c1, int c2, int d1, int d2, int player){
        char piece_1 = board[c2][c1];
        char piece_2 = board[d2][d1];

        if (player == 0) {
            return !(piece_2 >= 'A' && piece_2 <= 'Z');
        }
        else if (player == 1) {
            return !(piece_2 >= 'a' && piece_2 <= 'z');
        } else {
            return false;
        }
    }

    public void showDefeated(){
        for(int i=0;i<defeatedPieces.size();i++){
            if(defeatedPieces.get(i)>=65 && defeatedPieces.get(i)<=82){
                System.out.print(blue + defeatedPieces.get(i) + reset + " ");
            }
            else if(defeatedPieces.get(i)>=97 && defeatedPieces.get(i)<=114){
                System.out.print(red + defeatedPieces.get(i) + reset + " ");
            }
        }
    }

    public int getOneScore(){
        int score = 0;
        for(int i=0;i<defeatedPieces.size();i++){
            if(defeatedPieces.get(i)>=65 && defeatedPieces.get(i)<=82){
                score++;
            }
        }
        return score;
    }

    public int getTwoScore(){
        int score = 0;
        for(int i=0;i<defeatedPieces.size();i++){
            if(defeatedPieces.get(i)>=97 && defeatedPieces.get(i)<=114){
                score++;
            }
        }
        return score;
    }

    public boolean getState() {
        int blue = 0;
        int red = 0;

        for (int i = 0; i < 8; i++) {
            for(int j=0;j<8;j++) {
                if (board[i][j]>= 97 && board[i][j] <= 114) {
                    red++;
                } else if (board[i][j] >= 65 && board[i][j] <= 82) {
                    blue++;
                }
            }
        }
        return red == 0 || blue == 0;
    }

    public String getWinner(){
        int blue = 0;
        int red = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] >= 97 && board[i][j] <= 114) {
                    red++;
                } else if (board[i][j] >= 65 && board[i][j] <= 82) {
                    blue++;
                }
            }
        }

        if(red!=0){
            return "Red";
        }else{
            return "Blue";
        }
    }

    //
    public boolean casesDraw(Board chessBoard){
        int wKing = 0;
        int bKing = 0;
        int wBishop = 0;
        int bBishop = 0;
        int wKnight = 0;
        int bKnight = 0;
        int count = 0;

        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(board[i][j]!='.'){
                    count++;
                }
            }
        }

        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(count==3){
                    if(chessBoard.board[i][j]=='b'){
                        bBishop++;
                    }else if(chessBoard.board[i][j]=='B'){
                        wBishop++;
                    }
                    else if(chessBoard.board[i][j]=='k'){
                        bKing++;
                    } else if (chessBoard.board[i][j]=='K') {
                        wKing++;
                    }
                    else if(chessBoard.board[i][j]=='n'){
                        bKnight++;
                    }else if(chessBoard.board[i][j]=='N'){
                        wKnight++;
                    }
                }
            }
        }

        boolean q = (wKing==1) && (bKing==1);
        boolean w = (wKing==1) && (bBishop==1 && bKing==1);
        boolean e = (bKing==1) && (wBishop==1 && wKing==1);
        boolean r = (wKing==1) && (bKnight==1 && bKing==1);
        boolean t = (bKing==1) && (wKnight==1 && wKing==1);
        boolean y = (wKing==1 && wBishop==1) && (bKing==1 && bBishop==1);

        return (q || w || e || r || t || y);

    }

    public void saveGame(Board chessBoard, boolean finish){
        try {
            File file = new File("Saved.txt");
            FileWriter pieceWrite = new FileWriter(file);

            if(file.createNewFile()){
                System.out.println("Saved File created");
            }else{
                System.out.println("Already exists");
            }

            for(int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    chessBoard.savedBoard[i][j] = chessBoard.board[i][j];
                    pieceWrite.write(chessBoard.savedBoard[i][j]);
                    if(finish){
                        pieceWrite.close();
                        return;
                    }
                }
            }
            pieceWrite.write("\n");
            pieceWrite.close();
        }catch (IOException e){
            System.out.println("An error occured! ");
            e.printStackTrace();
        }
    }

}

