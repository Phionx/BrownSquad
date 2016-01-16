import java.util.*;

public class Tetris{
    public static final int time = 200;
    public int GridX, GridY;
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
   
    public static final GridPiece [][] O_Piece = new GridPiece [2][2];
    public static final GridPiece [][] J_Piece = new GridPiece [3][3];
    public static final GridPiece [][] L_Piece = new GridPiece [3][3];
    public static final GridPiece [][] I_Piece = new GridPiece [4][4];
    public static final GridPiece [][] S_Piece = new GridPiece [3][3];
    public static final GridPiece [][] T_Piece = new GridPiece [3][3];
    public static final GridPiece [][] Z_Piece = new GridPiece [3][3];

    public ArrayList<GamePiece> GameObjects = new ArrayList<GamePiece>();  
    public GridPiece [][] Game;
    
    public Tetris(int SizeX, int SizeY){ 
        GridX = SizeX; GridY = SizeY;
        if(SizeX < 10) GridX = 10;
        if (SizeY < 10) GridY = 10;
        Game = new GridPiece [GridY][GridX];
        init();
        initPieces();
    }

    public void initPieces(){
        O_Piece[0][0] = new GridPiece("Game", "O");
        O_Piece[0][1] = new GridPiece("Game", "O");
        O_Piece[1][0] = new GridPiece("Game", "O");
        O_Piece[1][1] = new GridPiece("Game", "O");

        J_Piece[0][0] = new GridPiece("Game", "J");
        J_Piece[0][1] = new GridPiece("Game", "J");
        J_Piece[0][2] = new GridPiece("Game", "J");
        J_Piece[1][0] = new GridPiece("Game", "J");
        J_Piece[1][1] = new GridPiece("Game", "J");
        J_Piece[1][2] = new GridPiece("Game", "J");
        J_Piece[2][0] = new GridPiece("Game", "J");
        J_Piece[2][1] = new GridPiece("Game", "J");
        J_Piece[2][2] = new GridPiece("Game", "J");

        I_Piece[0][0] = new GridPiece("Game", "I");
        I_Piece[0][1] = new GridPiece("Game", "I");
        I_Piece[0][2] = new GridPiece("Game", "I");
        I_Piece[0][3] = new GridPiece("Game", "I");
        I_Piece[1][0] = new GridPiece("Game", "I");
        I_Piece[1][1] = new GridPiece("Game", "I");
        I_Piece[1][2] = new GridPiece("Game", "I");
        I_Piece[1][3] = new GridPiece("Game", "I");
        I_Piece[2][0] = new GridPiece("Game", "I");
        I_Piece[2][1] = new GridPiece("Game", "I");
        I_Piece[2][2] = new GridPiece("Game", "I");
        I_Piece[2][3] = new GridPiece("Game", "I");
        I_Piece[3][0] = new GridPiece("Game", "I");
        I_Piece[3][1] = new GridPiece("Game", "I");
        I_Piece[3][2] = new GridPiece("Game", "I");
        I_Piece[3][3] = new GridPiece("Game", "I");

        L_Piece[0][0] = new GridPiece("Game", "L");
        L_Piece[0][1] = new GridPiece("Game", "L");
        L_Piece[0][2] = new GridPiece("Game", "L");
        L_Piece[1][0] = new GridPiece("Game", "L");
        L_Piece[1][1] = new GridPiece("Game", "L");
        L_Piece[1][2] = new GridPiece("Game", "L");
        L_Piece[2][0] = new GridPiece("Game", "L");
        L_Piece[2][1] = new GridPiece("Game", "L");
        L_Piece[2][2] = new GridPiece("Game", "L");

        S_Piece[0][0] = new GridPiece("Game", "S");
        S_Piece[0][1] = new GridPiece("Game", "S");
        S_Piece[0][2] = new GridPiece("Game", "S");
        S_Piece[1][0] = new GridPiece("Game", "S");
        S_Piece[1][1] = new GridPiece("Game", "S");
        S_Piece[1][2] = new GridPiece("Game", "S");
        S_Piece[2][0] = new GridPiece("Game", "S");
        S_Piece[2][1] = new GridPiece("Game", "S");
        S_Piece[2][2] = new GridPiece("Game", "S");

        T_Piece[0][0] = new GridPiece("Game", "T");
        T_Piece[0][1] = new GridPiece("Game", "T");
        T_Piece[0][2] = new GridPiece("Game", "T");
        T_Piece[1][0] = new GridPiece("Game", "T");
        T_Piece[1][1] = new GridPiece("Game", "T");
        T_Piece[1][2] = new GridPiece("Game", "T");
        T_Piece[2][0] = new GridPiece("Game", "T");
        T_Piece[2][1] = new GridPiece("Game", "T");
        T_Piece[2][2] = new GridPiece("Game", "T");

        Z_Piece[0][0] = new GridPiece("Game", "Z");
        Z_Piece[0][1] = new GridPiece("Game", "Z");
        Z_Piece[0][2] = new GridPiece("Game", "Z");
        Z_Piece[1][0] = new GridPiece("Game", "Z");
        Z_Piece[1][1] = new GridPiece("Game", "Z");
        Z_Piece[1][2] = new GridPiece("Game", "Z");
        Z_Piece[2][0] = new GridPiece("Game", "Z");
        Z_Piece[2][1] = new GridPiece("Game", "Z");
        Z_Piece[2][2] = new GridPiece("Game", "Z");

    }
    public void init(){
        for(int a = 0; a < Game.length; a++){
            for(int b = 0; b < Game[a].length; b++) {
                Game[a][b] = new GridPiece("Game");
            }
        }

        for(int i = 0; i < Game[0].length; i++){//Setting the Top and Bottom
            Game[0][i] = new GridPiece("Top");
            Game[Game.length - 1][i] = new GridPiece("Bottom");
        }
        for(int j = 1; j < Game.length-1; j++){//Setting the Sides
            Game[j][0] = new GridPiece("Side");
            Game[j][1] = new GridPiece("Side");
            Game[j][Game[0].length -1] = new GridPiece("Side");
            Game[j][Game[0].length -2] = new GridPiece("Side");
        }
    }
    
    public String printGame(){
        String ans = "";
        for(GridPiece[] i: Game){
            for(GridPiece j: i) {
                if(j.state){
                    ans += j.color + j.design;
                }else {
                    ans += " ";
                }
            }
            ans += "\n";
        }
        return ans;
    }

    public void newPiece(String BlockType){
        GamePiece temp = new GamePiece(GridX/2, 1, BlockType, 1);
        GameObjects.add(temp);
    }

    public void addToGame(GamePiece temp){
        int XSize, YSize, XStart, YStart;
        XSize = temp.Pos[0].length;
        YSize = temp.Pos.length;
        XStart = temp.XPos;
        YStart = temp.YPos;

        for(int i = YStart; i < YStart + YSize; i++){
            for(int j = XStart; j < XStart + XSize; j++){
                if(temp.Pos[i-YStart][j-XStart]){
                    Game[i][j] = new GridPiece("Game", temp.Block);
                    Game[i][j].state = true;
                }
            }
        }
    }
    private static void clear(){
        final String clear = "\u001b[2J";
        final String home = "\u001b[H";
        System.out.print(clear + home);
        System.out.flush();
    }
    
    private static void delay(){
        try {
            Thread.sleep(time);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main (String [] args){
        Tetris test = new Tetris(50, 50);
        clear();
        System.out.println(test.printGame());
        test.newPiece("T");
        test.addToGame(test.GameObjects.get(0));
        delay();
        clear();
        System.out.println(test.printGame());
    }


}
