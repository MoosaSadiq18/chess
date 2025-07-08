package game.chess;

public class Pieces {
    public boolean pawnIsValid(int player,int p1, int p2, int d1, int d2, Board chessBoard) {
        boolean hasValue = false;

        int rookLike = p2 - d2;
        int y = d1 - p1;
        int x = d2 - p2;

        if (player == 0) {
            if ((x == 1 && y == 1) || (x == 1 && y == -1)) {
                if (chessBoard.board[d2][d1] != '.') {
                    hasValue = true;
                }
            }
            return hasValue || (rookLike == -1 && p1 == d1);
        } else {
            if ((x == 1 && y == 1) || (x == -1 && y == 1)) {
                if (chessBoard.board[d2][d1] != '.') {
                    hasValue = true;
                }
            }
            return hasValue || (rookLike == 1 && p1 == d1);
        }
    }

    public boolean bishopIsValid(int p1, int p2, int d1, int d2) {

        int bishOne = p2 - d2;
        int bishTwo = p1 - d1;
        if (bishOne < 0) {
            bishOne = -bishOne;
        }
        if (bishTwo < 0) {
            bishTwo = -bishTwo;
        }

        return bishOne == bishTwo;
    }

    public boolean rookIsValid(int p1, int p2, int d1, int d2) {

        return (p2 == d2 || p1 == d1);
    }

    public boolean queenIsValid(int p1, int p2, int d1, int d2) {

        return (bishopIsValid(p1, p2, d1, d2) ||
                rookIsValid(p1, p2, d1, d2));
    }

    public boolean kingIsValid(int p1, int p2, int d1, int d2) {

        int rookLike = (p2 - p1) - (d2 - d1);
        if (rookLike < 0) {
            rookLike = -(rookLike);
        }

        int bishopLike = (p2 - p1) - (d2 - d1);
        if (bishopLike < 0) {
            bishopLike = -(bishopLike);
        }

        return (rookLike == 1) || (bishopLike == 0 || bishopLike == 2);
    }

    public boolean knightIsValid(int p1, int p2, int d1, int d2) {

        int knightOne = p2 + p1;
        int knightTwo = d2 + d1;
        int diff = knightTwo - knightOne;

        if (diff < 0) {
            diff = -(diff);
        }

        return diff == 3 || diff == 1;
    }


    //blocks
    public boolean rookIsBlocked(int p1, int p2, int d1, int d2, Board chessBoard) {
        boolean isBlocked = false;

        if (p1 == d1 && p2 > d2) {          //1,0 -> 6,0    ,i=2, j=6;
            int i = p2-1;
            while (i != d2) {
                if (chessBoard.board[i][p1] != '.') {
                    isBlocked = true;
                    break;
                }
                i--;
            }
        } else if (p1 == d1 && p2 < d2) {
            int i = p2+1;
            while (i != d2) {
                if (chessBoard.board[i][p1] != '.') {
                    isBlocked = true;
                    break;
                }
                i++;
            }
        } else if (p2 == d2 && p1 > d1) {
            int i = p1-1;
            while (i != d1) {
                if (chessBoard.board[p2][i] != '.') {
                    isBlocked = true;
                    break;
                }
                i--;
            }
        } else if (p2 == d2 && p1 < d1) {
            int i = p1+1;
            while (i != d1) {
                if (chessBoard.board[p2][i] != '.') {
                    isBlocked = true;
                    break;
                }
                i++;
            }
        }

        return isBlocked;
    }

    public boolean bishopIsBlocked(int p1, int p2, int d1, int d2, Board chessBoard){

        boolean isBlocked = false;
        int i = p2;
        int j = p1;

        while(i!=d2 && j!=d1) {
            if (i > d2) {
                if (j > d1) {
                    i--;
                    j--;
                }else if(j < d1){
                    i--;
                    j++;
                }
            }
            else if(i < d2){
                if(j > d1){
                    i++;
                    j--;
                }else if(j < d1){
                    i++;
                    j++;
                }
            }

            if(chessBoard.board[i][j]!='.'){
                isBlocked = true;
                break;
            }
        }
        return isBlocked;
    }

    public boolean queenIsBlocked(int p1, int p2, int d1, int d2, Board chessBoard, Pieces piece){
        return piece.rookIsBlocked(p1 ,p2, d1, d2, chessBoard)
                ||
               piece.bishopIsBlocked(p1 ,p2, d1, d2, chessBoard);
    }
}
