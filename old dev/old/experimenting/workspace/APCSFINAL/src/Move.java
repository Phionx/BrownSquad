import java.util.Scanner;

public class Move {

public static String currentpos = "faceup";


	public static void main(String[] args) {
		String a = "";
		Scanner s = new Scanner (System.in);
		while (s.hasNext()) {
			a =  s.next();
			if (a.equals("w")) {
				System.out.println("XXXX");
			}
			if (a.equals("a")) {
				System.out.println("X");
				System.out.println("X");
				System.out.println("X");
				System.out.println("X");
			}
			if (a.equals("d")) {
				System.out.println("X");
				System.out.println("X");
				System.out.println("X");
				System.out.println("X");
			
			}
			if (a.equals("s")) {
				System.out.println("XXXX");
			}
			
		}

	}

}
