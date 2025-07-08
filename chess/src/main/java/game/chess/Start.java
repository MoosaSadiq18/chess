package game.chess;

import java.util.Scanner;

public class Start {

    public void input(int player, Board chessBoard, Pieces piece) {

        Scanner input = new Scanner(System.in);
        String one, two;


        while (true) {

            System.out.print("Enter current position: ");
            one = input.next();
            System.out.print("Enter destination: ");
            two = input.next();

            if(one.length()<2 || two.length()<2){
                System.out.println("Input must have 2 characters!");
                continue;
            }

            int p1 = Character.getNumericValue(one.charAt(0)) - 10;
            int p2 = one.charAt(1) - '0' - 1;
            int d1 = Character.getNumericValue(two.charAt(0)) - 10;
            int d2 = two.charAt(1) - '0' - 1;


            if (one.charAt(0) < 'a' || one.charAt(0) > 'h' ||
                    two.charAt(0) < 'a' || two.charAt(0) > 'h' ||
                    one.charAt(1) < '0' || one.charAt(1) > '8' ||
                    two.charAt(1) < '0' || two.charAt(1) > '8') {
                System.out.println("Invalid input, try again.");
                System.out.println();
                continue;
            }
            else if(!chessBoard.validTurn(one.charAt(0) - 'a', one.charAt(1) - '1', player)){
                System.out.println("You can't play opponent's move!");
                System.out.println();
                continue;
            }
            else if(!chessBoard.selfAttack(one.charAt(0) - 'a', one.charAt(1) - '1'
                    ,two.charAt(0) - 'a', two.charAt(1) - '1', player)){
                System.out.println("You can't attack on yourself!");
                System.out.println();
                continue;
            }
            else if (chessBoard.board[p2][p1] == 'p' || chessBoard.board[p2][p1] == 'P') {
                if (!piece.pawnIsValid(player,p1, p2, d1, d2, chessBoard)) {
                    System.out.println("Invalid Pawn Move");
                    System.out.println();
                    continue;
                }
            }
            else if (chessBoard.board[p2][p1] == 'b' || chessBoard.board[p2][p1] == 'B') {
                if (!(piece.bishopIsValid(p1, p2, d1, d2))) {
                    System.out.println("Invalid Bishop Move");
                    System.out.println();
                    continue;
                }else if(piece.bishopIsBlocked(p1, p2, d1, d2, chessBoard)){
                    System.out.println("Bishop can't cross another piece!");
                    System.out.println();
                    continue;
                }
            }
            else if (chessBoard.board[p2][p1] == 'r' || chessBoard.board[p2][p1] == 'R') {
                if (!(piece.rookIsValid(p1, p2, d1, d2))) {
                    System.out.println("Invalid Rook Move");
                    System.out.println();
                    continue;
                }else if(piece.rookIsBlocked(p1, p2, d1, d2, chessBoard)){
                    System.out.println("Rook can't cross another piece!");
                    System.out.println();
                    continue;
                }
            }
            else if (chessBoard.board[p2][p1] == 'q' || chessBoard.board[p2][p1] == 'Q') {
                if (!(piece.queenIsValid(p1, p2, d1, d2))) {
                    System.out.println("Invalid Queen Move");
                    System.out.println();
                    continue;
                }else if(piece.queenIsBlocked(p1, p2, d1, d2, chessBoard,piece)){
                    System.out.println("Queen can't cross another piece!");
                    System.out.println();
                    continue;
                }
            }
            else if (chessBoard.board[p2][p1] == 'k' || chessBoard.board[p2][p1] == 'K') {
                if (!(piece.kingIsValid(p1, p2, d1, d2))) {
                    System.out.println("Invalid King Move");
                    System.out.println();
                    continue;
                }
            }
            else if (chessBoard.board[p2][p1] == 'n' || chessBoard.board[p2][p1] == 'N') {
                if (!(piece.knightIsValid(p1, p2, d1, d2))) {
                    System.out.println("Invalid Knight Move");
                    System.out.println();
                    continue;
                }
            }
            break;
        }

        int p_1 = Character.getNumericValue(one.charAt(0)) - 10;
        int p_2 = one.charAt(1) - '0' - 1;
        int d_1 = Character.getNumericValue(two.charAt(0)) - 10;
        int d_2 = two.charAt(1) - '0' - 1;

        System.out.println(p_1 + 1 + " " + (p_2 + 1));
        System.out.println(d_1 + 1 + " " + (d_2 + 1));

        chessBoard.dynamicBoard(p_1, p_2, d_1, d_2);
    }
}

