import java.util.ArrayList;

import jcurses.system.CharColor;

/**
 * A data model for a text screen displayed with JCurses api. This will have so
 * many rows and columns of Character Cells. A char cell has ascii code,
 * foreground color and background color.
 * 
 * @author Larry Gray
 * 
 */
public class TextScreenModel {
	/**
	 * Character cell is a data model that contains ascii char code, fore color
	 * and back color.
	 * 
	 * @author Larry Gray
	 * 
	 */
	public static class CharCell {
		/**
		 * Constructs a character cell given a char and a JCurses CharColor
		 * object.
		 * 
		 * @param aChar
		 * @param color
		 */
		public CharCell(char aChar, CharColor color) {
			this.aChar = aChar;
			this.color = color;
		} // CharCell constructor

		/**
		 * Sets the char for this char cell.
		 * 
		 * @param aChar
		 */
		public void setChar(char aChar) {
			this.aChar = aChar;
		} // setChar(char)

		/**
		 * A character for this cell
		 */
		private char aChar;

		/**
		 * Gets the char for this cell.
		 * 
		 * @return
		 */
		public char getChar() {
			return aChar;
		} // getChar

		/**
		 * Sets the char cell color to JCurses fg/bg color.
		 * 
		 * @param color
		 */
		public void setColor(CharColor color) {
			this.color = color;
		} // setColor

		/** JCurses CharColor fg/bg */
		private CharColor color;

		/**
		 * Gets JCurses Char cell color.
		 * 
		 * @return
		 */
		public CharColor getColor() {
			return color;
		} // getColor
	} // class CharCell

	/**
	 * A Model for the scrollable table that will be viewed.
	 * 
	 * @author Larry Gray
	 * 
	 */
	public static class TableModel {
		private boolean checkXBounds(int x){
			if(x<0||x>tableData.length)return false;
			return true;
		}
		private boolean checkYBounds(int y){
			if(y<0||y>tableData[0].length)return false;
			return true;
		}
		
		/**
		 * Gets character cell for x,y location in table model. Used to refresh
		 * the table view and draw table.
		 * 
		 * @param x
		 * @param y
		 * @return
		 */
		public CharCell getCharCell(int x, int y) {
			
			x -= colBegin;
			y -= rowBegin;
		//	if(x<0||x>tableX-viewWidth)return this.tableData[0][0];
		//	if(y<0||y>tableY-viewHeight)return this.tableData[0][0];
	
			if (y <= colHeaderRow && x <= rowHeaderCol) {
				if(checkXBounds(x)&&checkYBounds(y)) return this.tableData[x][y];
			} // if
			if (y <= colHeaderRow && x > rowHeaderCol) {
				if(checkXBounds(x+this.viewCol)&&checkYBounds(y)) return this.tableData[x + this.viewCol][y];
			} // if
			if (y > colHeaderRow && x <= rowHeaderCol) {
				if(checkXBounds(x)&&checkYBounds(y+this.viewRow)) return this.tableData[x][y + this.viewRow];
			} // if
			if (y > colHeaderRow && x > rowHeaderCol) {
				if(checkXBounds(x+this.viewCol)&&checkYBounds(y+this.viewRow)) return this.tableData[x + this.viewCol][y + this.viewRow];
			} // if
			return new CharCell(' ', new CharColor(CharColor.RED,
					CharColor.YELLOW));
		} // getCharCell
		/**
		 * 
		 * @param r
		 * @param aCellColor
		 */
		public void rowColor(int row, CharColor aCellColor ){
			for(int x=0;x<this.tableX;x++){
				tableData[x][row].color=aCellColor;
			}
		} // rowColor(row, aCellColor)
		/**
		 * 
		 * @param c
		 * @param r
		 * @param s
		 */
		public void storeTableCell(int c, int r, String s) {
			//CharColor aColor = new CharColor(CharColor.WHITE, CharColor.RED);
			char[] chars = s.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				//CharCell aCell = new CharCell(chars[i], aColor);
				tableData[cols[c] + i][r].aChar=chars[i];
			} // for

		} // storeCell
		/**
		 * 
		 * @param c
		 * @param r
		 * @param s
		 */
		public void storeTableCell(int c, int r, String s, CharColor aCharColor) {
			//CharColor aColor = new CharColor(CharColor.WHITE, CharColor.RED);
			char[] chars = s.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				//CharCell aCell = new CharCell(chars[i], aColor);
				tableData[cols[c] + i][r].aChar=chars[i];
				tableData[cols[c]+i][r].color=aCharColor;
			} // for

		} // storeCell

		/**
		 * Sets screen position for top left corner of table.
		 * 
		 * @param x
		 * @param y
		 */
		public void setScreenPosition(int x, int y) {
			rowBegin = y;
			colBegin = x;
		} // setScreenPosition

		/** Screen y position for top left corner. */
		private int rowBegin = 0;
		/** Screen x position for top left corner. */
		private int colBegin = 0;

		/**
		 * Sets header positions within table for row and column headers. If you
		 * scroll down column headers will not scroll and row header will. But
		 * if you scroll left or right then row header will not scroll and
		 * column headers will. Depending on the size of the table, row or
		 * column headers may not scroll at all.
		 * 
		 * @param row
		 * @param col
		 */
		public void setHeaderPositions(int row, int col) {
			colHeaderRow = row;
			rowHeaderCol = col;
		} // setHeaderPositions

		/** lower row of column header */
		private int colHeaderRow;
		/** right most column of row headers */
		private int rowHeaderCol;

		/**
		 * Sets a view port for the table.
		 * 
		 * @param x
		 * @param y
		 */
		public void setViewPosition(int x, int y) {
			if(x>0&&x<tableX-viewWidth)
				viewCol = x;
			if(y>0&&y<tableY-viewHeight)
				viewRow = y;
		} // setView

		/** Current Col Position of the View*/
		private int viewCol;
		/** Current Row Position of the View*/
		private int viewRow;

		/**
		 * 
		 * @param w
		 * @param h
		 */
		public void setViewPortSize(int w, int h) {
			viewWidth = w;
			viewHeight = h;
		} // setViewPortSize

		/** */
		private int viewWidth;
		/** */
		private int viewHeight;

		/**
		 * 
		 * @param cols
		 */
		public void setCols(int[] cols) {
			this.cols = cols;
		} // setCols

		/**
		 * 
		 * @param x
		 * @param y
		 */
		public void setTableSize(int x, int y) {
			tableX = x;
			tableY = y;
		} // setTableSize

		/** random text used for testing the table. */
		String words = "Air well (condenser) From Wikipedia, the free encyclopedia Jump to: navigation, search  hemisphere winter and southern hemisphere summer. ";

		/**
		 * Gets a random word from a text exert.
		 * 
		 * @return A random word.
		 */
		private String getRandomWord() {
			String randomWords[] = words.split(" ");
			int a = (int) (Math.random() * 100);
			return randomWords[a];
		} // getRandomWord();

		/**
		 * Sets up a default TableModel
		 */
		public TableModel() {
			// setup default values
			this.setScreenPosition(10, 10);
			this.setTableSize(200, 100);
			this.setViewPortSize(80, 30);
			this.setViewPosition(0, 0);
			this.setHeaderPositions(2, 20);
			int numberOfColumns = getNumberOfColumns();
			int numberOfRows = getNumberOfRows();
			initRows(numberOfRows);
			/*
			 * for (int c=0;c<numberOfColumns;c++){ for(int
			 * r=0;r<numberOfRows;r++){ //c =
			 * (int)(Math.random()*numberOfColumns); //r =
			 * (int)(Math.random()*numberOfRows);
			 * this.storeCell(c,r,"| "+this.getRandomWord()); }
			 * 
			 * }
			 */
			this.storeTableCell(2, 5, "Assets");
		} // no args Constructor

		/**
		 * Gets the number of rows in the table.
		 * 
		 * @return
		 */
		public int getNumberOfRows() {
			return tableY;
		} // getNumberOfRows

		/**
		 * Gets the number of data columns in the table.
		 * 
		 * @return
		 */
		public int getNumberOfColumns() {
			return cols.length;
		} // getNumberOfColumns

		/** Number of cellular columns in table. */
		private int tableX;
		/** Number of rows in table. */
		private int tableY;

		/**
		 * Sets the colors for alternating rows or sets of rows.
		 * 
		 * @param charColors
		 */
		public void setRowColors(CharColor[] charColors) {
			rowColors = charColors;
		} // setRowColors

		/**
		 * Initializes row cells by row colors.
		 * 
		 * @param rows
		 */
		public void initRows(int rows) {
			tableData = new CharCell[this.tableX][this.tableY];
			int totalRowColors = rowColors.length;
			for (int x = 0; x < tableX; x++) {
				for (int y = 0; y < tableY; y++) {
					double d = (double) y / (double) totalRowColors;
					// System.out.println("totalRowColors"+totalRowColors+"d"+d);
					int theRowColor = ((y / totalRowColors) - ((int) (y / totalRowColors)))
							* totalRowColors;
					// System.out.println("row Color"+theRowColor);
					if (theRowColor >= 0 && theRowColor < 4) {
						CharCell aCharCell = new CharCell(' ',
								rowColors[theRowColor]);
						tableData[x][y] = aCharCell;
					} else {
						System.out.println("theRowColor not >0 and <4"
								+ theRowColor + " total row colors "
								+ totalRowColors);
						System.exit(0);
					} // else
				} // for
			} // for
		} // initRows()

		/** Default cols setting. */
		private int[] cols = { 10, 20, 30, 40, 50, 60, 70, 80, 90 };
		/** Default row colors setting. */
		private CharColor[] rowColors = {
				new CharColor(CharColor.BLUE, CharColor.CYAN),
				new CharColor(CharColor.CYAN, CharColor.BLUE),
				new CharColor(CharColor.BLACK, CharColor.GREEN),
				new CharColor(CharColor.GREEN, CharColor.BLACK) };
		/** Cell data for the table. */
		private CharCell[][] tableData;

	} // inner Class TableModel
	
	
	/**
	 * 
	 * @param c
	 * @param r
	 * @param s
	 */
	public void storeText(int c, int r, String s) {
		//CharColor aColor = new CharColor(CharColor.WHITE, CharColor.RED);
		char[] chars = s.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			//CharCell aCell = new CharCell(chars[i], aColor);
			screenData[c + i][r].aChar=chars[i];
		} // for

	} // storeCell
	/**
	 * 
	 * @param c
	 * @param r
	 * @param s
	 */
	public void storeText(int c, int r, String s, CharColor aCharColor) {
		//CharColor aColor = new CharColor(CharColor.WHITE, CharColor.RED);
		char[] chars = s.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			//CharCell aCell = new CharCell(chars[i], aColor);
			screenData[c + i][r].aChar=chars[i];
			screenData[c+i][r].color=aCharColor;
		} // for

	} // storeCell

	/**
	 * 
	 * @param r
	 * @param aCellColor
	 */
	public void rowColor(int row, CharColor aCellColor ){
		for(int x=0;x<this.cols;x++){
			screenData[x][row].color=aCellColor;
		}
	} // rowColor(row, aCellColor)
	

	/** Number of cols in screen. */
	private int cols;
	/** Number of rows in screen. */
	private int rows;

	/**
	 * Constructs a TextScreenModel with given number of cols and rows. Used
	 * default data for setup.
	 * 
	 * @param cols
	 * @param rows
	 */
	public TextScreenModel(int cols, int rows) {
		this.cols = cols;
		this.rows = rows;
		screenData = new CharCell[cols][rows];
		CharCell blankCell = null;
				
		for (int x = 0; x < cols; x++)
			for (int y = 0; y < rows; y++){
				blankCell = new CharCell(' ', new CharColor(CharColor.BLACK,
						CharColor.WHITE));
				screenData[x][y] = blankCell;
			}

	} // constructor TextScreenModel(cols,rows)

	/** A cellular data grid array containing cell data. */
	private CharCell[][] screenData;
	/** A table model for this screen. */
	private TableModel table;

	/**
	 * Sets the tableModel for this screen overriding the default model.
	 * 
	 * @param aTableModel
	 */
	public void setTableModel(TableModel aTableModel) {
		this.table = aTableModel;
	} // setTableModel

	/**
	 * Writes a CharCell to the grid for the screen.
	 * 
	 * @param aChar
	 * @param x
	 * @param y
	 */
	public void writeCharCell(CharCell aChar, int x, int y) {
		screenData[x][y] = aChar;
	} // writeChar(CharCell, x, y)

	/**
	 * Gets a CharCell from the grid data for this screen.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public CharCell getCharCell(int x, int y) {
		if (x < table.colBegin || y < table.rowBegin)
			return screenData[x][y];
		else
			return table.getCharCell(x, y);
	} // getCharCell(x,y)

	/**
	 * Pans the view right a given number of cell columns.
	 * 
	 * @param columns
	 */
	public void incViewX(int columns) {
		if(this.table.viewCol+columns<this.table.tableX)
			this.table.viewCol += columns;
	} // incVeiwX(columns)

	/**
	 * Pans the view down a given number of rows.
	 * 
	 * @param rows
	 */
	public void incViewY(int rows) {
		if(this.table.viewRow+rows<this.table.tableY)
			this.table.viewRow += rows;
	} // incViewY(rows)

	/**
	 * Pans the view left a given number of cell columns
	 * 
	 * @param columns
	 */
	public void decViewX(int columns) {
		if(this.table.viewCol-columns>0)
			this.table.viewCol -= columns;
		else this.table.viewCol=0;
	} // decViewX(columns)

	/**
	 * Pans the view up a given number of rows.
	 * 
	 * @param rows
	 */
	public void decViewY(int rows) {
		if(this.table.viewRow-rows>0)
			this.table.viewRow -= rows;
		else this.table.viewRow=0;
	} // decViewY(rows);
} // class TextScreenModel
