public class GameBlock extends GridPiece{
    public int X, Y;

    public GameBlock(String block, int xpos, int ypos){
        super("Game",block);
        X = xpos;
        Y = ypos;
        this.state = true;
    }

    public void fall(){
        Y--;
    }
}
