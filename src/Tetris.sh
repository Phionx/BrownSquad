#!/bin/bash
rm *.class
javac -cp "../src/jcurses/lib/jcurses.jar:./" PlayTetris.java -Xlint:none
java -cp "../src/jcurses/lib/jcurses.jar:./" PlayTetris
