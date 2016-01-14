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
