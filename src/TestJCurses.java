import jcurses.event.*;
import jcurses.system.*;
import jcurses.util.*;
import jcurses.widgets.*;

public class TestJCurses {
	public static class TextPanel extends Panel {
	DefaultLayoutManager d = new DefaultLayoutManager();
			// this.setLayoutManager(new DefaultLayoutManager());
	NewTextComponent t = new NewTextComponent(40, 20);
	CharColor greenOnBlack = new CharColor(CharColor.BLACK,
					CharColor.GREEN);
		TextPanel() {
			DefaultLayoutManager d = new DefaultLayoutManager();
			// this.setLayoutManager(new DefaultLayoutManager());
			NewTextComponent t = new NewTextComponent(40, 20);
			// t.setCursorLocation(5,5);
			// t.setTextComponentColors(new
			// CharColor(CharColor.BLACK,CharColor.GREEN));
			t.setText(" Hello World");
			// t.setCursorLocation(0,0);
			t.setCursorColors(new CharColor(CharColor.BLACK, CharColor.GREEN));
			t.setText(" Whats up world!");
			CharColor yellowOnRed = new CharColor(CharColor.RED,
					CharColor.YELLOW);
			CharColor whiteOnBlack = new CharColor(CharColor.BLACK,
					CharColor.WHITE);
			t.printString("Java", 5, 3, greenOnBlack);
			t.printString("Kangaroo", 2, 8, whiteOnBlack);
			t.printString("George", 9, 2, yellowOnRed);
			d.bindToContainer(this);
			d.addWidget(t, 0, 0, 40, 20, WidgetsConstants.ALIGNMENT_CENTER,
					WidgetsConstants.ALIGNMENT_CENTER);

		}
		public void printToScreen(String s) {
			t.printString(s, 0, 0, greenOnBlack);
		}
	}

	public static Window w;

	public static void main(String args[]) {
		w = new Window(40, 20, true, "Test Window");
		TextPanel hi = new TestJCurses.TextPanel();
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
		
	    char test = 'h';
	while(true) {
            InputChar c = Toolkit.readCharacter();
            if (c.getCharacter() == 'a') {
                hi.printToScreen("sacha");    
            }
		else {
			hi.printToScreen("" + c.getCharacter());
		}
}
	}	
}
