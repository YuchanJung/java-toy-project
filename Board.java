package omok;

public class Board {
    char[][] array;

    public Board(){}

    public Board(int num){
        this.array = new char[num][num];
        for(int i = 0; i < num; i++){
            for(int j = 0; j < num; j++){
                this.array[i][j] = '.';
            }
        }
    }
}
