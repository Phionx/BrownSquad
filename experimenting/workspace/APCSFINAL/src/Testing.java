


import jcurses.system.CharColor;
import jcurses.system.InputChar;
import jcurses.system.Toolkit;
import jcurses.util.Rectangle;

/**
 * Simple example in JCurses
 *
 * @package hell.cenobites.code.java.jcurses.examples.one
 * @author Nycholas de Oliveira e Oliveira <nycholas@gmail.com>
 * @version 1.0
 */
public class Testing {    
        public static void main(String[] args) throws Exception {
        	short s = 0;
        	short d = 7;
        /////////////////////////////////////////////////////////////////////	
        
        	CharColor col = new CharColor(s,d);
        	Rectangle rect = new Rectangle (0,0,30,30);
        	Toolkit.drawRectangle(rect, col);
        	Toolkit.printString("hi",rect,col);
            Toolkit.init();
            char test = 'h';
        	InputChar c = Toolkit.readCharacter();
        	if (c.getCharacter() == 'a') {
        		
        		test = 'a';
        		Toolkit.shutdown();
        	}
        	
        	System.out.println(c.getCharacter());
            
        
           
        }
}
