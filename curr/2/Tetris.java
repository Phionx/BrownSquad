import java.util.*;

public class Tetris{
//Instance Variables
    public static final int time = 200;
    public int GridX, GridY;
	public static String mes = "";
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
/*May not be necessary   
    public static final GridPiece [][] O_Piece = new GridPiece [2][2];
    public static final GridPiece [][] J_Piece = new GridPiece [3][3];
    public static final GridPiece [][] L_Piece = new GridPiece [3][3];
    public static final GridPiece [][] I_Piece = new GridPiece [4][4];
    public static final GridPiece [][] S_Piece = new GridPiece [3][3];
    public static final GridPiece [][] T_Piece = new GridPiece [3][3];
    public static final GridPiece [][] Z_Piece = new GridPiece [3][3];
*/
    public ArrayList<GamePiece> GameObjects = new ArrayList<GamePiece>();
    public ArrayList<GameBlock> GameBlocks = new ArrayList<GameBlock>();
    public GridPiece [][] Game;
    public GridPiece [][] GameReset;

//Initialization ------------------------------------------------------------------------------------------------------------------------
    public Tetris(int SizeX, int SizeY){ 
        GridX = SizeX; GridY = SizeY;
        if(SizeX < 10) GridX = 10;
        if (SizeY < 10) GridY = 10;
        Game = new GridPiece [GridY][GridX];
        GameReset = new GridPiece[GridY][GridX];
        init();
        //initPieces(); //May not be necessary
        for(int i = 0; i < Game.length; i++){
            for(int j = 0; j < Game[i].length; j++){
                GameReset[i][j] = Game[i][j];
            }
        }
    }
    /*May not be necessary
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
    */
    
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
    
//Movement------------------------------------------------------------------------------------------------------------------------
    public void update(String type){
        reset();
        if(GameObjects.isEmpty()){
            newPiece(type);
        }
        fallGamePiece(GameObjects.get(0));
    }
    
    public void fallGamePiece(GamePiece temp){
        GridPiece [][] tempState = Game;
        reset();
        int lowBlock = 0;
        addGameBlocks();
        for(int i = 0; i < temp.Size; i++){
            for(int j = 0; j < temp.Size; j++){
                if(temp.Pos[j][i]) lowBlock = j;
            }
            if(Game[temp.YPos + lowBlock + 1][i + temp.XPos].state){
                GamePieceToGameBlock(temp);
                addGameBlocks();
                Game = tempState;
                return;
            }
        }
        temp.YPos++;
        addGamePieces();
    }

    //work on Later
    public void fallGamePieces(){
        for(Iterator<GamePiece> i = GameObjects.iterator(); i.hasNext();){
            GamePiece temp = i.next();
            fallGamePiece(temp);
        }
    }
    

    public void fallGameBlockLine(int height){
        for(ListIterator<GameBlock> i = GameBlocks.listIterator(); i.hasNext();){
            GameBlock temp = i.next();
            if(temp.Y == height){
                temp.Y = temp.Y + 1;
                i.set(temp);
            }
        }
    }
    
    public void clearLine(int height){
        for(Iterator<GameBlock> i = GameBlocks.iterator(); i.hasNext();){
            GameBlock temp = i.next();
            if(temp.Y == height){
                i.remove();
            }
        }
        for(int j = height-1; j >= 0; j--){
            fallGameBlockLine(j);
        }
    }
    
    public void turnGamePiece(){
        
    }
    
    public void moveGamePiece(){
        
    }
    
    
    
//Transition------------------------------------------------------------------------------------------------------------------------
    public void GamePieceToGameBlock(GamePiece temp){
        GameObjects.remove(temp);
        for(int i = 0; i < temp.Size; i++){
            for(int j = 0; j < temp.Size; j++){
                if(temp.Pos[i][j]){
                    GameBlocks.add(new GameBlock(temp.Block, temp.YPos + i, temp.XPos + j));
                }
            }
        }
    }
//AddToGame------------------------------------------------------------------------------------------------------------------------
    public void newPiece(String BlockType){
    	int a = (int)(Math.random() * 4);
        GamePiece temp = new GamePiece(GridX/2, 1, BlockType, a);
        GameObjects.add(temp);
    }

    public void addGamePieces(){
        for(Iterator<GamePiece> i = GameObjects.iterator(); i.hasNext();){
            addGamePieceToGame(i.next());
        }
    }

    public void addGamePieceToGame(GamePiece temp){
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

    public void addGameBlocks(){
        for(Iterator<GameBlock> i = GameBlocks.iterator(); i.hasNext();){
            GameBlock temp = i.next();
            Game[temp.X][temp.Y] = new GridPiece("Game", temp.name);
            Game[temp.X][temp.Y].state = true;
        }
    }
    public void turnGamePiece(GamePiece shape,String change_direction){
        switch(change_direction){
            case "w":
                shape.up();
                break;
            case "s":
                shape.down();
                break;
            case "a":
                shape.XPos--;
                break;
            case "d":
                shape.XPos++;
                break;
            default:
                break;
        }
    	shape.Pos = GamePiece.ALL[shape.type -1][shape.Turn];
    }
    	
    public void moveGamePiece(GamePiece shape,String change_direction){ 
    	if (change_direction.equals("a")) {
    		shape.XPos--;
    	}
    	if(change_direction.equals("d")) {
    		shape.XPos++;
    	}
        
    }
    
//Checkers------------------------------------------------------------------------------------------------------------------------
    /*
    public void checkClearLine(){
        boolean temp = true;
        for(int i = 1; i < GridY-1; i++){
            temp = true;
            for(int j = 1; j < GridX-1; j++){
                if(!(Game[i][j].state)) temp = false;
            }
            if(temp) 
        }
    }*/
//Printing------------------------------------------------------------------------------------------------------------------------        
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
        return ans + ANSI_RESET;
    }

 public void makeBlockAppear() {
    	int a = (int)(Math.random() * 7);
    	String [] s = {"O","T","S","J","L","Z","I"};
    	this.update(s[a]);

    }

//Reset,Clear,Delay---------------------------------------------------------------------------------------------------------------    
    public void reset(){
        for(int i = 0; i < Game.length; i++) for(int j = 0; j < Game.length; j++) Game[i][j] = GameReset[i][j];
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

    public boolean stuffInTop() {
    	if (!this.GameObjects.isEmpty()) {
    	GamePiece g = GameObjects.get(0);
    	if ((g.YPos == 1)&& this.Game[g.XPos][g.YPos+4].state==false){
    		
    		return true;
    	}
    	}
    	return false;
    }
    public static void printTit () {

String a0="__________                             "+"\n";
String a1="\\______   \\_______  ______  _  ______  "+"\n";
String a2=" |    |  _/\\_  __ \\/  _ \\ \\/ \\/ /    \\ "+"\n";
String a3=" |    |   \\ |  | \\(  <_> )     /   |  \\"+"\n";
String a4=" |______  / |__|   \\____/ \\/\\_/|___|  /"+"\n";
String a5="        \\/                          \\/ "+"\n";
String a6="  __          __         .__           "+"\n";
String a7="_/  |_  _____/  |________|__| ______   "+"\n";
String a8="\\   __\\/ __ \\   __\\_  __ \\  |/  ___/   "+"\n";
String a9=" |  | \\  ___/|  |  |  | \\/  |\\___ \\    "+"\n";
String b0=" |__|  \\___  >__|  |__|  |__/____  >   "+"\n";
String b1="           \\/                    \\/    "+"\n";
System.out.println(ANSI_YELLOW + a0+a1+a2+a3+a4+a5 + ANSI_BLUE + a6+a7+a8+a9+b0+b1 + ANSI_RED);

	
	}

	public void begin () {
		printTit();
		System.out.println("Would you like to start a game?" + "\n" + ANSI_GREEN + "if yes press y" + ANSI_RESET);
		Scanner s = new Scanner(System.in);
		String userin = "";
		if (s.hasNext()) {
			userin = s.next();
		}
		if (userin.equals("Y") || userin.equals("y") || userin.equals("Yes") || userin.equals("yes")) {
		this.createGame();
}
	}
    public void createGame() {
    	
    	while (!stuffInTop()) {
    		Runnable r = new ScanPrint();
        	Thread thread2 = new Thread(r);
            thread2.setDaemon(true);
            thread2.setPriority(Thread.MAX_PRIORITY);
            thread2.start();
    		delay();
            clear();
            this.makeBlockAppear();
            System.out.println(this.printGame());
            //System.out.println(this.stuffInTop());
            try {
            Thread.sleep(2000);
            }
            catch(InterruptedException ex) {
                thread2.interrupt();
            }
            if (!this.GameObjects.isEmpty()) {          
            this.turnGamePiece(this.GameObjects.get(0),mes);
         //   System.out.println(mes);
            mes = "";
            }
    		
    	}
           
 
    }

//Main----------------------------------------------------------------------------------------------------------------------------
    public static void main (String [] args){
    	
    
        Tetris test = new Tetris(20,20);
        clear();
        test.begin();
        


        
    }

    }



