import jcurses.system.InputChar;
import jcurses.system.Toolkit;	
import jcurses.util.Rectangle;
import jcurses.system.CharColor;




/**
 * Simple example in JCurses
 *
 * @package hell.cenobites.code.java.jcurses.examples.one
 * @author Nycholas de Oliveira e Oliveira <nycholas@gmail.com>
 * @version 1.0
 */
public class Test {    
        public static void main(String[] args) throws Exception {
           short sh1 = 0;
        short sh2 = 1;
        TextScreen t = new TextScreen(50,50);
        t.drawScreen();
        CharColor c = new CharColor(sh1,sh2);
        t.printString("hi", 0, 0, c);
                
        }
}

