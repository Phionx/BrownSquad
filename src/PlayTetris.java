import java.util.Scanner;
public class PlayTetris {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
   
    public static void printTitle() {

        String a0="\n\n\n\t\t__________                             "+"\n";
        String a1="\t\t\\______   \\_______  ______  _  ______  "+"\n";
        String a2="\t\t |    |  _/\\_  __ \\/  _ \\ \\/ \\/ /    \\ "+"\n";
        String a3="\t\t |    |   \\ |  | \\(  <_> )     /   |  \\"+"\n";
        String a4="\t\t |______  / |__|   \\____/ \\/\\_/|___|  /"+"\n";
        String a5="\t\t        \\/                          \\/ "+"\n";
        String a6="\t\t  __          __         .__           "+"\n";
        String a7="\t\t_/  |_  _____/  |________|__| ______   "+"\n";
        String a8="\t\t\\   __\\/ __ \\   __\\_  __ \\  |/  ___/   "+"\n";
        String a9="\t\t |  | \\  ___/|  |  |  | \\/  |\\___ \\    "+"\n";
        String b0="\t\t |__|  \\___  >__|  |__|  |__/____  >   "+"\n";
        String b1="\t\t           \\/                    \\/    "+"\n";

        String title = ANSI_YELLOW + a0+a1+a2+a3+a4+a5 + ANSI_BLUE + a6+a7+a8+a9+b0+b1 + ANSI_RED;
        System.out.println(title);
	}

	public static boolean begin () {
	final String clear = "\u001b[2J";
        final String home = "\u001b[H";
        System.out.print(clear + home);
        System.out.flush();
		printTitle();
		System.out.println("Would you like to start a game?" + "\n" + ANSI_GREEN + "(If yes please enter y):" + ANSI_RESET);
		Scanner s = new Scanner(System.in);
		String userin = "";
		if (s.hasNext()) {
			userin = s.next();
		}
		if (userin.equals("Y") || userin.equals("y") || userin.equals("Yes") || userin.equals("yes")) {
		return true;	
				}
        return false;
	}

	public static void main (String [] args) {
		
			if (begin() == true) {
			Tetris.startGame();
		}

		}



}
