package omok;

import java.util.Arrays;
import java.util.Scanner;

public class OmokGame implements OmokInterface{
    Player player1;
    Player player2;
    Board board;
    int turn;

    public OmokGame(){}
    public OmokGame(Player player1, Player player2, Board board, int turn){
        this.player1 = player1;
        this.player2 = player2;
        this.board = board;
        this.turn = turn;
    }

    public void boardview(){
        int arrLength = this.board.array.length;
        int i;
        for(i = 0; i < arrLength; i++){
            System.out.print(String.format("%d  ", i+1));
            for(int j = 0; j < arrLength; j ++){
                System.out.print(String.format("%c ", this.board.array[i][j]));
            }
            System.out.println();
        }
        System.out.print("   ");
        for(i = 0; i < arrLength; i++){
            System.out.print(String.format("%c ", (char)i+65));
        }
        System.out.println("\n");
    }
    public int[] returnlocation(Scanner s){
        int[] location = {-1, -1};
        int row;
        String column;
        System.out.print("Enter Row >> ");
        while ((row = s.nextInt()) > this.board.array.length){
            s.nextLine();
            System.out.print("Error. Reenter the row >> ");
        }
        s.nextLine();
        System.out.print("Enter Column >> ");
        column = s.nextLine();
        while ((int)column.charAt(0) - 65 > this.board.array.length){
            System.out.print("Error. Reenter the column >> ");
            column = s.nextLine();
        }

        if (this.board.array[row - 1][(int)column.charAt(0) - 65] == '.'){
            location[0] = row - 1;
            location[1] = (int)column.charAt(0) - 65;
        }
        return location;
    }
    public int judge_win_vertical(int row, int column, Player player){
        char fpiece = player.piece.charAt(0);
        int cnt = 0;
        int tmp_row = row;
        int tmp_col = column;
        while(tmp_row >= 0 & tmp_col >= 0 & tmp_row < this.board.array.length & tmp_col < this.board.array.length){
            if (this.board.array[tmp_row][tmp_col] == fpiece) {
                cnt++;
                tmp_row--;
            }
            else{
                break;
            }
        }
        tmp_row = row + 1;
        tmp_col = column;

        if (cnt == 5){
            return 1;
        }
        else{
            while(tmp_row >= 0 & tmp_col >= 0 & tmp_row < this.board.array.length & tmp_col < this.board.array.length){
                if(this.board.array[tmp_row][tmp_col] == fpiece) {
                    cnt++;
                    tmp_row++;
                }
                else{
                    break;
                }
            }
            return (cnt == 5) ? 1 : 0;
        }
    }
    public int judge_win_lower_right(int row, int column, Player player){
        char fpiece = player.piece.charAt(0);
        int cnt = 0;
        int tmp_row = row;
        int tmp_col = column;
        while(tmp_row >= 0 & tmp_col >= 0 & tmp_row < this.board.array.length & tmp_col < this.board.array.length){
            if (this.board.array[tmp_row][tmp_col] == fpiece){
                cnt++;
                tmp_row--;
                tmp_col--;
            }
            else{
                break;
            }
        }
        tmp_row = row + 1;
        tmp_col = column + 1;
        if (cnt == 5){
            return 1;
        }
        else{
            while(tmp_row >= 0 & tmp_col >= 0 & tmp_row < this.board.array.length & tmp_col < this.board.array.length){
                if(this.board.array[tmp_row][tmp_col] == fpiece){
                    cnt++;
                    tmp_row++;
                    tmp_col++;
                }
                else{
                    break;
                }
            }
            return (cnt == 5) ? 1 : 0;
        }
    }
    public int judge_win_horizon(int row, int column, Player player){
        char fpiece = player.piece.charAt(0);
        int cnt = 0;
        int tmp_row = row;
        int tmp_col = column;
        while(tmp_row >= 0 & tmp_col >= 0 & tmp_row < this.board.array.length & tmp_col < this.board.array.length){
            if(this.board.array[tmp_row][tmp_col] == fpiece){
                cnt++;
                tmp_col--;
            }
            else{
                break;
            }
        }
        tmp_row = row;
        tmp_col = column + 1;

        if (cnt == 5){
            return 1;
        }
        else{
            while(tmp_row >= 0 & tmp_col >= 0 & tmp_row < this.board.array.length & tmp_col < this.board.array.length){
                if(this.board.array[tmp_row][tmp_col] == fpiece){
                    cnt++;
                    tmp_col++;
                }
                else{
                    break;
                }
            }
            return (cnt == 5) ? 1 : 0;
        }
    }
    public int judge_win_upper_right(int row, int column, Player player){
        char fpiece = player.piece.charAt(0);
        int cnt = 0;
        int tmp_row = row;
        int tmp_col = column;
        while(tmp_row >= 0 & tmp_col >= 0 & tmp_row < this.board.array.length & tmp_col < this.board.array.length){
            if(this.board.array[tmp_row][tmp_col] == fpiece){
                cnt++;
                tmp_row++;
                tmp_col--;
            }
            else{
                break;
            }
        }
        tmp_row = row - 1;
        tmp_col = column + 1;
        if (cnt == 5){
            return 1;
        }
        else{
            while(tmp_row >= 0 & tmp_col >= 0 & tmp_row < this.board.array.length & tmp_col < this.board.array.length){
                if(this.board.array[tmp_row][tmp_col] == fpiece){
                    cnt++;
                    tmp_row--;
                    tmp_col++;
                }
                else{
                    break;
                }
            }
            return (cnt == 5) ? 1 : 0;
        }
    }
    public boolean judge_win(int row, int column, Player player){
        int numflag = 0;
        numflag = judge_win_vertical(row, column, player);
        if (numflag == 1) return true;
        numflag = judge_win_lower_right(row, column, player);
        if (numflag == 1) return true;
        numflag = judge_win_horizon(row, column, player);
        if (numflag == 1) return true;
        numflag = judge_win_upper_right(row, column, player);
        if (numflag == 1) return true;
        return false;
    }
    public void playturn(int row, int column, Player player){
        this.board.array[row][column] =  player.piece.charAt(0);
    }
}
