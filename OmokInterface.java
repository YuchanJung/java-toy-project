package omok;

import java.util.Scanner;
public interface OmokInterface {
    void boardview();
    int judge_win_vertical(int row, int column, Player player);
    int judge_win_lower_right(int row, int column, Player player);
    int judge_win_horizon(int row, int column, Player player);
    int judge_win_upper_right(int row, int column, Player player);
    boolean judge_win(int row, int column, Player player);
    int[] returnlocation(Scanner s);
    void playturn(int row, int column, Player player);

}
