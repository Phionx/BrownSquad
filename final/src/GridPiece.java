import jcurses.system.CharColor;

public class GridPiece {
    public CharColor color; // color of piece
    public String cellType; //type of cell
    public String design;
    public boolean state;
    public String name;
    CharColor greenOnBlack = new CharColor(CharColor.BLACK,CharColor.GREEN);
    CharColor yellowOnBlack = new CharColor(CharColor.BLACK,CharColor.YELLOW);
    CharColor redOnBlack = new CharColor(CharColor.BLACK,CharColor.RED);
    CharColor whiteOnBlack = new CharColor(CharColor.BLACK,CharColor.WHITE);
    CharColor blueOnBlack = new CharColor(CharColor.BLACK,CharColor.BLUE);
    CharColor magentaOnBlack = new CharColor(CharColor.BLACK,CharColor.MAGENTA);
    CharColor cyanOnBlack = new CharColor(CharColor.BLACK,CharColor.CYAN);
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
    public GridPiece(String type, String block){
        this(type);
        name = block;
        if(block.equals("O")) { color = yellowOnBlack;}
        else if(block.equals("J")) { color = blueOnBlack;}
        else if(block.equals("L")) { color = whiteOnBlack;}
        else if(block.equals("I")) { color = cyanOnBlack;}
        else if(block.equals("T")) { color = magentaOnBlack;}
        else if(block.equals("Z")) { color = redOnBlack;}
        else if(block.equals("S")) { color = greenOnBlack;}
    }
    public GridPiece(String type){
        cellType = type;
        color = whiteOnBlack; 
        if(cellType.equals("Game")){
            design = "X";
            state = false;
        } else if(cellType.equals("Bottom")) {
            design = "=";
            state = true;
        } else if(cellType.equals("Side")){
            design = "|";
            state = true;
        } else if(cellType.equals("Top")){
            design = "_";
            state = true;
        }
    }

}

