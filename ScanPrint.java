import java.util.Scanner;

public class ScanPrint implements Runnable{
public String message;
ScanPrint() {
	message = "";
}
	public void run() {
		Scanner s = new Scanner(System.in);
         while (s.hasNext()) {
         	Tetris.mes= s.next();
         }

	}
	
	public String getMes () {
		return message;
	}
}
