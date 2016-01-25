import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import jcurses.system.InputChar;
import jcurses.system.Toolkit;
import jcurses.event.WindowEvent;
import jcurses.event.WindowListener;
import jcurses.system.CharColor;
import jcurses.widgets.DefaultLayoutManager;
import jcurses.widgets.Panel;
import jcurses.widgets.WidgetsConstants;
import jcurses.widgets.Window;
public class ScanPrint implements Runnable{
public String message;
ScanPrint() {
	message = "";
}
	public void run() {
		while(true) {
            InputChar c = Toolkit.readCharacter();
            String a = "" + c.getCharacter();
            Tetris.test.turnGamePiece(Tetris.test.GameObjects.get(0),a);
		}

	}
	


}

