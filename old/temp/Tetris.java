
public class Tetris {
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	static String top = " ---------------- ";
	static String middle = "|                |";
	static String board = top + "\n" + middle + "\n"  + middle + "\n" + middle + "\n"+ middle + "\n"+ middle + "\n"+ middle + "\n"+ top;
/*	
 * 
 * 	
	static String a = ANSI_RED + "▀█▀" + ANSI_RESET;
	static String b = ANSI_BLUE + "▄▄▄▄"  + ANSI_RESET;
	static String c = ANSI_GREEN + "██" + ANSI_RESET;
	static String d = ANSI_RED + "▄█▄" + ANSI_RESET;
	static String e = ANSI_RED + "▄ " + "\n" + ANSI_RED + "█▀" + ANSI_RESET;
	static String f = "▄▄" + "\n" + "█";
*/
	//static String [] shapes = {a,b,c,d,e,f};
	
	
	public static String makeshape (int [] [] s) {
		
		String rs = "";
		for (int [] i: s) {
			for (int j: i) {
				if (j == 1)  {
					rs+="O";
				}
				
				else {
					rs+=" ";
				}
			}
			rs+="\n";
		}
		return rs;
	}
	public static String makeboard (int n) {
		String rs = top+"\n";
		for (int i = 0; i < n; i++) {
			rs+=middle+"\n";
		}
		rs = rs + top;
		board = rs;
		return rs;
	}
	 
		public static void main(String[] args) {
			
			int [] [] hi = {{0,1,0},{1,1,1}};
			System.out.println(makeshape(hi));
			/*
			makeboard(20);
		for (String a: shapes) {
			System.out.println(a);
		}
		System.out.println(board);
		*/
	}

}

