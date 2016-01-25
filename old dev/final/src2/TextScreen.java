import jcurses.event.ValueChangedListener;
import jcurses.event.WindowEvent;
import jcurses.event.WindowListener;
import jcurses.system.CharColor;
import jcurses.widgets.DefaultLayoutManager;
import jcurses.widgets.Panel;
import jcurses.widgets.WidgetsConstants;
import jcurses.widgets.Window;

/**
 * Makes a NewTextComponent and installs it. Performs setup for various column
 * and row layouts for reports. Has some methods for printing to given locations
 * with given colors. Crops strings to be printed. Aligns text to be printed.
 * 
 * @author Larry Gray(caverdude)
 * 
 */
public class TextScreen extends Panel {
	/** A Custom Text Component */
	private NewTextComponent screen;
	/** A window */
	private Window w;

	/**
	 * Adds a keyboard input listener which handles key scan code events. (for
	 * arrows and escape key)
	 * 
	 * @param inputListener
	 */
	public void addInputListener(InputListener inputListener) {
		screen.setInputListener(inputListener);

	} // addInputListener

	/**
	 * Constructs a TextScreen with given dimensions.
	 * 
	 * @param x
	 * @param y
	 */
	public TextScreen(int x, int y) {
		w = new Window(x + 3, y + 4, true, "");
		w.setRootPanel(this);
		// InputChar closeChar = new InputChar('x');
		// w.setClosingChar(closeChar);
		w.addListener(new WindowListener() {
			public void windowChanged(WindowEvent we) {
				if (we.getType() == WindowEvent.CLOSING) {
					w.close();
					System.exit(0);
				}// if
			} // windoChanged
		}); // addListener
		DefaultLayoutManager d = new DefaultLayoutManager();
		// this.setLayoutManager(new DefaultLayoutManager());
		screen = new NewTextComponent(x, y);

		d.bindToContainer(this);
		d.addWidget(screen, 0, 0, x, y, WidgetsConstants.ALIGNMENT_TOP,
				WidgetsConstants.ALIGNMENT_LEFT);
		screenCols = x;
		screenRows = y;
		screen.focus();
		// w.setVisible(true);
		w.show();

	} // constructor

	/** Number of columns in this text screen. */
	private int screenCols = 0;
	/** Number of rows in this text screen. */
	private int screenRows = 0;

	/**
	 * Closes the text screen interface, returns to command line.
	 */
	public void close() {
		w.hide();
		w.close();
		System.exit(0);
	}

	/**
	 * Prints a string on the screen starting at the given x,y coordinate and
	 * using given bg/fg colors.
	 * 
	 * @param s
	 * @param x
	 * @param y
	 * @param c
	 */
	public void printString(String s, int x, int y, CharColor c) {
		screen.printString(s, x, y, c);

	} // printString

	/**
	 * Draws a character at the given coordinate using the given bg/fg colors.
	 * 
	 * @param x
	 * @param y
	 * @param c
	 * @param ch
	 */
	public void drawChar(int x, int y, CharColor c, char ch) {
		screen.drawChar(x, y, c, ch);
	} // drawChar

	/** Aligns Text Left */
	private static int ALIGN_LEFT = 0;
	/** Aligns text right. */
	private static int ALIGN_RIGHT = 1;
	/** Aligns text centered. */
	private static int ALIGN_CENTER = 2;

	/**
	 * Gets a given number of space characters in a string form.
	 * 
	 * @param space
	 *            number of spaces.
	 * @return String of spaces.
	 */
	private String getSpace(int space) {
		String spaceString = "";
		for (int i = 0; i < space; i++)
			spaceString += " ";
		return spaceString;
	} // getSpace

	/**
	 * Adds space to end of a string. Used for alignment.
	 * 
	 * @param s
	 * @param space
	 * @return String with spaces added to end.
	 */
	private String addSpaceEnd(String s, int space) {
		String newString = s + getSpace(space);
		return newString;
	} // addSpaceEnd

	/**
	 * Adds space to the front of a string. Used for alignment.
	 * 
	 * @param s
	 * @param space
	 * @return String with spaces added to front.
	 */
	private String addSpaceFront(String s, int space) {
		String newString = getSpace(space) + s;
		return newString;
	} // addSpaceFront

	/**
	 * Draws a table cell or field using alignment.
	 * 
	 * @param s
	 * @param c
	 * @param r
	 */
	public void drawCell(String s, int c, int r) {
		// get column width
		int width = widths[c];
		// rebuild string with correct alignment\
		int alignment = alignments[c];
		int sWidth = s.length();
		String aString = "";
		if (alignment == ALIGN_LEFT) {
			if (width > sWidth)
				aString = addSpaceEnd(s, width - sWidth);
			else if (width < sWidth)
				aString = s.substring(0, width);
			else
				aString = s;
		} else if (alignment == ALIGN_RIGHT) {
			if (width > sWidth)
				aString = addSpaceFront(s, width - sWidth);
			else if (width < sWidth)
				aString = s.substring(width - sWidth, sWidth);
			else
				aString = s;
		} else if (alignment == ALIGN_CENTER) {
			int half = (int) ((width - sWidth) / 2);
			if (width > sWidth) {
				aString = addSpaceFront(s, half);
				aString = addSpaceEnd(s, half);
			} // if
			else if (width < sWidth)
				aString = s.substring(half, sWidth - half);
			else
				aString = s;
		} // if

		// get x and y from column number and row number
		int x = positions[c];
		int y = r;
		// get row colors for this row
		int rowColor = rowsColors[r];
		CharColor color = colors[rowColor];
		// call printString(s,x,y,c);
		printString(aString, x, y, color);
	} // drawCell

	/**
	 * Draws a CharCell on the screen at given coordinates. It gets this from
	 * the TextScreenModel.
	 * 
	 * @param charCell
	 * @param x
	 * @param y
	 */
	private void drawChar(TextScreenModel.CharCell charCell, int x, int y) {
		screen.drawChar(x, y, charCell.getColor(), charCell.getChar());
	}

	/**
	 * A Model representing the cellular data for this TextScreen.
	 */
	private TextScreenModel tsModel;

	/**
	 * Draws or refreshes this TextScreen from the TextScreenModel data.
	 * 
	 */
	public void drawScreen() {
		TextScreenModel.CharCell aCharCell = null;
		for (int x = 0; x < screenCols; x++) {
			for (int y = 0; y < screenRows; y++) {
				aCharCell = tsModel.getCharCell(x, y);
				drawChar(x, y, aCharCell.getColor(), aCharCell.getChar());
			} // for
		} // for
	} // drawScreen()

	/**
	 * Moves view up a number of rows.
	 * 
	 * @param rows
	 */
	public void up(int rows) {
		tsModel.decViewY(rows);
	} // up(rows)

	/**
	 * Moves view down a number of rows.
	 * 
	 * @param rows
	 */
	public void down(int rows) {
		tsModel.incViewY(rows);
	} // down(rows)

	/**
	 * Moves view left a number of cellular columns.
	 * 
	 * @param columns
	 */
	public void left(int columns) {
		
		tsModel.decViewX(columns);
	} // left(columns)

	/**
	 * Moves view right a number of cellular columns.
	 * 
	 * @param columns
	 */
	public void right(int columns) {
		tsModel.incViewX(columns);
	} // right(columns)

	/**
	 * Sets the TextScreenModel for this screen.
	 * 
	 * @param tsm
	 */
	public void setTextScreenModel(TextScreenModel tsm) {
		this.tsModel = tsm;
	} // setTextScreenModel(TextScreenModel)

	/** data column widths */
	int[] widths;
	/** data column starting positions */
	int[] positions;

	/*
	 * 
	 * @param widths
	 * 
	 * @param positions
	 * 
	 * public void setColumnWidths(int[] widths, int[] positions) { this.widths
	 * = widths; this.positions = positions; } // setColumnWidths
	 */

	/** data column alignments */
	int[] alignments;

	/*
	 * 
	 * @param alignments
	 * 
	 * public void setColumnAlignments(int[] alignments) { this.alignments =
	 * alignments; } // setColumnAlignments
	 */

	/** */
	CharColor[] colors;

	/** Different colors for alternating rows or sets of rows */
	int[] rowsColors;

	/*
	 * 
	 * @param colors
	 * 
	 * @param rowsColors
	 * 
	 * public void setRowColors(CharColor[] colors, int[] rowsColors) {
	 * this.colors = colors; this.rowsColors = rowsColors; } // setRowColors
	 */
	/*
	 * 
	 * 
	 * public void setupBalanceScreen() {
	 * 
	 * int[] alignments = { TextScreen.ALIGN_LEFT, TextScreen.ALIGN_LEFT,
	 * TextScreen.ALIGN_LEFT, TextScreen.ALIGN_LEFT };
	 * 
	 * this.setColumnAlignments(alignments); int[] widths = { 20, 3, 11, 11 };
	 * int[] positions = { 0, 21, 33, 45 }; this.setColumnWidths(widths,
	 * positions); CharColor[] colors = { new CharColor(CharColor.BLUE,
	 * CharColor.CYAN), new CharColor(CharColor.CYAN, CharColor.BLUE), new
	 * CharColor(CharColor.BLACK, CharColor.GREEN), new
	 * CharColor(CharColor.GREEN, CharColor.BLACK) }; int[] rowColors = { 0, 1,
	 * 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1,
	 * 0, 1 };
	 * 
	 * this.setRowColors(colors, rowColors); } // setupBalanceScreen
	 */
	/*
	 * 
	 * 
	 * public void setupMonthsBalanceScreen() {
	 * 
	 * int[] alignments = { TextScreen.ALIGN_LEFT, TextScreen.ALIGN_LEFT,
	 * TextScreen.ALIGN_LEFT, TextScreen.ALIGN_LEFT };
	 * 
	 * this.setColumnAlignments(alignments); int[] widths = { 20, 3, 11, 11, 11,
	 * 11, 11, 11 }; int[] positions = { 0, 21,25, 37, 49, 61,73,85 };
	 * this.setColumnWidths(widths, positions); CharColor[] colors = { new
	 * CharColor(CharColor.BLUE, CharColor.CYAN), new CharColor(CharColor.CYAN,
	 * CharColor.BLUE), new CharColor(CharColor.BLACK, CharColor.GREEN), new
	 * CharColor(CharColor.GREEN, CharColor.BLACK) }; int[] rowColors = { 0, 1,
	 * 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1,
	 * 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1,
	 * 0, 1, 0, 1, 0, 1 };
	 * 
	 * this.setRowColors(colors, rowColors); } // setupBalanceScreen
	 */

	/*
	 * 
	 * 
	 * public void setupLedgerAccountsScreen() {
	 * 
	 * int[] alignments = { TextScreen.ALIGN_LEFT, TextScreen.ALIGN_LEFT,
	 * TextScreen.ALIGN_LEFT, TextScreen.ALIGN_LEFT, TextScreen.ALIGN_LEFT,
	 * TextScreen.ALIGN_LEFT };
	 * 
	 * this.setColumnAlignments(alignments); int[] widths = { 11, 20, 7, 7, 7, 7
	 * }; int[] positions = { 0, 12, 33, 41, 49, 57 };
	 * this.setColumnWidths(widths, positions); CharColor[] colors = { new
	 * CharColor(CharColor.BLUE, CharColor.CYAN), new CharColor(CharColor.CYAN,
	 * CharColor.BLUE), new CharColor(CharColor.BLACK, CharColor.GREEN), new
	 * CharColor(CharColor.GREEN, CharColor.BLACK) }; int[] rowColors = { 0, 1,
	 * 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1,
	 * 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1,
	 * 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1,
	 * 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1,
	 * 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 };
	 * 
	 * this.setRowColors(colors, rowColors); } // setLedgerAccountsScreen
	 */
	/*
	 * 
	 * 
	 * public void setupAccountsScreen() {
	 * 
	 * int[] alignments = { TextScreen.ALIGN_LEFT, TextScreen.ALIGN_LEFT,
	 * TextScreen.ALIGN_LEFT };
	 * 
	 * this.setColumnAlignments(alignments); int[] widths = { 10, 20, 3 }; int[]
	 * positions = { 0, 11, 32 }; this.setColumnWidths(widths, positions);
	 * CharColor[] colors = { new CharColor(CharColor.BLUE, CharColor.CYAN), new
	 * CharColor(CharColor.CYAN, CharColor.BLUE), new CharColor(CharColor.BLACK,
	 * CharColor.GREEN), new CharColor(CharColor.GREEN, CharColor.BLACK) };
	 * int[] rowColors = { 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1,
	 * 0, 1, 0, 1, 0, 1, 0, 1, 0, 1 };
	 * 
	 * this.setRowColors(colors, rowColors); } // setAccountsScreeen
	 */
	/*
	 * 
	 * 
	 * public void setupJournalScreen() {
	 * 
	 * int[] alignments = { TextScreen.ALIGN_LEFT, TextScreen.ALIGN_LEFT,
	 * TextScreen.ALIGN_LEFT, TextScreen.ALIGN_LEFT, TextScreen.ALIGN_LEFT };
	 * this.setColumnAlignments(alignments); int[] widths = { 11, 20, 3, 7, 7 };
	 * int[] positions = { 0, 12, 33, 37, 45, 53 }; this.setColumnWidths(widths,
	 * positions); CharColor[] colors = { new CharColor(CharColor.BLUE,
	 * CharColor.CYAN), new CharColor(CharColor.CYAN, CharColor.BLUE), new
	 * CharColor(CharColor.BLACK, CharColor.GREEN), new
	 * CharColor(CharColor.GREEN, CharColor.BLACK) }; int[] rowColors = { 0, 1,
	 * 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1,
	 * 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1,
	 * 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1,
	 * 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1,
	 * 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1,
	 * 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1,
	 * 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1,
	 * 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1,
	 * 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1,
	 * 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1,
	 * 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1,
	 * 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1,
	 * 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1,
	 * 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1,
	 * 2, 3};
	 * 
	 * this.setRowColors(colors, rowColors); } // setJournalScreen
	 */
	/*
	 * 
	 * @param args
	 * 
	 * public static void main(String args[]) { TextScreen ts = new
	 * TextScreen(60, 20); ts.setupJournalScreen(); ts.drawCell("testing", 0,
	 * 3); ts.drawCell("testing", 3, 2); ts.drawCell("testing", 2, 5);
	 * ts.drawCell("testing", 4, 1);
	 * 
	 * } // main method
	 */
} // class TextScreen
