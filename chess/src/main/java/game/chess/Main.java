package game.chess;

public class Main {
    public static void main(String[] args) {
        Board chessBoard = new Board();
        Pieces piece = new Pieces();
        chessBoard.defaultBoard();
        chessBoard.dynamicBoard(null, null, null, null);

        Start begin = new Start();
        boolean finish = false;
        boolean draw = false;

        String red = "\u001B[31m";
        String blue = "\u001B[34m";
        String reset = "\u001B[0m";

        while (!finish) {
            System.out.println(blue + "Player 1 (Blue): " + reset);
            begin.input(0, chessBoard, piece);

            System.out.println(red + "Player 2 (Red): " + reset);
            begin.input(1, chessBoard, piece);

            System.out.println("Defeated Pieces: ");
            System.out.print("              ");
            chessBoard.showDefeated();
            int oneScore = chessBoard.getOneScore();
            int twoScore = chessBoard.getTwoScore();
            System.out.println();
            System.out.println("Player 1's score: " + twoScore);
            System.out.println("Player 2's score: " + oneScore);
            System.out.println();
            System.out.println();
            System.out.println();

            chessBoard.saveGame(chessBoard,finish);



            finish = chessBoard.getState();
            draw = chessBoard.casesDraw(chessBoard);
            if(draw){
                System.out.println("Game drawn!");
                break;
            }
        }

        if (finish) {
            String winner = chessBoard.getWinner();
            System.out.println("The Winner is Player " + winner);
        }
    }
}
