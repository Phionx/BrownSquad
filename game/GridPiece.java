public class GridPiece {
    public String color; // color of piece
    public String cellType; //type of cell
    public String design;
    public boolean state;
    
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
        if(block.equals("O")) { color = ANSI_YELLOW;}
        else if(block.equals("J")) { color = ANSI_BLUE;}
        else if(block.equals("L")) { color = ANSI_WHITE;}
        else if(block.equals("I")) { color = ANSI_CYAN;}
        else if(block.equals("T")) { color = ANSI_PURPLE;}
        else if(block.equals("Z")) { color = ANSI_RED;}
        else if(block.equals("S")) { color = ANSI_GREEN;}
    }
    public GridPiece(String type){
        cellType = type;
        color = ANSI_WHITE; 
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
