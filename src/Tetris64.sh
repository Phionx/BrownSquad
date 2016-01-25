#!/bin/bash
rm *.class
javac -cp "../src/jcurses64/lib/jcurses.jar:./" PlayTetris.java -Xlint:none
java -cp "../src/jcurses64/lib/jcurses.jar:./" PlayTetris
