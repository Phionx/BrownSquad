import jcurses.system.CharColor;
import jcurses.widgets.DefaultLayoutManager;
import jcurses.widgets.Label;
import jcurses.widgets.WidgetsConstants;
import jcurses.widgets.Window;

/**
 * Simple example in JCurses
 * 
 * @package org.cenobites.code.java.jcurses.examples.one
 * @author Nycholas de Oliveira e Oliveira <nycholas@gmail.com>
 * @version 1.0
 */
public class Hello {
        
        public static void main(String[] args) throws Exception {
                System.out.println("+++++ " + System.getProperty("java.library.path"));
                
                
                Window w = new Window(100, 100, true, "Hai hai!");
                Label label = new Label("Hai hai!", new CharColor(CharColor.BLACK,
                                CharColor.GREEN));
                DefaultLayoutManager mgr = new DefaultLayoutManager();
                mgr.bindToContainer(w.getRootPanel());
                mgr.addWidget(label, 0, 0, 100, 100, WidgetsConstants.ALIGNMENT_CENTER,
                                WidgetsConstants.ALIGNMENT_CENTER);
                w.show();
                Thread.currentThread();
                Thread.sleep(3000);
                w.close();
        }
}
