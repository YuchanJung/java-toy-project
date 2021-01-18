package omok;

import java.util.Scanner;

public class OmokGameMain {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        Board playboard;
        OmokGame maingame;
        int size;
        int[] locarray;
        Player player1 = new Player();
        Player player2 = new Player();

        System.out.print("player1's name >> ");
        player1.name = s.nextLine();

        System.out.print("player1's piece(O/X) >> ");

        while (!((player1.piece = s.nextLine()).equals("O") | player1.piece.equals("X"))){
            System.out.print("Error. Reenter the piece >> ");
        }

        System.out.print("player2's name >> ");
        player2.name = s.nextLine();
        player2.piece = player1.piece.equals("O") ? "X" : "O";

        player1.turn = player1.piece.equals("O") ? 0 : 1;
        player2.turn = player2.piece.equals("O") ? 0 : 1;

        System.out.print("Enter the size of the board >> ");
        while ((size = s.nextInt()) < 5){
            s.nextLine();
            System.out.print("Error. Reenter the size >> ");
        }
        playboard = new Board(size);
        maingame = new OmokGame(player1, player2, playboard, 0);
        maingame.boardview();

        // start

        while(true){
            Player tmpplayer = (player1.turn == maingame.turn) ? player1 : player2;
            System.out.println(String.format("%s's turn", tmpplayer.name));

            while ((locarray = maingame.returnlocation(s))[0] == -1){
                System.out.println("Error. Reenter the location.");
            }


            maingame.playturn(locarray[0], locarray[1], tmpplayer);
            maingame.boardview();
            if (maingame.judge_win(locarray[0], locarray[1],tmpplayer)){
                System.out.println(String.format("%s win!",tmpplayer.name));
                break;
            }
            maingame.turn += (maingame.turn == 0) ? 1 : -1;
        }
    }
}
