import jcurses.event.*;
import jcurses.system.*;
import jcurses.util.*;
import jcurses.widgets.*;
import java.util.*;

public class Tetris{
    //Jcurses helper class
    public static class TextPanel extends Panel {
	DefaultLayoutManager d = new DefaultLayoutManager();
			// this.setLayoutManager(new DefaultLayoutManager());
	NewTextComponent t = new NewTextComponent(40, 20);
		TextPanel() {
			DefaultLayoutManager d = new DefaultLayoutManager();
			// this.setLayoutManager(new DefaultLayoutManager());
			NewTextComponent t = new NewTextComponent(40, 20);
			d.bindToContainer(this);
			d.addWidget(t, 0, 0, 40, 20, WidgetsConstants.ALIGNMENT_CENTER,
					WidgetsConstants.ALIGNMENT_CENTER);

		}
		public void printToScreen(String s,int x, int y, CharColor c) {
			t.printString(s, x, y, c);
		}
	}


    
//Instance Variables-----------------------------------------------------------------------------------------------------------------------

    //holds game in jcurses
    public static TextPanel hi = new Tetris.TextPanel();
    public static Window w;

    //holds time delay
    public static final int time = 500;
    
    //keeps game running
    public boolean gamerun = true;

    //holds Grid Size
    public int GridX, GridY;

    //holds position message
	public static String mes = "";

    //holds game score
    public int score = 0;

    //holds colors
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

    //Holds falling GamePieces such as "O" or "T" 
    public ArrayList<GamePiece> GameObjects = new ArrayList<GamePiece>();
    
    //hodls individual stationary blocks
    public ArrayList<GameBlock> GameBlocks = new ArrayList<GameBlock>();
    
    //holds all GridPieces in the Tetris Game Map
    public GridPiece [][] Game;

    //holds empty original Tetris Game Map -- used to reset field
    public GridPiece [][] GameReset;

//Initialization ------------------------------------------------------------------------------------------------------------------------
    //constructer that sets the size for the Tetris Field
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
    
    //intialize Game field
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
    
    //updater function
    public void update(){
        int a = (int)(Math.random() * 7);
    	String [] s = {"O","T","S","J","L","Z","I"};

        reset();
        if(GameObjects.isEmpty()){
            newPiece(s[a]);
        }
        fallGamePiece(GameObjects.get(0));
    }
    
    //Helps a GamePiece fall 
    //clears field
    //adds GameBlocks
    //clears any full lines
    //turns the GamePiece only if it doesn't do anything illegal
    //makes the GamePiece fall only if it doesn't do anything illegal
    //adds teh GamePiece officially to the Game
    public void fallGamePiece(GamePiece temp){
        GridPiece [][] tempState = Game;
        reset();
        int lowBlock = 0;
        addGameBlocks();
        checkClearLine();
        turnGamePiece(temp, mes);
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

    //if for some reason multiple game pieces are falling down then this
    //function will help 
    public void fallGamePieces(){
        for(Iterator<GamePiece> i = GameObjects.iterator(); i.hasNext();){
            GamePiece temp = i.next();
            fallGamePiece(temp);
        }
    }
    
    //this brings a line of game blocks down
    public void fallGameBlockLine(int height){
        for(ListIterator<GameBlock> i = GameBlocks.listIterator(); i.hasNext();){
            GameBlock temp = i.next();
            if(temp.Y == height){
                temp.Y = temp.Y + 1;
                i.set(temp);
            }
        }
    }
    
    //this clears a line of game blocks and brings the game blocks above it down
    public void clearLine(int height){
        score += 10;
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
    
    //changes the Postion or orientation of game piece according to user input
    public void turnGamePiece(GamePiece shape,String change_direction){
        switch(change_direction){
            case "w":
                turnUp(shape);
                break;
            case "s":
                turnDown(shape);
                break;
            case "a":
                moveLeft(shape);
                break;
            case "d":
                moveRight(shape);
                break;
            case "e":
                gamerun = false;
                break;
            default:
                break;
        }
        mes = "";
    }

    public void turnUp(GamePiece shape){
        GamePiece temp = new GamePiece(shape);
        shape.up();
        for(int i = 0; i < temp.Size; i++){
            for(int j = 0; j < temp.Size; j++){
                if(Game[shape.YPos + i][shape.XPos + j].state && shape.Pos[i][j]) {
                    shape = new GamePiece(temp);
                    break;
                }
            }
        }
    }

    public void turnDown(GamePiece shape){
        GamePiece temp = new GamePiece(shape);
        shape.down();
        for(int i = 0; i < temp.Size; i++){
            for(int j = 0; j < temp.Size; j++){
                if(Game[shape.YPos + i][shape.XPos + j].state && shape.Pos[i][j]){
                    shape = new GamePiece(temp);
                    break;
                }
            }
        }
    }

    public void moveRight(GamePiece shape){
        GamePiece temp = new GamePiece(shape);
        shape.XPos++;
        for(int i = 0; i < temp.Size; i++){
            for(int j = 0; j < temp.Size; j++){
                if(Game[shape.YPos + i][shape.XPos + j].state && shape.Pos[i][j]){
                    shape = new GamePiece(temp);
                    break;
                }
            }
        }
    }

    public void moveLeft(GamePiece shape){
        GamePiece temp = new GamePiece(shape);
        shape.XPos--;
        for(int i = 0; i < temp.Size; i++){
            for(int j = 0; j < temp.Size; j++){
                if(Game[shape.YPos + i][shape.XPos + j].state && shape.Pos[i][j]) {
                    shape = new GamePiece(temp);
                    break;
                }
            }
        }
    }

    
//Transition------------------------------------------------------------------------------------------------------------------------
    //Turns a GamePiece that has hit the floor or a stationary block to stop 
    //and turns its individual blocks into GameBlock and adds them to GameBlocks
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
    //adds a random new GamePiece to the game
    public void newPiece(String BlockType){
    	int a = (int)(Math.random() * 4);
        GamePiece temp = new GamePiece(GridX/2, 1, BlockType, a);
        GameObjects.add(temp);
    }

    //
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
    	
    
//Checkers------------------------------------------------------------------------------------------------------------------------
    //remember to check for lines to clear after adding the GameBlocks to the
    //game and before adding the GamePiece
    //checks for a need to clear a line of GameBlocks and clears if needed
    public void checkClearLine(){
        boolean temp = true;
        for(int i = 1; i < GridY-1; i++){
            temp = true;
            for(int j = 1; j < GridX-1; j++){
                if(!(Game[i][j].state)) {
                    temp = false;
                }
            }
            if(temp){
                clearLine(i);
            }
        }
        addGameBlocks();
    }

    //work on this to end the Game
    public boolean stuffInTop() {
    	if (!this.GameObjects.isEmpty()) {
    	GamePiece g = GameObjects.get(0);
    	if ((g.YPos == 1)&& this.Game[g.XPos][g.YPos+4].state==false){
    		
    		return true;
    	}
    	}
    	return false;
    }

//Printing------------------------------------------------------------------------------------------------------------------------        
    //prints the entire Tetris Game field
    //work on asthetic
    public String printGame(){
        String ans = "\n\n\t\tScore: " + score + "\n\t\t";
        for(GridPiece[] i: Game){
            for(GridPiece j: i) {
                if(j.state){
                    ans += j.color + j.design;
                }else {
                    ans += " ";
                }
            }
            ans += "\n\t\t";
        }
        return ans + ANSI_RESET;
    }


//Reset,Clear,Delay---------------------------------------------------------------------------------------------------------------    
    //resets game field
    public void reset(){
        for(int i = 0; i < Game.length; i++) for(int j = 0; j < Game.length; j++) Game[i][j] = GameReset[i][j];
    }

    //clears gamescreen
    private static void clear(){
        final String clear = "\u001b[2J";
        final String home = "\u001b[H";
        System.out.print(clear + home);
        System.out.flush();
    }
    
    //delays prints
    private static void delay(){
        try {
            Thread.sleep(time);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
//Intializing Game-------------------------------------------------------------------------------------------------------------------
    //begins actual Game
    public void startGame(){
        clear();
        this.runGame();
    }
    //runs actual Game
    public void runGame() {
        //setup Jcurses listener
        w = new Window(40, 20, true, "Test Window");
		w.setRootPanel(hi);
		w.addListener(new WindowListener() {
			public void windowChanged(WindowEvent we) {
				if (we.getType() == WindowEvent.CLOSING) {
					w.hide();
					w.close();
					System.exit(0);
				}
			}
		});

        //run Game thread
    	while (gamerun) {
    		Runnable r = new ScanPrint();
        	Thread thread2 = new Thread(r);
            thread2.setDaemon(true);
            thread2.setPriority(Thread.MAX_PRIORITY);
            thread2.start();
    	    delay();
            this.printGame();
            this.update();
        }
        delay();
        printEnd();
    	Toolkit.shutdown();
    }
           

//Game End------------------------------------------------------------------------------------------------------------------------
    public void printEnd(){
        clear();
        System.out.println("THANK YOU FOR PLAYING BROWN TETRIS :D!\nPlease play again!\nHere was your final score: " + score);
    }
//Main----------------------------------------------------------------------------------------------------------------------------
    public static void main (String [] args){ 
    }
}



