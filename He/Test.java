import jcurses.system.InputChar;
import jcurses.system.Toolkit;


public class Test {    
        public static void main(String[] args) throws Exception {
            Toolkit.init();
            char test = 'h';
            InputChar c = Toolkit.readCharacter();
            
            
            
            if (c.getCharacter() == 'a') {
                
                test = 'a';
                Toolkit.shutdown();
                
            }

            else {
                    System.out.println(c.getCharacter());
            }
            
            System.out.println("testing, if all is well it will be a.... and it is :" + test);

                
        }
}

