import jcurses.event.*;
import jcurses.system.*;
import jcurses.util.*;
import jcurses.widgets.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public class Tetris{
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
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
//Instance Variables
	public static TextPanel hi = new Tetris.TextPanel();
        public static final int time = 500;
        public int GridX, GridY;
	public static String mes = "";
	public static boolean gamerun = true;

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
            fallGamePiece(i.next());
        }
    }
    
    
      public void turnGamePiece(GamePiece shape,String change_direction){
    	
    	if (change_direction.equals("w")) {
    		shape.turnPieceUp();
  
    		}
    	if (change_direction.equals("s")) {
    		
  		shape.turnPieceDown();
    		}

	if (change_direction.equals("a")) {
    		shape.XPos--;
    	}
    	if(change_direction.equals("d")) {
    		shape.XPos++;
    	}
/*------------------ARROW KEYS----------------------*/
    	if (change_direction.equals("" + InputChar.KEY_UP)) {
    		shape.turnPieceUp();
  
    		}
    	if (change_direction.equals("" + InputChar.KEY_DOWN)) {
    		
  		shape.turnPieceDown();
    		}

	if (change_direction.equals("" + InputChar.KEY_LEFT)) {
    		shape.XPos--;
    	}
    	if(change_direction.equals("" + InputChar.KEY_RIGHT)) {
    		shape.XPos++;
    	}
if (change_direction.equals("e")) {
gamerun = false;
  
    		}
    	
    	shape.Pos = shape.ALL[shape.type -1][shape.Turn];
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
    
    public void printGame(){
        for (int y = 0; y < Game.length;y++) {
	for (int x = 0; x < Game[y].length;x++) {
		if(Game[y][x].state) {
	hi.printToScreen(Game[y][x].design,x,y,Game[y][x].color);
			}
	else {
                   hi.printToScreen(" ",x,y,Game[y][x].color);
                }
}
}
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


    public void createGame() {
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
    	while (gamerun) {

    		Runnable r = new ScanPrint();
        	Thread thread2 = new Thread(r);
            thread2.setDaemon(true);
            thread2.setPriority(Thread.MAX_PRIORITY);
            thread2.start();

    	    delay();
            this.makeBlockAppear();
            this.printGame();
            }
    		Toolkit.shutdown();
    		
    	}
           
 
    

public static Window w;
public static Tetris test = new Tetris(20,20);
//Main----------------------------------------------------------------------------------------------------------------------------
    public static void startGame (){
    	
    
		
        	clear();
                test.createGame();


        
    }

    }



