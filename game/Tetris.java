public class Tetris{
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

    public GridPiece [][] Game;
    
    public Tetris(int SizeX, int SizeY){
        GridX = SizeX; GridY = SizeY;
        Game = new GridPiece [GridY][GridX];
        init();
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
                    ans += j.design + j.color;
                }else {
                    ans += " ";
                }
            }
            ans += "\n";
        }
        return ans;
    }
    private static void clear(){
        final String clear = "\u001b[2J";
        final String home = "\u001b[H";
        System.out.print(clear + home);
        System.out.flush();
    }
    
    private static void delay(){
        try {
            Thread.sleep(200);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main (String [] args){
        Tetris test = new Tetris(50, 50);
        clear();
        System.out.println(test.printGame());
    }


}
