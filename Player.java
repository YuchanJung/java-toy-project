package omok;

public class Player {
    String name;
    String piece;
    int turn;

    public Player(){}

    public Player(String name, String piece) {
        this.name = name;
        this.piece = piece;
        this.turn = this.piece.equals("O") ? 0 : 1;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", piece='" + piece + '\'' +
                ", turn=" + turn +
                '}';
    }
}

