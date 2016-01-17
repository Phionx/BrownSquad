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
 
            InputChar c = Toolkit.readCharacter();
           
           Toolkit.shutdown();
           InputChar tes = Toolkit.readCharacter();
			if (tes.getCharacter() == 'a') {
   			System.out.println(c.getCharacter());
			}
     	   // char test = c.getCharacter();
           
              
            
            //`System.out.println("testing, if all is well it will be a.... and it is :" + test);

	//Toolkit.shutdown();

                
        }
}

